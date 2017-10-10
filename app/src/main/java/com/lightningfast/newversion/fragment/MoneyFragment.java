package com.lightningfast.newversion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightningfast.R;
import com.lightningfast.base.BaseFragment;
import com.lightningfast.databinding.FragmentMoneyBinding;

/**
 * 作者:吕清锋
 * 时间:2017/10/10
 * 版本:v1.0
 * 类描述：现金贷界面
 * 修改时间:
 */

public class MoneyFragment extends BaseFragment<FragmentMoneyBinding> {

    public static MoneyFragment newInstance() {
        MoneyFragment fragment = new MoneyFragment();
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
        return R.layout.fragment_money;
    }
}
