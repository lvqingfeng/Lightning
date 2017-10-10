package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.lightningfast.bean.RepaymentDetailsBean;
import com.lightningfast.databinding.ActivityRepaymentDetailsBinding;
import com.lightningfast.ui.adapter.RepaymentDetailsAdapter;
import com.lightningfast.uitls.LoginHelper;
import com.lightningfast.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RepaymentDetailsActivity extends BaseActivity<ActivityRepaymentDetailsBinding>
implements SpringView.OnFreshListener{
    private String id;
    private List<RepaymentDetailsBean.DataBean.DetailBean> mList;
    private RepaymentDetailsAdapter mAdapter;
    private boolean isEnd=false;
    public static void actionStart(Activity activity,String id) {
        Intent intent = new Intent(activity, RepaymentDetailsActivity.class);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
        openTitleLeftView(true);
        setTextTitleView("订单详情",DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        mList=new ArrayList<>();
        id=getIntent().getStringExtra("id");
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        mAdapter = new RepaymentDetailsAdapter(mList);
        mDataBinding.recyclerView.setAdapter(mAdapter);
        setListData(mList);
        initLoadData();
    }

    @Override
    protected void loadListData(final int page) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .getRepaymentDetails(LoginHelper.getInstance().getUserId(),id,LoginHelper.getInstance()
                        .getUserToken()),new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<RepaymentDetailsBean>() {
            @Override
            public void onNext(RepaymentDetailsBean result) {
                if (result.getCode()==1){
                    isEnd=page==1;
                    mList.addAll(result.getData().getDetail());
                    mAdapter.notifyDataSetChanged();
                    mDataBinding.springView.onFinishFreshAndLoad();
                }else if (result.getCode()==2){
                    toLogin();
                }else {
                    Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                    mDataBinding.springView.onFinishFreshAndLoad();
                }
            }
        }));
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repayment_details;
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
