package com.lightningfast.ui.manager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.base.LazyFragment;
import com.lightningfast.databinding.ActivityLoanUserBinding;
import com.lightningfast.ui.manager.fragment.ApplyFragment;
import com.lightningfast.ui.manager.fragment.LoanFragment;
import com.lightningfast.ui.manager.fragment.OverdueFragment;

import java.util.List;

public class LoanUserActivity extends BaseActivity<ActivityLoanUserBinding> {
    private List<LazyFragment> mList;
    private FragmentTransaction transaction;
    private FragmentManager manager;

    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,LoanUserActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("我的客户", DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout,ApplyFragment.newInstance());
        transaction.commit();

        mDataBinding.shenqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataBinding.shenqing.setTextColor(ContextCompat.getColor(mContext,R.color.BaseColor));
                mDataBinding.jiekuan.setTextColor(ContextCompat.getColor(mContext,R.color.textBlackColor));
                mDataBinding.yuqi.setTextColor(ContextCompat.getColor(mContext,R.color.textBlackColor));
                mDataBinding.view1.setBackgroundColor(ContextCompat.getColor(mContext,R.color.BaseColor));
                mDataBinding.view2.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white_background));
                mDataBinding.view3.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white_background));
                FragmentTransaction transaction1 = manager.beginTransaction();
                transaction1.replace(R.id.frame_layout,ApplyFragment.newInstance());
                transaction1.commit();
            }
        });

        mDataBinding.jiekuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataBinding.shenqing.setTextColor(ContextCompat.getColor(mContext,R.color.textBlackColor));
                mDataBinding.jiekuan.setTextColor(ContextCompat.getColor(mContext,R.color.BaseColor));
                mDataBinding.yuqi.setTextColor(ContextCompat.getColor(mContext,R.color.textBlackColor));
                mDataBinding.view1.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white_background));
                mDataBinding.view2.setBackgroundColor(ContextCompat.getColor(mContext,R.color.BaseColor));
                mDataBinding.view3.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white_background));
                FragmentTransaction transaction2 = manager.beginTransaction();
                transaction2.replace(R.id.frame_layout,LoanFragment.newInstance());
                transaction2.commit();
            }
        });

        mDataBinding.yuqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataBinding.shenqing.setTextColor(ContextCompat.getColor(mContext,R.color.textBlackColor));
                mDataBinding.jiekuan.setTextColor(ContextCompat.getColor(mContext,R.color.textBlackColor));
                mDataBinding.yuqi.setTextColor(ContextCompat.getColor(mContext,R.color.BaseColor));
                mDataBinding.view1.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white_background));
                mDataBinding.view2.setBackgroundColor(ContextCompat.getColor(mContext,R.color.white_background));
                mDataBinding.view3.setBackgroundColor(ContextCompat.getColor(mContext,R.color.BaseColor));
                FragmentTransaction transaction3 = manager.beginTransaction();
                transaction3.replace(R.id.frame_layout,OverdueFragment.newInstance());
                transaction3.commit();
            }
        });
//        mList=new ArrayList<>();
//        mList.add(ApplyFragment.newInstance());
//        mList.add(LoanFragment.newInstance());
//        mList.add(OverdueFragment.newInstance());
//        LoanUserPagerAdapter adapter = new LoanUserPagerAdapter(getSupportFragmentManager(), mList);
//        mDataBinding.viewPager.setAdapter(adapter);
//        mDataBinding.toolbarTab.setupWithViewPager(mDataBinding.viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_loan_user;
    }

}
