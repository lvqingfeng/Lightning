package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.AuditingBean;
import com.lightningfast.databinding.ActivityAuditBinding;
import com.lightningfast.uitls.LoginHelper;

public class AuditActivity extends BaseActivity<ActivityAuditBinding> {

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, AuditActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("审核进度", DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .updateaAuditing(LoginHelper.getInstance().getUserId(), LoginHelper.getInstance().getUserToken()), new ProgressSubscriber<AuditingBean>(mContext, new SubscriberOnNextListener<AuditingBean>() {
            @Override
            public void onNext(AuditingBean result) {
                if (result.getCode() == 1) {
                    mDataBinding.infoFirst.setText(result.getData().getInfo1());
                    mDataBinding.infoSecond.setText(result.getData().getInfo2());
                    if (result.getData().getStatus() == 3) {
                        mDataBinding.infoThird.setText(result.getData().getInfo3()
                                + "\n放款金额:" + result.getData().getNeed_price());
                    }
                    switch (result.getData().getStatus()) {
                        case 1:
                            mDataBinding.ivFirst.setImageResource(R.drawable.ic_vector_blue_point);
                            mDataBinding.statusFirst.setBackgroundResource(R.drawable.bg_blue_round);
                            mDataBinding.statusFirst.setTextColor(ContextCompat.getColor(mContext, R.color.white_background));
                            break;
                        case 2:
                            mDataBinding.ivFirst.setImageResource(R.drawable.ic_vector_blue_line);
                            mDataBinding.statusSecond.setBackgroundResource(R.drawable.bg_blue_round);
                            mDataBinding.statusSecond.setTextColor(ContextCompat.getColor(mContext, R.color.white_background));
                            break;
                        case 3:
                            mDataBinding.statusSecond.setTextColor(ContextCompat.getColor(mContext, R.color.white_background));
                            mDataBinding.statusThird.setTextColor(ContextCompat.getColor(mContext, R.color.white_background));
                            mDataBinding.ivFirst.setImageResource(R.drawable.ic_vector_blue_line);
                            mDataBinding.statusSecond.setBackgroundResource(R.drawable.bg_blue_round);
                            mDataBinding.statusThird.setBackgroundResource(R.drawable.bg_blue_round);
                            mDataBinding.ivSecond.setImageResource(R.drawable.ic_vector_blue_line);
                            break;
                        default:
                            break;
                    }
                }
            }
        }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_audit;
    }
}
