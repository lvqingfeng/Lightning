package com.lightningfast.ui.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.jingewenku.abrahamcaijin.commonutil.BankCheck;
import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.PersonDataBean;
import com.lightningfast.databinding.ActivityCardBinding;
import com.lightningfast.uitls.LogInUtils;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.SelectMenuPopWindow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddCardActivity extends BaseActivity<ActivityCardBinding> {
    private String salaryId;
    private String address;
    private String name;
    private String id_card;
    private String bank;
    private String bankNumber;
    private String card_end;
    private final int request_code = 0x000123;
    private SelectMenuPopWindow popWindow;
    private Calendar mCalendar;
    private String bankMobile;
    private String bankAddress;
    private String bankCity;
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, AddCardActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("个人信息",DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request_code && resultCode == RESULT_OK) {
            if (data != null) {
                address = data.getStringExtra("address");
                mDataBinding.cardAddress.setText(address);
            }
        }
    }

    @Override
    protected void initViews() {
        mCalendar = Calendar.getInstance();
        initPop();
        initMessage();
        mDataBinding.cardDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int yearNow = mCalendar.get(Calendar.YEAR);
                int monthNow = mCalendar.get(Calendar.MONTH);
                int dayNow = mCalendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(new ContextThemeWrapper(mContext, android.R.style.Theme_Holo_Light_Dialog_NoActionBar), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String newMonth;
                        String newDay;
                        mDataBinding.cardDate.setText(String.valueOf(year) + "-" + (month + 1) + "-" + dayOfMonth);
                        if (String.valueOf(month+1).length()==1){
                            newMonth="0"+String.valueOf(month+1);
                        }else {
                            newMonth=String.valueOf(month+1);
                        }
                        if (String.valueOf(dayOfMonth).length()==1){
                            newDay="0"+String.valueOf(dayOfMonth);
                        }else {
                            newDay=String.valueOf(dayOfMonth);
                        }
                        card_end=String.valueOf(year)+"-"+newMonth+"-"+newDay;
                    }
                }, yearNow, monthNow, dayNow);
                dialog.setTitle("请选择时间");
                dialog.show();
            }
        });
        mDataBinding.cardAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressActivity.actionStart(AddCardActivity.this, request_code);
            }
        });
        mDataBinding.gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.showPopupWindow(mDataBinding.getRoot());
            }
        });
        mDataBinding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mDataBinding.customerName.getText())){
                    name=mDataBinding.customerName.getText().toString();
                }else {
                    Toast.makeText(mContext, "请输入姓名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(salaryId)) {
                    Toast.makeText(mContext, "请选择性别", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(mContext, "请选择地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(mDataBinding.idCard.getText())){
                    id_card=mDataBinding.idCard.getText().toString();
                }else {
                    Toast.makeText(mContext, "请输入身份证号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(mDataBinding.bank.getText())){
                    bank=mDataBinding.bank.getText().toString();
                }else {
                    Toast.makeText(mContext, "请输入银行卡类型", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(mDataBinding.bankNumber.getText())){
                    bankNumber=mDataBinding.bankNumber.getText().toString();
                }else {
                    Toast.makeText(mContext, "请输入银行卡号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!LogInUtils.IDCardValidate(id_card)) {
                    Toast.makeText(mContext, "请输入有效的身份证号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(card_end)){
                    Toast.makeText(mContext, "请选择身份证有限期", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mDataBinding.bankAddress.getText())){
                    Toast.makeText(mContext, "请输入开户行", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    bankAddress=mDataBinding.bankAddress.getText().toString();
                }
                if (!TextUtils.isEmpty(mDataBinding.bankMobile.getText())){
                    bankMobile=mDataBinding.bankMobile.getText().toString();
                }else {
                    Toast.makeText(mContext, "请输入银行卡柜台预留手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mDataBinding.bankCity.getText())){
                    Toast.makeText(mContext, "请输入开户行所在的", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    bankCity=mDataBinding.bankCity.getText().toString();
                }
                if (!BankCheck.checkBankCard(bankNumber)){
                    Toast.makeText(mContext, "请输入正确的银行卡号", Toast.LENGTH_SHORT).show();
                    return;
                }
                updateMessage();
            }
        });
    }

    private void initMessage() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object)
                .getPersonData(LoginHelper.getInstance().getUserId(),LoginHelper.getInstance().getUserToken())
                ,new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<PersonDataBean>() {
            @Override
            public void onNext(PersonDataBean result) {

                if (result.getCode()==1){
                    PersonDataBean.DataBean.CustomerBean data = result.getData().getCustomer();
                    mDataBinding.customerName.setText(data.getCustomer_name());
                    mDataBinding.cardDate.setText(data.getCert_expire());
                    mDataBinding.gender.setText(data.getGender_str());
                    mDataBinding.cardAddress.setText(data.getCard_address());
                    mDataBinding.idCard.setText(data.getId_card());
                    mDataBinding.bank.setText(data.getBank());
                    mDataBinding.bankNumber.setText(data.getBank_number());
                    mDataBinding.bankMobile.setText(data.getBank_telephone_no());
                    mDataBinding.bankAddress.setText(data.getBank_subbranch());
                    mDataBinding.bankCity.setText(data.getBank_city());
                    address=data.getCard_address();
                    salaryId=String.valueOf(data.getGender());
                    card_end=data.getCert_expire();
                    bankMobile=data.getBank_telephone_no();
                    bankAddress=data.getBank_subbranch();
                    bankCity=data.getBank_city();
                }else if (result.getCode()==2){
                    toLogin();
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    private void initPop() {
        popWindow = new SelectMenuPopWindow(this);
        List<String> genList=new ArrayList<>();
        genList.add("男");
        genList.add("女");
        popWindow.bindingList(genList);
        popWindow.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.gender.setText(itemName);
               if ("男".equals(itemName)){
                   salaryId="1";
               }else {
                   salaryId="2";
               }
            }
        });
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            }
        });
    }

    private void updateMessage() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).updatePersonInfomation(LoginHelper.getInstance().getUserId(),name, salaryId, address
                , id_card, bank, bankNumber,LoginHelper.getInstance().getUserToken(),card_end,bankMobile
                ,bankAddress,bankCity)
                , new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<BaseBean>() {
                    @Override
                    public void onNext(BaseBean result) {
                        if (result.getCode() == 1) {
                            AddPersonDataActivity.actionStart(AddCardActivity.this,AddPersonDataActivity.EDITPERSON_REQUESTCODE);
                            finish();
                        }else if (result.getCode() == 2){
                            toLogin();
                        } else {
                            Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card;
    }
}
