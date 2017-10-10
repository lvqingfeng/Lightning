package com.lightningfast.newversion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightningfast.R;
import com.lightningfast.base.BaseFragment;
import com.lightningfast.databinding.FragmentHouseBinding;

/**
 * 作者:吕清锋
 * 时间:2017/10/10
 * 版本:v1.0
 * 类描述：房租贷
 * 修改时间:
 */

public class HouseFragment extends BaseFragment<FragmentHouseBinding> {

    public static HouseFragment newInstance() {
        HouseFragment fragment = new HouseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_house;
    }
}
