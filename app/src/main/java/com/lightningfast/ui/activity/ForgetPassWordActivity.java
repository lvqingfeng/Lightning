package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.databinding.ActivityForgetPassWordBinding;
import com.lightningfast.service.RegisterCodeTimerService;
import com.lightningfast.uitls.EditTextUtils;
import com.lightningfast.uitls.LogInUtils;

import rx.Subscriber;

/***
 * 忘记密码的界面
 * */
public class ForgetPassWordActivity extends BaseActivity<ActivityForgetPassWordBinding> {
    private String phone;
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ForgetPassWordActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {

    }

    @Override
    protected void initViews() {
        RegisterCodeTimerService.setCountDownTime(60000);
        RegisterCodeTimerService.setCountDownView(mDataBinding.registerBtnCode, R.drawable.bg_blue_fillet);
        EditTextUtils.checkOnEditInputForButtonState(mContext,mDataBinding.registerBtnRegister,mDataBinding.registerPhone,mDataBinding.registerMessageCode,mDataBinding.registerPassword);
        mDataBinding.registerBtnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone=mDataBinding.registerPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)){
                    showMessage("请输入手机号码");
                    return;
                }
                getMessageCode(phone);
            }
        });

        mDataBinding.registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = mDataBinding.registerMessageCode.getText().toString().trim();
                String pass = mDataBinding.registerPassword.getText().toString().trim();
                if (TextUtils.isEmpty(code)){
                    showMessage("请输入验证码");
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
                forgetPasWord(phone,code,pass);
            }
        });
    }
    private void getMessageCode(String phone){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).createVerify(phone)
                , new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean result) {
                        if (1==result.getCode()){
                            RegisterCodeTimerService.startService(ForgetPassWordActivity.this, R.drawable.bg_blue_fillet);
                            showMessage(result.getMsg());
                        }
                    }
                });
    }
    private void forgetPasWord(String phone,String code,String pass){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object)
                .resetPassWord(phone,code,pass),new ProgressSubscriber<>(mContext
                , new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean result) {
                if (result.getCode()==1){
                    showMessage(result.getMsg());
                    finish();
                }else {
                    showMessage(result.getMsg());
                }
            }
        }));
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pass_word;
    }
}
