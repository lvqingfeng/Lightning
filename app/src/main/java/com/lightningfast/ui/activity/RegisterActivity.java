package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.base.HttpResult;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.RegisterBean;
import com.lightningfast.databinding.ActivityRegisterBinding;
import com.lightningfast.service.RegisterCodeTimerService;
import com.lightningfast.uitls.EditTextUtils;
import com.lightningfast.uitls.LogInUtils;

import rx.Subscriber;

/***
 * 注册
 * */
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> {
    private InputMethodManager mInputManager;
    public static final int REQUEST_REGISTER=0x000123;
    public static void actionStart(Activity activity,int requestCode) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivityForResult(intent,requestCode);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void initFieldBeforeMethods() {
        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    @Override
    protected void setupTitle() {

    }

    @Override
    protected void initViews() {
        //检测手机号和密码的输入
        EditTextUtils.checkOnEditInputForButtonState(mContext
                ,mDataBinding.registerBtnRegister,mDataBinding.registerPhone
                ,mDataBinding.registerPassword,mDataBinding.registerMessageCode);
        RegisterCodeTimerService.setCountDownTime(60000);
        RegisterCodeTimerService.setCountDownView(mDataBinding.registerBtnCode, R.drawable.bg_blue_fillet);

        mDataBinding.registerBtnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInputManager.hideSoftInputFromWindow(mDataBinding.getRoot().getWindowToken(), 0);
                String phone=mDataBinding.registerPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)){
                    showMessage("请输入正确的手机号码");
                    return;
                }
//                if (!LogInUtils.isPhone(phone)){
//                    showMessage("请输入正确的手机号码");
//                    return;
//                }
                checkMobile(phone);

            }
        });
        mDataBinding.registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInputManager.hideSoftInputFromWindow(mDataBinding.getRoot().getWindowToken(), 0);
                String phone=mDataBinding.registerPhone.getText().toString().trim();
                String code=mDataBinding.registerMessageCode.getText().toString();
                String pass=mDataBinding.registerPassword.getText().toString().trim();
                if (TextUtils.isEmpty(phone)){
                    showMessage("请输入手机号");
                    return;
                }

                if (TextUtils.isEmpty(code)){
                    showMessage("请获取验证码并填写");
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    showMessage("请输入密码");
                    return;
                }

                if (!"1".equals(LogInUtils.checkPassword(pass))){
                    showMessage(LogInUtils.checkPassword(pass));
                    return;
                }
                registers(phone,code,pass);
            }
        });

    }


    private void checkMobile(final String phone){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).checkMobile(phone)
                , new Subscriber<HttpResult<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult<String> result) {
                        if (ApiService.STATUS_SUC.equals(result.getCode())){
                            getMessageCode(phone);
                        }else {
                            showMessage(result.getMsg());
                        }
                    }
                });

    }
    private void getMessageCode(String phone){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).createVerify(phone)
                ,new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean result) {
                if (1==result.getCode()){
                    RegisterCodeTimerService.startService(RegisterActivity.this, R.drawable.bg_blue_fillet);
                    showMessage(result.getMsg());
                }
            }
        }));
    }

    private void registers(final String phone, String code, final String pass){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object)
                .register(phone, code, pass), new ProgressSubscriber<>(mContext
                , new SubscriberOnNextListener<RegisterBean>() {
            @Override
            public void onNext(RegisterBean result) {
                if (result.getCode()==1){
                    showMessage(result.getMsg());
                    ChooseEmployeeActivity.actionStart(RegisterActivity.this,result.getData().getUid(),phone,pass);
                }else {
                    showMessage(result.getMsg());
                }
            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RegisterCodeTimerService.onActivityFinish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

}
