package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.databinding.ActivityAboutUsBinding;
/***
 * 关于我们的界面
 * */
public class AboutUsActivity extends BaseActivity<ActivityAboutUsBinding> {
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, AboutUsActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("关于我们",DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        mDataBinding.webView.loadUrl("https://www.sdkfenqi.com");
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_about_us;
    }
}
