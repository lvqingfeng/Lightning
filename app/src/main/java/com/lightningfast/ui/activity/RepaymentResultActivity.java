package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.RepaymentResultBean;
import com.lightningfast.databinding.ActivityRepaymentResultBinding;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.CountDownViews;

public class RepaymentResultActivity extends BaseActivity<ActivityRepaymentResultBinding> {
    private String id;
    public static void actionStart(Activity activity,String id) {
        Intent intent = new Intent(activity, RepaymentResultActivity.class);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        setTextTitleView("还款结果",DEFAULT_TITLE_TEXT_COLOR);
    }


    @Override
    protected void initViews() {
        id=getIntent().getStringExtra("id");
        mDataBinding.timeView.start();

        mDataBinding.timeView.setOnLoadingFinishListener(new CountDownViews.OnLoadingFinishListener() {
            @Override
            public void finish() {
                RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object).getRepaymentResult(LoginHelper
                        .getInstance().getUserId(),LoginHelper.getInstance().getUserToken(),id),new ProgressSubscriber<RepaymentResultBean>(mContext, new SubscriberOnNextListener<RepaymentResultBean>() {
                    @Override
                    public void onNext(RepaymentResultBean result) {
                        if (result.getCode()==1){
                            switch (result.getData().getStatus()){
                                case -1:
                                    Toast.makeText(mContext, result.getData().getFail_reason(), Toast.LENGTH_SHORT).show();
                                    RepaymentResultActivity.this.finish();
                                    break;
                                case 0:
                                    Toast.makeText(mContext, result.getData().getFail_reason(), Toast.LENGTH_SHORT).show();
                                    RepaymentResultActivity.this.finish();
                                    break;
                                case 1:
                                    Toast.makeText(mContext, result.getData().getFail_reason(), Toast.LENGTH_SHORT).show();
                                    RepaymentResultActivity.this.finish();
                                    break;
                            }
                        }
                    }
                }));
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            Toast.makeText(mContext, "请稍后...", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repayment_result;
    }
}
