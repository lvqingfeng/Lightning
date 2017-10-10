package com.lightningfast.newversion;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lightningfast.newversion.fragment.HouseFragment;
import com.lightningfast.newversion.fragment.MoneyFragment;
import com.lightningfast.newversion.fragment.PersonFragment;

/**
 * 作者:吕清锋
 * 时间:2017/10/10
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class NewVersionAdapter extends FragmentPagerAdapter {
    public NewVersionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
               return MoneyFragment.newInstance();
            case 1:
               return HouseFragment.newInstance();
            case 2:
                return PersonFragment.newInstance();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
