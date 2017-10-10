package com.lightningfast.ui.manager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.EmployeeDataBean;
import com.lightningfast.databinding.ActivityMannerDataBinding;
import com.lightningfast.uitls.EmployeeLoginHelper;

public class MannerDataActivity extends BaseActivity<ActivityMannerDataBinding> {
    public static void actionStart(Activity activity){
        Intent intent = new Intent(activity,MannerDataActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("个人信息",DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .updateEmployeeInfo(EmployeeLoginHelper.getInstance().getUserId(),EmployeeLoginHelper.getInstance().getUserToken()),new ProgressSubscriber<EmployeeDataBean>(mContext, new SubscriberOnNextListener<EmployeeDataBean>() {
            @Override
            public void onNext(EmployeeDataBean result) {
                if (result.getCode()==1){
                    mDataBinding.name.setText(result.getData().getInfo().getEmployee_name());
                    mDataBinding.age.setText(result.getData().getInfo().getBirth());
                    mDataBinding.address.setText(result.getData().getInfo().getAddress());
                    mDataBinding.money.setText(result.getData().getInfo().getDeposit());
                    mDataBinding.gender.setText(result.getData().getInfo().getGetder_str());
                    mDataBinding.mobile.setText(result.getData().getInfo().getMobile());
                    mDataBinding.risk.setText(result.getData().getInfo().getRisk());
                }
            }
        }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manner_data;
    }
}
