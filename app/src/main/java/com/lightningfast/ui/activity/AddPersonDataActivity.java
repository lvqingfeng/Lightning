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
import com.lightningfast.bean.DatasBean;
import com.lightningfast.bean.PersonInfoBean;
import com.lightningfast.databinding.ActivityEditPersonDataBinding;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.SelectMenuPopWindow;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/***
 * 编辑个人信息
 * */
public class AddPersonDataActivity extends BaseActivity<ActivityEditPersonDataBinding> {
    public static final int EDITPERSON_REQUESTCODE=0x000124;
    private SelectMenuPopWindow popEducation;//学历
    private SelectMenuPopWindow popMarry;//婚姻
    private SelectMenuPopWindow popHouse;//住房
    private SelectMenuPopWindow popCar;//车
    private SelectMenuPopWindow popHousehold;//户籍类型

    private List<DatasBean.DataBean.EducationBean> mEduList;
    private List<DatasBean.DataBean.HouseNatureBean> mHouseList;
    private List<DatasBean.DataBean.MarryBean> mMarryList;

    private String xueliId;
    private String mfangchanId;
    private String mMarryId;
    private String carType;
    private String houkouType;
    private String provenceId;
    private String cityId;
    private String countyId;
    private String details;
    public static void actionStart(Activity activity,int requestCode) {
        Intent intent = new Intent(activity, AddPersonDataActivity.class);
        activity.startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initFieldBeforeMethods() {
        mEduList=new ArrayList<>();
        mHouseList=new ArrayList<>();
        mMarryList=new ArrayList<>();
        initPop();
        initPopDatas();
        List<String> list4=new ArrayList<String>();
        List<String> list5=new ArrayList<String>();
        list4.add("有");
        list4.add("无");
        list5.add("农业户口");
        list5.add("非农业户口");
        popCar.bindingList(list4);
        popHousehold.bindingList(list5);
    }

    private void initPop() {
        popEducation = new SelectMenuPopWindow(this);
        popMarry = new SelectMenuPopWindow(this);
        popHouse = new SelectMenuPopWindow(this);
        popCar = new SelectMenuPopWindow(this);
        popHousehold = new SelectMenuPopWindow(this);

        popEducation.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.education.setText(itemName);
                xueliId=mEduList.get(position).getId();
            }
        });
        popEducation.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        popMarry.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.marriage.setText(itemName);
                mMarryId=mMarryList.get(position).getId();
            }
        });
        popMarry.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        popHouse.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.housingType.setText(itemName);
                mfangchanId=mHouseList.get(position).getId();
            }
        });
        popHouse.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        popCar.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.cars.setText(itemName);
                if ("有".equals(itemName)){
                    carType="1";
                }else {
                    carType="0";
                }
            }
        });
        popCar.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
        popHousehold.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.household.setText(itemName);
                if ("农业户口".equals(itemName)){
                    houkouType="1";
                }else {
                    houkouType="2";
                }
            }
        });
        popHousehold.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }
    private void initPopDatas() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object).getPopwindowsData(ApiService.popwindows), new Subscriber<DatasBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DatasBean result) {
                if (result.getCode()==1){
                    mEduList.addAll(result.getData().getEducation());
                    mHouseList.addAll(result.getData().getHouse_nature());
                    mMarryList.addAll(result.getData().getMarry());
                    List<String> list1=new ArrayList<String>();
                    List<String> list2=new ArrayList<String>();
                    List<String> list3=new ArrayList<String>();
                    for (int i = 0; i < mEduList.size(); i++) {
                        list1.add(mEduList.get(i).getName());
                    }
                    for (int i = 0; i <mHouseList.size() ; i++) {
                        list2.add(mHouseList.get(i).getName());
                    }
                    for (int i = 0; i <mMarryList.size() ; i++) {
                        list3.add(mMarryList.get(i).getName());
                    }

                    popEducation.bindingList(list1);
                    popHouse.bindingList(list2);
                    popMarry.bindingList(list3);

                }
            }
        });
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("基本信息",DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        initPersomData();
        mDataBinding.education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEduList.size()>0){
                    popEducation.showPopupWindow(mDataBinding.getRoot());
                }else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDataBinding.household.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popHousehold.showPopupWindow(mDataBinding.getRoot());
            }
        });
        mDataBinding.marriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMarryList.size()>0){
                    popMarry.showPopupWindow(mDataBinding.getRoot());
                }else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDataBinding.cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popCar.showPopupWindow(mDataBinding.getRoot());
            }
        });

        mDataBinding.housingType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mHouseList.size()>0){
                    popHouse.showPopupWindow(mDataBinding.getRoot());
                }else {
                    Toast.makeText(mContext, "暂无数据", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mDataBinding.address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressActivity.actionStart(AddPersonDataActivity.this,AddressActivity.ADDRESS_REQUESTCODE);
            }
        });
        mDataBinding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(xueliId)){
                    Toast.makeText(mContext, "请选择学历", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mMarryId)){
                    Toast.makeText(mContext, "请选择婚姻状况", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(houkouType)){
                    Toast.makeText(mContext, "请选择户籍类型", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(carType)){
                    Toast.makeText(mContext, "请选择有无车辆", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mfangchanId)){
                    Toast.makeText(mContext, "请选择住房类型", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(provenceId)){
                    Toast.makeText(mContext, "请选择地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                updatePersonData();

            }


        });
    }

    private void initPersomData() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).getPersonBaseData(LoginHelper.getInstance().getUserId()
                ,LoginHelper.getInstance().getUserToken())
                ,new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<PersonInfoBean>() {
            @Override
            public void onNext(PersonInfoBean result) {
                if (result.getCode()==1){
                    PersonInfoBean.DataBean.CustomerBean customer = result.getData().getCustomer();
                    mDataBinding.education.setText(customer.getEducation_str());
                    mDataBinding.marriage.setText(customer.getMarry_str());
                    mDataBinding.household.setText(customer.getResidence_str());
                    mDataBinding.cars.setText(customer.getCar_str());
                    mDataBinding.housingType.setText(customer.getHouse_nature_str());
                    mDataBinding.address.setText(customer.getArea_str()+customer.getLive_address());

                    xueliId=String.valueOf(customer.getAcademic());
                    mMarryId=String.valueOf(customer.getMarry());
                    houkouType=String.valueOf(customer.getResidence());
                    carType=String.valueOf(customer.getIs_car());
                    mfangchanId=String.valueOf(customer.getLive_address_nature());
                    provenceId=String.valueOf(customer.getProvice_id());
                    cityId=String.valueOf(customer.getCity_id());
                    countyId=String.valueOf(customer.getArea_id());
                    details=customer.getLive_address();
                }else if (result.getCode() == 2){
                    toLogin();
                }
            }
        }));
    }

    private void updatePersonData() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).addPersonData(LoginHelper.getInstance().getUserId(),xueliId
                ,mMarryId,houkouType,carType,mfangchanId,provenceId,cityId,countyId,details,LoginHelper.getInstance().getUserToken())
                ,new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean result) {
                if (result.getCode()==1){
                    AddCompanyActivity.actionStart(AddPersonDataActivity.this,AddCompanyActivity.EditCompany_RequestCode);
                    finish();
                }else if (result.getCode() == 2){
                    toLogin();
                }else {
                    result.getMsg();
                }
            }
        }));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==AddressActivity.ADDRESS_REQUESTCODE){
            if (resultCode==RESULT_OK){
                String address = data.getStringExtra("address");
                mDataBinding.address.setText(address);
                details=data.getStringExtra("mDetails");
                provenceId=data.getStringExtra("mProvence");
                cityId=data.getStringExtra("mCity");
                countyId=data.getStringExtra("mCountry");
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_person_data;
    }
}
