package com.lightningfast.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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
import com.lightningfast.bean.PersonDataBean;
import com.lightningfast.databinding.ActivityMessageBinding;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.LoginHelper;
import com.tbruyelle.rxpermissions.RxPermissions;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.functions.Action1;


public class MessageActivity extends BaseActivity<ActivityMessageBinding> {
    private String tel;
    private String customer_name="";
    private final int REQUEST_CODE=0x000123;
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, MessageActivity.class);
        activity.startActivity(intent);
    }

    public class Presenter {
        public void onClickAudit(){
            AuditActivity.actionStart(MessageActivity.this);
        }

        public void login() {
            if (!LoginHelper.getInstance().isOnline()){
                LoginActivity.actionStart(MessageActivity.this,LoginActivity.FROM_OTHER);
            }
        }

        public void onClickPersonInformation() {
            AuthenticationActivity.actionStart(MessageActivity.this);
        }

        public void onClickPersonLoan() {
            MyLoanActivity.actionStart(MessageActivity.this);
        }

        public void onClickCustomer() {
            if (!TextUtils.isEmpty(tel)){
                callTel(tel);
            }else {
                Toast.makeText(mContext, "暂时还没有专属客服", Toast.LENGTH_SHORT).show();
            }
        }
        public void onClickAddEmployee(){
            AddEmployeeActivity.actionStart(MessageActivity.this,REQUEST_CODE);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE){
            if (resultCode==RESULT_OK){
                initPersonData();
            }
        }
    }

    @Override
    protected void setupTitle() {

    }

    @Override
    protected void initViews() {
        mDataBinding.setPresenter(new Presenter());
        mDataBinding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.actionStart(MessageActivity.this);
            }
        });
        initPersonData();

        mDataBinding.hotLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callTel("17169566666");
            }
        });

        mDataBinding.hotLine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callTel("0951-6078066");
            }
        });
    }
    private void initPersonData(){
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .getPersonData(LoginHelper.getInstance().getUserId(),LoginHelper.getInstance().getUserToken()),new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<PersonDataBean>() {
            @Override
            public void onNext(PersonDataBean result) {
                if (result.getCode()==1){
                    if (result.getData().getCustomer().getIs_order()==1){
                        mDataBinding.progress.setVisibility(View.VISIBLE);
                    }else {
                        mDataBinding.progress.setVisibility(View.GONE);
                    }
                    customer_name = result.getData().getCustomer().getCustomer_name();
                    if (!TextUtils.isEmpty(customer_name)){
                        mDataBinding.login.setText(result.getData().getCustomer().getCustomer_name());
                    }else {
                        if (LoginHelper.getInstance().isOnline()){
                            mDataBinding.login.setText("闪电快金融");
                        }else {
                            mDataBinding.login.setText("请登录");
                        }
                    }
                    mDataBinding.credit.setText(result.getData().getCustomer().getCredit_score());
                    tel=result.getData().getCustomer().getEmployee_mobile();
                    if (TextUtils.isEmpty(tel)){
                        mDataBinding.addEmployee.setVisibility(View.VISIBLE);
                        mDataBinding.viewEm.setVisibility(View.VISIBLE);
                    }else {
                        mDataBinding.addEmployee.setVisibility(View.GONE);
                        mDataBinding.viewEm.setVisibility(View.GONE);
                    }
                }else if (result.getCode()==2){
                    toLogin();
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        }));
    }
    private void callTel(final String mobile){
        RxPermissions.getInstance(mContext).request(Manifest.permission.CALL_PHONE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            DialogUtils.alertDialog(mContext, "温馨提示", "您是否要联系客服?", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();

                                }
                            }, new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    //用intent启动拨打电话
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));

                                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling
                                        //    ActivityCompat#requestPermissions
                                        // here to request the missing permissions, and then overriding
                                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                        //                                          int[] grantResults)
                                        // to handle the case where the user grants the permission. See the documentation
                                        // for ActivityCompat#requestPermissions for more details.
                                        showMessage("权限被拒绝");
                                        return;
                                    }
                                    startActivity(intent);
                                }
                            });
                        } else {
                            Toast.makeText(mContext, "拨号权限被禁用", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }
}
