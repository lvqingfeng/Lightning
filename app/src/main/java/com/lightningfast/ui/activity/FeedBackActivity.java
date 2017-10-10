package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.databinding.ActivityFeedBackBinding;

public class FeedBackActivity extends BaseActivity<ActivityFeedBackBinding> {
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, FeedBackActivity.class);
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
        openTitleLeftView(true);
        setTextTitleView("审核中",DEFAULT_TITLE_TEXT_COLOR);
        mDataBinding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed_back;
    }
}
