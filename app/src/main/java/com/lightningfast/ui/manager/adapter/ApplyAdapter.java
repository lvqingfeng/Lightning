package com.lightningfast.ui.manager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightningfast.R;
import com.lightningfast.base.BindingViewHolder;
import com.lightningfast.bean.ApplyBean;
import com.lightningfast.databinding.ItemLayoutApplyBinding;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/25
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class ApplyAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemLayoutApplyBinding>> {
    private List<ApplyBean.DataBean.CustomerInfoBean> mList;

    public ApplyAdapter(List<ApplyBean.DataBean.CustomerInfoBean> mList) {
        this.mList = mList;
    }

    @Override
    public BindingViewHolder<ItemLayoutApplyBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_apply,parent,false);
        return new BindingViewHolder<>(ItemLayoutApplyBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ItemLayoutApplyBinding> holder, int position) {
        ApplyBean.DataBean.CustomerInfoBean customerInfoBean = mList.get(position);
        holder.getBinding().name.setText(customerInfoBean.getCustomer_name());
        holder.getBinding().mobile.setText(customerInfoBean.getMobile());
        holder.getBinding().money.setText(customerInfoBean.getNeed_price());
        holder.getBinding().time.setText(customerInfoBean.getCreate_time());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
