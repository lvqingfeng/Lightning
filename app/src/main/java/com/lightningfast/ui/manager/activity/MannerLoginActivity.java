package com.lightningfast.ui.manager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.lightningfast.Constant;
import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.Employee;
import com.lightningfast.bean.EmployeeLoginBean;
import com.lightningfast.databinding.ActivityMannerLoginBinding;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.EditTextUtils;
import com.lightningfast.uitls.EmployeeLoginHelper;
import com.lightningfast.uitls.EmployeeUtils;
import com.lightningfast.uitls.NetUtils;

import rx.Subscriber;

public class MannerLoginActivity extends BaseActivity<ActivityMannerLoginBinding> {

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, MannerLoginActivity.class);
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
        /***
         * 若果不是第一次登陆，就默认设置记住密码
         * */
        Employee user = EmployeeUtils.getUser(mContext);
        if (user != null) {
            mDataBinding.loginUsername.setText(user.getPhone());
            mDataBinding.loginPassword.setText(user.getPassword());
        }

        /***
         * 检测Button的能否点击的状态，只有这两个个输入框输入
         * 的内容长度都大于3时，button才可以点击
         * **/
        EditTextUtils.checkOnEditInputForButtonState(mContext, mDataBinding.btnLogin
                , mDataBinding.loginUsername, mDataBinding.loginPassword);

        /***
         * 检测账号输入框  如果账号输入框清空，则密码清空
         * **/
        mDataBinding.loginUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (before > 0) {
                    if (s.length() <= 1) {
                        mDataBinding.loginPassword.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mDataBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!NetUtils.isConnected(mContext)) {
                    Toast.makeText(mContext, "当前网络无连接", Toast.LENGTH_SHORT).show();
                    return;
                }
                String userName = mDataBinding.loginUsername.getText().toString().trim();
                String passWord = mDataBinding.loginPassword.getText().toString().trim();
                login(userName,passWord);
            }

        });
    }

    private void login(final String userName, final String passWord) {
        DialogUtils.showProgressDialog(mContext, "正在登录中", "", "");
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .employeeLogin(userName, passWord), new Subscriber<EmployeeLoginBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                showMessage(e.getMessage());
                hideDialogError();
            }

            @Override
            public void onNext(EmployeeLoginBean result) {
                if (result.getCode()==1){
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                    EmployeeUtils.putUserName(mContext,userName);
                    EmployeeUtils.putUserPassword(mContext,passWord);
                    Employee employee = EmployeeUtils.getUser(mContext);
                    employee.setPhone(userName);
                    employee.setPassword(passWord);
                    employee.setUid(result.getData().getDetail().getId());
                    employee.setToken(result.getData().getDetail().getToken());
                    EmployeeUtils.saveUser(mContext,employee);
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent().setAction(Constant.ACTION_PAGE_REFRESH));
                    EmployeeLoginHelper.getInstance().setOnline(true);
                    MannerMainActivity.actionStart(MannerLoginActivity.this);
                    finish();
                }else {
                    showMessage(result.getMsg());
                    hideDialogError();
                }
            }
        });
    }
    private void hideDialogError() {
        DialogUtils.hideDialog(DialogUtils.LoadCompleteType.Error);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_manner_login;
    }
}
