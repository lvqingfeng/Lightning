package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.CompanyDataBean;
import com.lightningfast.bean.ContactBean;
import com.lightningfast.bean.DatasBean;
import com.lightningfast.databinding.ActivityEditCompanyBinding;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.SelectMenuPopWindow;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/***
 *编辑单位信息的界面
 * */
public class AddCompanyActivity extends BaseActivity<ActivityEditCompanyBinding> {
    private List<String>companyTypeList;
    private List<String> workYearsList;
    private List<String> moneyList;
    private List<DatasBean.DataBean.SalaryBean> salary;
    private List<DatasBean.DataBean.CompanyNatureBean> companyNature;
    private List<DatasBean.DataBean.WorkYearsBean> workYears;
    public static final int  EditCompany_RequestCode=0x000123;

    private String company_id="";
    private String contact_id="";
    private String details;
    private String mProvenceId;
    private String mCityId;
    private String mCountryId;
    private String comName;
    private String workId;
    private String moneyId;
    private String typeId;
    private String mpName;
    private String mpPhone;
    private SelectMenuPopWindow popSalary;
    private SelectMenuPopWindow popWorkYears;
    private SelectMenuPopWindow popCompany;
    public static void actionStart(Activity activity,int requestCode) {
        Intent intent = new Intent(activity, AddCompanyActivity.class);
        activity.startActivityForResult(intent,requestCode);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFieldBeforeMethods() {
        moneyList=new ArrayList<>();
        workYearsList=new ArrayList<>();
        companyTypeList=new ArrayList<>();
        salary=new ArrayList<>();
        companyNature=new ArrayList<>();
        workYears=new ArrayList<>();
        initPop();
        inPopDatas();
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("公司信息",DEFAULT_TITLE_TEXT_COLOR);
    }

    private void initPop() {
        popSalary = new SelectMenuPopWindow(AddCompanyActivity.this);
        popWorkYears = new SelectMenuPopWindow(AddCompanyActivity.this);
        popCompany = new SelectMenuPopWindow(AddCompanyActivity.this);

        popSalary.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.money.setText(itemName);
                moneyId=salary.get(position).getId();
            }
        });
        popSalary.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        popWorkYears.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.business.setText(itemName);
                workId=workYears.get(position).getId();
            }
        });
        popWorkYears.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        popCompany.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.type.setText(itemName);
                typeId=companyNature.get(position).getId();
            }
        });
        popCompany.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }

    private void inPopDatas() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object).getPopwindowsData(ApiService.popwindows), new Subscriber<DatasBean>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DatasBean datasBean) {
                if (datasBean.getCode()==1){
                    salary = datasBean.getData().getSalary();
                    for (int i = 0; i < salary.size(); i++) {
                        moneyList.add(salary.get(i).getName());
                    }
                    workYears = datasBean.getData().getWorkYears();
                    for (int i = 0; i < workYears.size(); i++) {
                        workYearsList.add(workYears.get(i).getName());
                    }
                    companyNature = datasBean.getData().getCompanyNature();
                    for (int i = 0; i < companyNature.size(); i++) {
                        companyTypeList.add(companyNature.get(i).getName());
                    }
                    popSalary.bindingList(moneyList);
                    popCompany.bindingList(companyTypeList);
                    popWorkYears.bindingList(workYearsList);
                }
            }
        });
    }

    @Override
    protected void initViews() {
        initCompany();
        initContact();
        mDataBinding.type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (companyNature.size()>0){
                    popCompany.showPopupWindow(mDataBinding.getRoot());
                }else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDataBinding.money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (salary.size()>0){
                    popSalary.showPopupWindow(mDataBinding.getRoot());
                }else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDataBinding.business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (workYearsList.size()>0){
                    popWorkYears.showPopupWindow(mDataBinding.getRoot());
                }else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDataBinding.companyAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressActivity.actionStart(AddCompanyActivity.this,AddressActivity.ADDRESS_REQUESTCODE);
            }
        });
        mDataBinding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mDataBinding.companyName.getText())){
                    comName=mDataBinding.companyName.getText().toString();
                }else {
                    Toast.makeText(mContext, "请输入公司名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(mDataBinding.friendName.getText())){
                    mpName=mDataBinding.friendName.getText().toString();
                }else {
                    Toast.makeText(mContext, "请输入同事姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(mDataBinding.friendPhone.getText())){
                    mpPhone=mDataBinding.friendPhone.getText().toString();
                }else {
                    Toast.makeText(mContext, "请输入公司名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mProvenceId)){
                    Toast.makeText(mContext, "请选择地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(workId)){
                    Toast.makeText(mContext, "请选择工作年限", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(moneyId)){
                    Toast.makeText(mContext, "请选择薪资范围", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(typeId)){
                    Toast.makeText(mContext, "请选择公司性质", Toast.LENGTH_SHORT).show();
                    return;
                }
                EditCompany();
            }
        });
    }

    private void initContact() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .getContactInfo(LoginHelper.getInstance().getUserId(), "2",LoginHelper.getInstance().getUserToken())
                , new Subscriber<ContactBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ContactBean result) {
                if (result.getCode()==1){
                    ContactBean.DataBean bean = result.getData().get(0);
                    contact_id=String.valueOf(bean.getId());
                    mDataBinding.friendName.setText(bean.getContact_name());
                    mDataBinding.friendPhone.setText(bean.getMobile());
                    mpName=bean.getContact_name();
                    mpPhone=bean.getMobile();
                }else if (result.getCode() == 2){
                    toLogin();
                }
            }
        });
    }

    private void initCompany() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object)
                .updateCompanyInfo(LoginHelper.getInstance().getUserId(),LoginHelper.getInstance().getUserToken()),new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<CompanyDataBean>() {
            @Override
            public void onNext(CompanyDataBean result) {
                if (result.getCode()==1){
                    CompanyDataBean.DataBean data = result.getData();
                    mDataBinding.companyName.setText(data.getCompany_name());
                    mDataBinding.companyAddress.setText(data.getArea_str());
                    mDataBinding.business.setText(data.getWork_years_str());
                    mDataBinding.money.setText(data.getSalary_str());
                    mDataBinding.type.setText(data.getCompany_nature_str());

                    comName=data.getCompany_name();
                    company_id=String.valueOf(data.getCompany_id());
                    mProvenceId=String.valueOf(data.getProvince_id());
                    mCityId=String.valueOf(data.getCity_id());
                    mCountryId=String.valueOf(data.getArea_id());
                    moneyId=data.getSalary();
                    typeId=String.valueOf(data.getCompany_nature());
                    workId=String.valueOf(data.getWork_years());
                    details=data.getCompany_address();
                }else if (result.getCode() == 2){
                    toLogin();
                }
            }
        }));
    }

    private void EditCompany(){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService
                .class, RetrofitManager.RetrofitType.Object).addCompanyInfo(company_id, LoginHelper.getInstance().getUserId(),mProvenceId,mCityId
                ,mCountryId,details,comName,typeId,workId,moneyId,mpName,mpPhone,contact_id,LoginHelper.getInstance().getUserToken()),new ProgressSubscriber<BaseBean>(mContext, new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean result) {
                if (result.getCode()==1){
                    AddContactsActivity.actionStart(AddCompanyActivity.this,AddContactsActivity.ADDCONTANTS_REQUESTCODE);
                    finish();
                }else if (result.getCode() ==2){
                    toLogin();
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==AddressActivity.ADDRESS_REQUESTCODE){
            if (resultCode==RESULT_OK){
                if (data!=null){
                    String address = data.getStringExtra("address");
                    mDataBinding.companyAddress.setText(address);
                    details=data.getStringExtra("mDetails");
                    mProvenceId=data.getStringExtra("mProvence");
                    mCityId=data.getStringExtra("mCity");
                    mCountryId=data.getStringExtra("mCountry");
                }
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_company;
    }
}
