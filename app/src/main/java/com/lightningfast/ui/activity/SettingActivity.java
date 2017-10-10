package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.databinding.ActivitySettingBinding;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.uitls.UserUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;

/***
 * 设置
 * */
public class SettingActivity extends BaseActivity<ActivitySettingBinding> {
    private static int versionCode;
    private static String versionName;
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, SettingActivity.class);
        activity.startActivity(intent);
    }

    public class Presenter{
        //修改密码
        public void onClickEditPassword(){
            ForgetPassWordActivity.actionStart(SettingActivity.this);
        }
        //关于我们
        public void onClickAboutUs(){
            AboutUsActivity.actionStart(SettingActivity.this);
        }
        //退出登录
        public void onClickExitLogin(){
            DialogUtils.alertDialog(mContext, "温馨提示", "您确定要注销登录?", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismiss();
                }
            }, new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismiss();
                    UserUtils.quit(mContext);
                    LoginHelper.getInstance().setOnline(false);
                    LoginActivity.actionStart(SettingActivity.this,LoginActivity.FROM_GUIDE);
                    finish();
                }
            });
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {

    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {

        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versionCode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }
    /***
     * 获取当前应用的版本号
     * */

    private String getVersionName() throws Exception
    {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
        String version = packInfo.versionName;
        return version;
    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(new Presenter());
        mDataBinding.versionMessage.setText(getAppVersionName(mContext));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }
}
