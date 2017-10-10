package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.BaseBean;
import com.lightningfast.bean.MoneyBean;
import com.lightningfast.bean.RepaymentBean;
import com.lightningfast.bean.RepaymentResultBean;
import com.lightningfast.databinding.ActivityRepaymentBinding;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.SelectMenuPopWindow;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.Subscriber;


public class RepaymentActivity extends BaseActivity<ActivityRepaymentBinding> {

    private boolean isClick = false;
    private SelectMenuPopWindow popWindow;
    private String periods = "1";
    private String order_id;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, RepaymentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        initPop();
        initRepayment();
    }

    private void initPop() {
        popWindow = new SelectMenuPopWindow(this);
        popWindow.setOnClickSelectListener(new SelectMenuPopWindow.OnClickSelectListener() {
            @Override
            public void onClickSelectItem(String itemName, int position) {
                mDataBinding.periods.setText(itemName);
                periods = "" + (position + 1);
            }
        });
        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

        mDataBinding.repayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isClick) {
                    popWindow.showPopupWindow(mDataBinding.getRoot());
                }
            }
        });
    }

    private void initMoneys(final String id) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .getMoneyData(order_id, id, LoginHelper.getInstance().getUserToken()), new Subscriber<MoneyBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final MoneyBean moneyBean) {
                if (moneyBean.getCode() == 1) {
                    DialogUtils.alertDialogNormal(mContext, "温馨提示", "您当前选择还款" + id + "期,合计" + moneyBean.getData().getRepay_price() + "元,是否确认还款?", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    }, new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            Repayment(id, moneyBean.getData().getRepay_price());
                        }
                    });
                } else if (moneyBean.getCode() == 2) {
                    toLogin();
                }
            }
        });
    }

    private void Repayment(String id, String money) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                        .confirmRepayment(order_id, id, money, LoginHelper.getInstance().getUserId(), LoginHelper.getInstance().getUserToken())
                , new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<BaseBean>() {
                    @Override
                    public void onNext(BaseBean result) {
                        if (result.getCode() == 1) {
                            RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                                    , RetrofitManager.RetrofitType.Object).getRepaymentResult(LoginHelper
                                    .getInstance().getUserId(), LoginHelper.getInstance().getUserToken(), order_id), new Subscriber<RepaymentResultBean>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(RepaymentResultBean result) {
                                    if (result.getCode() == 1) {
                                        switch (result.getData().getStatus()) {
                                            case 0:
                                                RepaymentResultActivity.actionStart(RepaymentActivity.this, order_id);
                                                Toast.makeText(RepaymentActivity.this, result.getData().getFail_reason(), Toast.LENGTH_SHORT).show();
                                                RepaymentActivity.this.finish();
                                                break;
                                            case -1:
                                                Toast.makeText(RepaymentActivity.this, result.getData().getFail_reason(), Toast.LENGTH_SHORT).show();
                                                RepaymentActivity.this.finish();
                                                break;
                                            case 1:
                                                Toast.makeText(RepaymentActivity.this, result.getData().getFail_reason(), Toast.LENGTH_SHORT).show();
                                                RepaymentActivity.this.finish();
                                                break;
                                        }
                                    }
                                }
                            });
                        } else if (result.getCode() == 2) {
                            toLogin();
                        }else if (result.getCode()==3){
                            Toast.makeText(mContext, "还款处理中，请稍后...", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(mContext, "还款失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }));
    }

    private void initRepayment() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                        .updateRepayment(LoginHelper.getInstance().getUserId())
                , new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<RepaymentBean>() {
                    @Override
                    public void onNext(RepaymentBean result) {
                        if (result.getCode() == 1) {
                            order_id = result.getData().getOrder_id();
                            mDataBinding.moneyTotal.setText(result.getData().getRemainder() + "/" + result.getData().getTotal_payment());
                            String fenqi_remainder = result.getData().getFenqi_remainder();
                            mDataBinding.moneyRepayment.setText(fenqi_remainder + "/" + result.getData().getFenqi_nums());
                            if (Integer.parseInt(fenqi_remainder) > 0) {
                                isClick = true;
                                List<String> mList = new ArrayList<>();
                                for (int i = 0; i < Integer.parseInt(fenqi_remainder); i++) {
                                    mList.add((i + 1) + "期");
                                }
                                popWindow.bindingList(mList);
                            }
                        } else if (result.getCode() == 2) {
                            toLogin();
                        }
                    }
                }));
    }

    @Override
    protected void initViews() {
        mDataBinding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initMoneys(periods);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repayment;
    }
}
