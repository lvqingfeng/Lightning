package com.lightningfast.ui.manager.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.lightningfast.bean.ApplyBean;
import com.lightningfast.databinding.FragmentApplyBinding;
import com.lightningfast.recycler_listener.OnRecyclerItemClickListener;
import com.lightningfast.ui.manager.activity.AddUserActivity;
import com.lightningfast.ui.manager.adapter.ApplyAdapter;
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

public class ApplyFragment extends LazyFragment<FragmentApplyBinding>
        implements SpringView.OnFreshListener {
    private boolean isEnd;
    private List<ApplyBean.DataBean.CustomerInfoBean> mList;
    private ApplyAdapter applyAdapter;

    public static ApplyFragment newInstance() {
        ApplyFragment fragment = new ApplyFragment();
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
        mList=new ArrayList<>();
        mDataBinding.springView.setHeader(new DefaultHeader(mContext));
        mDataBinding.springView.setFooter(new DefaultFooter(mContext));
        mDataBinding.springView.setListener(this);
        mDataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDataBinding.recyclerView.addItemDecoration(new DividerGridItemDecoration(mContext));
        setListData(mList);
        applyAdapter = new ApplyAdapter(mList);
        mDataBinding.recyclerView.setAdapter(applyAdapter);


        mDataBinding.recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mDataBinding.recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                String customerId = mList.get(vh.getLayoutPosition()).getCustomer_id();
                String orderId = mList.get(vh.getLayoutPosition()).getOrder_id();
                AddUserActivity.actionStart(getActivity(),customerId,orderId);
            }
        });
    }

    @Override
    protected ViewGroup getRefreshView() {
        return mDataBinding.springView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_apply;
    }

    @Override
    protected void loadData() {
        initLoadData();
    }

    @Override
    protected void loadListData(int page) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .updateCustomerApplyList(EmployeeLoginHelper.getInstance().getUserId(),EmployeeLoginHelper.getInstance().getUserToken(),String.valueOf(page)),new ProgressSubscriber<ApplyBean>(mContext, new SubscriberOnNextListener<ApplyBean>() {
            @Override
            public void onNext(ApplyBean result) {
                if (result.getCode()==1){
                    isEnd=result.getData().getIs_nums()==0;
                    mList.addAll(result.getData().getCustomerInfo());
                    applyAdapter.notifyDataSetChanged();
                    mDataBinding.springView.onFinishFreshAndLoad();
                }else {
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
