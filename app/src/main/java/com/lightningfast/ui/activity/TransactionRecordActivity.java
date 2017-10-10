package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.databinding.ActivityTransactionRecordBinding;
import com.lightningfast.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;
/***
 * 交易记录
 * */
public class TransactionRecordActivity extends BaseActivity<ActivityTransactionRecordBinding>
implements SpringView.OnFreshListener{

    private boolean isEnd=true;
    private List<String> mList;
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, TransactionRecordActivity.class);
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
        mList=new ArrayList<>();
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));

        setListData(mList);
        initLoadData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transaction_record;
    }

    @Override
    protected void loadListData(int page) {

    }

    @Override
    public ViewGroup getRefreshView() {
        return mDataBinding.springView;
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
