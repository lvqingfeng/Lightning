package com.lightningfast.ui.manager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BannerBean;
import com.lightningfast.databinding.ActivityMannerMainBinding;
import com.lightningfast.ui.activity.AdvertisementActivity;
import com.lightningfast.uitls.GlideImageLoader;
import com.lightningfast.uitls.LoginHelper;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class MannerMainActivity extends BaseActivity<ActivityMannerMainBinding> {
    private List<BannerBean.DataBean> banners;
    private List<String> list_path;
    public static void actionStart(Activity activity){
        Intent intent=new Intent(activity,MannerMainActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public class Presenter{
        //业务员名下借款的客户
        public void onClickAllUser(){
            LoanUserActivity.actionStart(MannerMainActivity.this);
        }
        //逾期人员名单
        public void onClickOverdueUser(){
            OverdueListActivity.actionStart(MannerMainActivity.this);
        }
        //业务员上传客户资料
        public void onClickUploadData(){
        }
        //业务员信息
        public void onClickMannerData(){
            MannerDataActivity.actionStart(MannerMainActivity.this);
        }
    }
    @Override
    protected void initFieldBeforeMethods() {
        banners=new ArrayList<>();
        list_path=new ArrayList<>();
        initBanner();
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
                    banners = bannerBean.getData();
                    list_path.clear();
                    for (int i = 0; i < banners.size(); i++) {
                        list_path.add(banners.get(i).getPath());
                    }
                    mDataBinding.banner.setImageLoader(new GlideImageLoader());
                    mDataBinding.banner.setBannerAnimation(Transformer.ZoomIn);
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
                            AdvertisementActivity.actionStart(MannerMainActivity.this, url);
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void setupTitle() {
        setTextTitleView("闪电快金融",DEFAULT_TITLE_TEXT_COLOR);
        getRightView().setButtonDrawable(R.drawable.ic_vector_setting);
        getRightView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResetPassWordActivity.actionStart(MannerMainActivity.this);
            }
        });
    }

    @Override
    protected void initViews() {
        Log.e("我屮艸芔茻", LoginHelper.getInstance().getUserToken()+"=="+LoginHelper.getInstance().getUserId());
        mDataBinding.setPresenter(new Presenter());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manner_main;
    }
}
