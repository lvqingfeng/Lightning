package com.lightningfast.ui.manager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
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
import com.lightningfast.base.LazyFragment;
import com.lightningfast.bean.LoanBean;
import com.lightningfast.databinding.FragmentLoanBinding;
import com.lightningfast.ui.manager.adapter.LoanAdapter;
import com.lightningfast.uitls.EmployeeLoginHelper;
import com.lightningfast.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/25
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class LoanFragment extends LazyFragment<FragmentLoanBinding>
implements SpringView.OnFreshListener{
    private boolean isEnd=true;
    private List<LoanBean.DataBean.CustomerInfoBean> mList;
    private LoanAdapter loanAdapter;
    public static LoanFragment newInstance() {
        LoanFragment fragment = new LoanFragment();
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
        mList = new ArrayList<>();
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        setListData(mList);
        loanAdapter = new LoanAdapter(mList);
        mDataBinding.recyclerView.setAdapter(loanAdapter);
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_loan;
    }

    @Override
    protected void loadData() {
        initLoadData();
    }

    @Override
    protected void loadListData(int page) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class
                , RetrofitManager.RetrofitType.Object)
                        .updateCustomerRepayList(EmployeeLoginHelper.getInstance().getUserId()
                                ,EmployeeLoginHelper.getInstance().getUserToken(), String.valueOf(page))
                , new ProgressSubscriber<>(mContext, new SubscriberOnNextListener<LoanBean>() {
                    @Override
                    public void onNext(LoanBean result) {
                        if (result.getCode() == 1) {
                            mList.clear();
                            isEnd = result.getData().getIs_nums() == 0;
                            mList.addAll(result.getData().getCustomerInfo());
                            loanAdapter.notifyDataSetChanged();
                            mDataBinding.springView.onFinishFreshAndLoad();
                        } else {
                            Toast.makeText(mContext, result.getMsg(), Toast.LENGTH_SHORT).show();
                            mDataBinding.springView.onFinishFreshAndLoad();
                        }
                    }
                }));
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
