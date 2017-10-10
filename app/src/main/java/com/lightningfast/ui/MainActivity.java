package com.lightningfast.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hanks.htextview.HTextViewType;
import com.lightningfast.Constant;
import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BannerBean;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.HomeMessageBean;
import com.lightningfast.bean.RepaymentBean;
import com.lightningfast.bean.VersionBean;
import com.lightningfast.databinding.ActivityMainBinding;
import com.lightningfast.newversion.NewMainActivity;
import com.lightningfast.service.DownloadService;
import com.lightningfast.ui.activity.AdvertisementActivity;
import com.lightningfast.ui.activity.ApplyLoanActivity;
import com.lightningfast.ui.activity.LoginActivity;
import com.lightningfast.ui.activity.MessageActivity;
import com.lightningfast.ui.activity.RepaymentActivity;
import com.lightningfast.uitls.AppManager;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.GlideImageLoader;
import com.lightningfast.uitls.LoginHelper;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscriber;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private boolean isClick = false;
    private List<String> list_path;
    private List<String> mNotice;
    private String tv_notice;
    private String order_id;
    private List<BannerBean.DataBean> banners;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
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
    protected void onResume() {
        super.onResume();
        initRepayment();
        initNotice();
    }

    @Override
    protected void initFieldBeforeMethods() {
        cheVersion();
        banners = new ArrayList<>();
        mNotice = new ArrayList<>();
        list_path = new ArrayList<>();
        initBanner();
    }

    @Override
    protected void initViews() {
        initNotice();
        new LooperThread().start();
        mDataBinding.information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewMainActivity.actionStart(MainActivity.this);
//                NoticeActivity.actionStart(MainActivity.this);
//                CameraActivity.actionStart(MainActivity.this);
            }
        });

        mDataBinding.person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String smsInPhone = SmsRead.getSmsInPhone(mContext);
                if (LoginHelper.getInstance().checkIsOnline()) {
                    MessageActivity.actionStart(MainActivity.this);
                } else {
                    startLogin();
                }
            }
        });
        mDataBinding.loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginHelper.getInstance().checkIsOnline()) {
                    isLoan();
                } else {
                    startLogin();
                }
            }
        });
        mDataBinding.repayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (LoginHelper.getInstance().checkIsOnline()) {
                    isClick = true;
                    initRepayment();
                } else {
                    startLogin();
                }
            }
        });


    }

    private void cheVersion() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .updateCheckVersion(), new Subscriber<VersionBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final VersionBean result) {
                if (result.getCode() == 1) {
                    if (Integer.parseInt(getVersionCode(mContext)) < result.getData().getVersion_value()) {
                        DialogUtils.alertDialogNormal(mContext, "版本更新"
                                , result.getData().getLog(), new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                    }
                                }, new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismiss();
                                        if (!TextUtils.isEmpty(result.getData().getDownload_url())) {
                                            DownloadService.actionStart(mContext, result.getData().getDownload_url());
                                        } else {
                                            Toast.makeText(mContext, "暂时无法下载", Toast.LENGTH_SHORT).show();
                                        }
                                        Toast.makeText(mContext, "文件开始下载", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
            }
        });
    }

    public String getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    private void isLoan() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                        .getIsLoan(LoginHelper.getInstance().getUserId(), LoginHelper.getInstance().getUserToken())
                , new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean result) {
                        if (result.getCode() == 1) {
                            ApplyLoanActivity.actionStart(MainActivity.this);
                        } else {
                            Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initRepayment() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .updateRepayment(LoginHelper.getInstance().getUserId()), new Subscriber<RepaymentBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RepaymentBean result) {
                if (result.getCode() == 1) {
                    mDataBinding.tvRepayment.setText(result.getData().getRemainder());
                    order_id = result.getData().getOrder_id();
                    if (result.getData().getDue_qi_number() != null && Integer.parseInt(result.getData().getDue_qi_number()) != 0) {
                        mDataBinding.tvDue.setText("(逾期" + result.getData().getDue_qi_number() + "期)");
                    }
                    if (isClick) {
                        if (TextUtils.isEmpty(order_id)) {
                            Toast.makeText(mContext, "您当前没有需要还款的订单", Toast.LENGTH_SHORT).show();
                        } else {
                            RepaymentActivity.actionStart(MainActivity.this);
                            isClick = false;
                        }

                    }
                } else {
                    order_id = "";
                    mDataBinding.tvRepayment.setText("无需还款");
                    if (isClick) {
                        isClick = false;
                        Toast.makeText(mContext, "您当前没有需要还款的订单", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void initNotice() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).updateHomePageMessage("1"), new Subscriber<HomeMessageBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HomeMessageBean result) {
                if (result.getCode() == 1) {
                    List<HomeMessageBean.DataBean> data = result.getData();
                    if (data.size() > 0) {
                        mNotice.clear();
                        for (int i = 0; i < data.size(); i++) {
                            mNotice.add(data.get(i).getCustomer_name() + "成功借款" + data.get(i).getNeed_price());
                        }
                    }
                }
            }
        });
    }

    private void initBanner() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object).updateBanner(""), new Subscriber<BannerBean>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BannerBean bannerBean) {
                if (bannerBean.getCode() == 1) {
                    initNotice();
                    banners = bannerBean.getData();
                    list_path.clear();
                    for (int i = 0; i < banners.size(); i++) {
                        list_path.add(banners.get(i).getPath());
                    }
                    mDataBinding.banner.setImageLoader(new GlideImageLoader());
                    mDataBinding.banner.setBannerAnimation(Transformer.Default);
                    mDataBinding.banner.isAutoPlay(true);
                    mDataBinding.banner.setDelayTime(3000);
                    mDataBinding.banner.setIndicatorGravity(BannerConfig.CENTER);
                    mDataBinding.banner.setImages(list_path);
                    mDataBinding.banner.start();

                    mDataBinding.banner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            String url = banners.get(position).getUrl();
                            if (TextUtils.isEmpty(url)) {
                                return;
                            }
                            AdvertisementActivity.actionStart(MainActivity.this, url);
                        }
                    });
                }
            }
        });

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mDataBinding.tvMessage.setAnimateType(HTextViewType.ANVIL);
                    mDataBinding.tvMessage.animateText(tv_notice);
                    break;
                case 1:
                    break;
            }
        }
    };

    class LooperThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                if (mNotice.size() <= 0) {
                    mNotice.add("张**龙成 功借款5000.00元");
                    mNotice.add("贺**平于五分钟前成功借款10000.00元");
                    mNotice.add("林**涛三分钟前成功借款2000.00元");
                }
                for (int i = 0; i < mNotice.size(); i++) {
                    if (i == mNotice.size() - 1) {
                        tv_notice = mNotice.get(i);
                        i = -1;
                    } else {
                        tv_notice = mNotice.get(i);
                    }
                    if (i > 0) {
                        Thread.sleep(5000);
                    }
                    Message m = new Message();
                    m.what = 0;
                    handler.sendMessage(m);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("mains", "主页重新被打开");
        boolean isConflict = intent.getBooleanExtra(Constant.ACCOUNT_CONFLICT, false);
        if (isConflict) {
            DialogUtils.alertDialog(mContext, "温馨提示", "当前账号已在另外一台设备上登陆"
                    , new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            logout(sweetAlertDialog);
                        }
                    }, new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            logout(sweetAlertDialog);
                        }
                    });
        }
    }

    /**
     * 退出登陆
     *
     * @param dialog
     */
    private void logout(SweetAlertDialog dialog) {
        dialog.dismissWithAnimation();
        LoginHelper.getInstance().setOnline(false);
        LoginActivity.actionStart(MainActivity.this, LoginActivity.FROM_OTHER);
    }

    private void startLogin() {
        DialogUtils.alertDialog(mContext, "温馨提示", "您还未登录或是登录已过期,是否重新登录?", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
            }
        }, new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismiss();
                LoginActivity.actionStart(MainActivity.this, LoginActivity.FROM_OTHER);
            }
        });
    }

    @Override
    public void onBackPressed() {
        AppManager.getAppManager().exitApp();
    }
}
