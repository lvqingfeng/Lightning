package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;

import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.databinding.ActivityAdvertisementBinding;

public class AdvertisementActivity extends BaseActivity<ActivityAdvertisementBinding> {
    private String mUrl;
    public static void actionStart(Activity activity,String mUrl) {
        Intent intent = new Intent(activity, AdvertisementActivity.class);
        intent.putExtra("mUrl",mUrl);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("活动",DEFAULT_TITLE_TEXT_COLOR);
    }


    @Override
    protected void initViews() {
        mUrl=getIntent().getStringExtra("mUrl");
        WebSettings webSettings = mDataBinding.webView.getSettings();
        webSettings.setDomStorageEnabled(true);
        mDataBinding.webView.loadUrl(mUrl);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advertisement;
    }
}
