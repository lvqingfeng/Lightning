package com.lightningfast.ui.manager.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.lightningfast.bean.OverdueBean;
import com.lightningfast.databinding.FragmentOverdueBinding;
import com.lightningfast.recycler_listener.OnRecyclerItemClickListener;
import com.lightningfast.ui.manager.adapter.OverdueAdapter;
import com.lightningfast.uitls.DialogUtils;
import com.lightningfast.uitls.EmployeeLoginHelper;
import com.lightningfast.view.DividerGridItemDecoration;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.functions.Action1;

/**
 * 作者:吕清锋
 * 时间:2017/8/24
 * 版本:v1.0
 * 类描述：逾期客户
 * 修改时间:
 */

public class OverdueFragment extends LazyFragment<FragmentOverdueBinding> implements SpringView.OnFreshListener{
    private boolean isEnd=true;
    private List<OverdueBean.DataBean.CustomerInfoBean> mList;
    private OverdueAdapter overdueAdapter;

    public static OverdueFragment newInstance() {
        OverdueFragment fragment = new OverdueFragment();
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
        overdueAdapter = new OverdueAdapter(mList);
        mDataBinding.recyclerView.setAdapter(overdueAdapter);

        mDataBinding.recyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mDataBinding.recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                call(mList.get(vh.getLayoutPosition()).getMobile());
            }
        });
    }
    private void call(final String tell){
        RxPermissions.getInstance(mContext).request(Manifest.permission.CALL_PHONE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            DialogUtils.alertDialog(mContext, "温馨提示", "您是否要联系客户?", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();

                                }
                            }, new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismiss();
                                    //用intent启动拨打电话
                                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tell));

                                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling
                                        //    ActivityCompat#requestPermissions
                                        // here to request the missing permissions, and then overriding
                                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                        //                                          int[] grantResults)
                                        // to handle the case where the user grants the permission. See the documentation
                                        // for ActivityCompat#requestPermissions for more details.
                                        Toast.makeText(mContext, "权限被拒绝", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    startActivity(intent);
                                }
                            });
                        } else {
                            Toast.makeText(mContext, "拨号权限被禁用", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_overdue;
    }

    @Override
    protected void loadData() {
        initLoadData();
    }

    @Override
    protected void loadListData(int page) {
        RetrofitManager.toSubscribe(ApiClient.getApiService(ApiService.class, RetrofitManager.RetrofitType.Object)
                .updateoverdueOrderList(EmployeeLoginHelper.getInstance().getUserId(),EmployeeLoginHelper.getInstance().getUserToken(),String.valueOf(page)),new ProgressSubscriber<OverdueBean>(mContext, new SubscriberOnNextListener<OverdueBean>() {
            @Override
            public void onNext(OverdueBean result) {
                if (result.getCode()==1){
                    isEnd=result.getData().getIs_nums()==0;
                    mList.addAll(result.getData().getCustomerInfo());
                    overdueAdapter.notifyDataSetChanged();
                    mDataBinding.springView.onFinishFreshAndLoad();
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
    public void onRefresh() {
        onRefresh(isEnd);
    }

    @Override
    public void onLoadmore() {
        onLoadMore(isEnd);
    }
}
