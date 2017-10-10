package com.lightningfast.ui.activity;

import android.os.Bundle;

import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.databinding.ActivityAgreementBinding;

public class AgreementActivity extends BaseActivity<ActivityAgreementBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {

    }


    @Override
    protected void initViews() {
        mDataBinding.webView.loadUrl("http://www.sdkfenqi.com/front/login/to_login.html");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_agreement;
    }
}
