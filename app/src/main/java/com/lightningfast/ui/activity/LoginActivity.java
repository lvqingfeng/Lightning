package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.lightningfast.Constant;
import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.LoginBean;
import com.lightningfast.bean.User;
import com.lightningfast.databinding.ActivityLoginBinding;
import com.lightningfast.ui.MainActivity;
import com.lightningfast.ui.manager.activity.MannerLoginActivity;
import com.lightningfast.uitls.AppManager;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.EditTextUtils;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.uitls.NetUtils;
import com.lightningfast.uitls.UserUtils;
import com.lightningfast.view.SendOpinionPopWindow;

import rx.Subscriber;

/***
 * 登录界面
 * */
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    public static final int FROM_GUIDE=0x000123;
    public static final int FROM_OTHER=0x000124;
    private int fromWhere;
    public static void actionStart(Activity activity,int fromWhere) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra("fromWhere",fromWhere);
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
        fromWhere=getIntent().getIntExtra("fromWhere",-1);
        /***
         * 若果不是第一次登陆，就默认设置记住密码
         * */
        User user = UserUtils.getUser(mContext);
        if (user != null) {
            mDataBinding.loginUsername.setText(user.getPhone());
            mDataBinding.loginPassword.setText(user.getPassword());
        }
        /***
         * 检测Button的能否点击的状态，只有这两个个输入框输入
         * 的内容长度都大于3时，button才可以点击
         * **/
        EditTextUtils.checkOnEditInputForButtonState(mContext, mDataBinding.btnLogin, mDataBinding.loginUsername, mDataBinding.loginPassword);
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
        /***
         * 检测密码输入框  如果密码输入框清空，则账号清空
         * **/
        mDataBinding.loginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (before > 0) {
                    if (s.length() <= 1) {
                        mDataBinding.loginUsername.setText("");
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
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(LoginActivity.this, "请输入账号！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passWord)) {
                    Toast.makeText(LoginActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                login(userName, passWord);
            }
        });

        mDataBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.actionStart(LoginActivity.this, RegisterActivity.REQUEST_REGISTER);
            }
        });

        mDataBinding.forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPassWordActivity.actionStart(LoginActivity.this);
            }
        });
        final SendOpinionPopWindow popWindow = new SendOpinionPopWindow(LoginActivity.this, new SendOpinionPopWindow.SendOpinionCallback() {
            @Override
            public void onCallback(String inputText, boolean isAnonymous) {
                if ("sdfq6666".equals(inputText)){
                    MannerLoginActivity.actionStart(LoginActivity.this);
                }else {
                    Toast.makeText(mContext, "邀请码输入有误,请联系相关人员索要", Toast.LENGTH_SHORT).show();
                }
            }
        });
        popWindow.setAnonymousGone();

        mDataBinding.employeeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.showPopupWindow(mDataBinding.getRoot());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    private void login(final String phone, final String pass) {
        DialogUtils.showProgressDialog(mContext, "正在登录中", "", "");
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object)
                .login(phone, pass), new Subscriber<LoginBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                showMessage(e.getMessage());
                hideDialogError();
            }

            @Override
            public void onNext(LoginBean result) {
                if (result.getCode() == 1) {
                    Toast.makeText(LoginActivity.this, result.getMsg(), Toast.LENGTH_SHORT).show();
                    UserUtils.putUserName(mContext, phone);
                    UserUtils.putUserPassword(mContext, pass);
                    User user = UserUtils.getUser(mContext);
                    user.setPhone(phone);
                    user.setPassword(pass);
                    user.setUid(result.getData().getCustomer_id());
                    user.setToken(result.getData().getToken());
                    user.setInfo("customer");
                    UserUtils.saveUser(mContext, user);
                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent().setAction(Constant.ACTION_PAGE_REFRESH));
                    LoginHelper.getInstance().setOnline(true);
                    if (fromWhere==FROM_GUIDE){
                        MainActivity.actionStart(LoginActivity.this);
                        finish();
                    }else {
                        finish();
                    }
                } else {
                    showMessage(result.getMsg());
                    hideDialogError();
                }
            }
        });
    }



    @Override
    public void onBackPressed() {
        AppManager.getAppManager().finishAllActivity();
    }

    private void hideDialogError() {
        DialogUtils.hideDialog(DialogUtils.LoadCompleteType.Error);
    }
}
