package com.lightningfast.newversion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.WindowManager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.databinding.ActivityNewMainBinding;

public class NewMainActivity extends BaseActivity<ActivityNewMainBinding> {
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, NewMainActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected void setupTitle() {
        setTextTitleView("闪电快金融",DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        mDataBinding.newVersionViewpager.setAdapter(new NewVersionAdapter(getSupportFragmentManager()));

        mDataBinding.bottomBar.setMode(BottomNavigationBar.MODE_SHIFTING)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mDataBinding.bottomBar
                .addItem(new BottomNavigationItem(R.drawable.ic_vector_orange_money, "现金贷"))
                .setActiveColor(R.color.red_background)
                .addItem(new BottomNavigationItem(R.drawable.ic_vector_house, "房租贷"))
                .setActiveColor(R.color.green_background)
                .addItem(new BottomNavigationItem(R.drawable.ic_vector_person, "我的"))
                .setActiveColor(R.color.blue_background).setFirstSelectedPosition(0)//设置默认选择的按钮
                .initialise();
        mDataBinding.newVersionViewpager.setCurrentItem(0);

        mDataBinding.bottomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position){
                    case 0:
                        setTextTitleView("现金贷",DEFAULT_TITLE_TEXT_COLOR);
                        break;
                    case 1:
                        setTextTitleView("房租贷",DEFAULT_TITLE_TEXT_COLOR);
                        break;
                    case 2:
                        setTextTitleView("个人中心",DEFAULT_TITLE_TEXT_COLOR);
                        break;
                }
                mDataBinding.newVersionViewpager.setCurrentItem(position);

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        mDataBinding.newVersionViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mDataBinding.bottomBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_main;
    }
}
