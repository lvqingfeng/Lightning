package com.lightningfast.guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.lightningfast.R;
import com.lightningfast.SplashActivity;
import com.lightningfast.uitls.SharedPreferencesUtils;

public class GuidePageActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private static final String SHARE_TAG = "guide_page_load";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide_page);


        mViewPager = ((ViewPager) findViewById(R.id.guide_view_pager));
        mViewPager.setAdapter(new Myadapter(getSupportFragmentManager()));
        boolean isLoadOver = (boolean) SharedPreferencesUtils.getParam(GuidePageActivity.this, SHARE_TAG, false);
        if (isLoadOver) {
            onClickGo();
            return;
        }else {
            SharedPreferencesUtils.setParam(GuidePageActivity.this, SHARE_TAG, true);
        }
    }
    public void onClickGo() {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }
}
