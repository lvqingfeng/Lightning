package com.lightningfast.ui.manager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lightningfast.base.LazyFragment;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/24
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class LoanUserPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] ={"申请中","已借款","已逾期"};
    private List<LazyFragment> fragments;
    public LoanUserPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public LoanUserPagerAdapter(FragmentManager fm, List<LazyFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
