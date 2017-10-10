package com.lightningfast.ui.manager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.databinding.ActivityResetPassWordBinding;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.EmployeeLoginHelper;
import com.lightningfast.uitls.UserUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ResetPassWordActivity extends BaseActivity<ActivityResetPassWordBinding> {
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, ResetPassWordActivity.class);
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
        mDataBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass = mDataBinding.oldPassword.getText().toString();
                String newPass = mDataBinding.newPassword.getText().toString();
                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(mContext, "请输入旧密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(newPass)) {
                    Toast.makeText(mContext, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                reset(pass,newPass);
            }
        });
    }

    private void reset(String pass, String newPass) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .employeeResetPassword(EmployeeLoginHelper.getInstance().getUserId(), pass, newPass), new ProgressSubscriber<BaseBean>(mContext, new SubscriberOnNextListener<BaseBean>() {
            @Override
            public void onNext(BaseBean result) {
                if (result.getCode() == 1) {
                    UserUtils.quit(mContext);
                    DialogUtils.alertDialogNormal(mContext, "温馨提示", "密码修改成功,是否前往登录?", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    }, new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            MannerLoginActivity.actionStart(ResetPassWordActivity.this);
                            finish();
                        }
                    });
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_pass_word;
    }
}
