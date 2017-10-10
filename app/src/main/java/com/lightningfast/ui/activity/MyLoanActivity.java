package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lightningfast.R;
import com.lightningfast.api.ApiClient;
import com.lightningfast.api.ApiService;
import com.lightningfast.api.ProgressSubscriber;
import com.lightningfast.api.RetrofitManager;
import com.lightningfast.api.SubscriberOnNextListener;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.bean.MyLoanBean;
import com.lightningfast.bean.MyLoanTotalBean;
import com.lightningfast.databinding.ActivityMyLoanBinding;
import com.lightningfast.recycler_listener.OnRecyclerItemClickListener;
import com.lightningfast.ui.adapter.MyLoanAdapter;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/***
 * 我的借款
 * */
public class MyLoanActivity extends BaseActivity<ActivityMyLoanBinding> implements SpringView.OnFreshListener {
    private boolean isEnd;
    private List<MyLoanBean.DataBean.OrderInfoBean> mList;
    private MyLoanAdapter myLoanAdapter;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, MyLoanActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {

    }


    @Override
    protected void initViews() {
        mList = new ArrayList<>();
        initData();
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        myLoanAdapter = new MyLoanAdapter(mList);
        mDataBinding.recyclerView.setAdapter(myLoanAdapter);
        setListData(mList);
        initLoadData();
        mDataBinding.recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mDataBinding.recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                int past_number = mList.get(vh.getLayoutPosition()).getPast_number();
                String id = String.valueOf(mList.get(vh.getLayoutPosition()).getId());
                if (past_number <= 0) {
                    Toast.makeText(mContext, "暂无还款记录...", Toast.LENGTH_SHORT).show();
                    return;
                }
                RepaymentDetailsActivity.actionStart(MyLoanActivity.this, id);
            }
        });
    }

    private void initData() {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object).updateMyLoanTotal(LoginHelper
                .getInstance().getUserId(), LoginHelper.getInstance().getUserToken()), new Subscriber<MyLoanTotalBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MyLoanTotalBean result) {
                mDataBinding.moneyTotal.setText(result.getData().getTotal_payment());
                if (TextUtils.isEmpty(String.valueOf(result.getData().getTotal_past_payment()))) {
                    mDataBinding.moneyRepayment.setText("0");
                } else {
                    mDataBinding.moneyRepayment.setText(result.getData().getTotal_past_payment());
                }
            }
        });
    }

    private void initBaseData(int page) {

        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object)
                .updateMyorder(LoginHelper.getInstance().getUserId(), LoginHelper.getInstance().getUserToken(), page), new ProgressSubscriber<>(mContext
                , new SubscriberOnNextListener<MyLoanBean>() {
            @Override
            public void onNext(MyLoanBean result) {
                if (result.getCode() == 1) {
                    isEnd = result.getData().getIs_nums() == 0;
                    mList.clear();
                    mList.addAll(result.getData().getOrder_info());
                    myLoanAdapter.notifyDataSetChanged();
                    mDataBinding.springView.onFinishFreshAndLoad();
                } else if (result.getCode() == 2) {
                    toLogin();
                } else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                    mDataBinding.springView.onFinishFreshAndLoad();
                }
            }
        }));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_loan;
    }

    @Override
    public ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected void loadListData(int page) {
        initBaseData(page);
    }

    @Override
    public void onRefresh() {
        onRefresh(isEnd);
    }

    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }
}
