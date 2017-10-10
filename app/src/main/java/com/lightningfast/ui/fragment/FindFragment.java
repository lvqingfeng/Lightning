package com.lightningfast.ui.fragment;

import android.os.Bundle;

import com.lightningfast.R;
import com.lightningfast.base.BaseFragment;
import com.lightningfast.databinding.FragmentFindBinding;

/**
 * Created by Tongfang on 2017/7/7.
 */

public class FindFragment extends BaseFragment<FragmentFindBinding> {

    public static FindFragment newInstance() {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    @Override
    protected void initViews() {
        mDataBinding.webView.loadUrl("http://www.sdkfenqi.com/front/login/to_login.html");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }
}
