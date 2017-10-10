package com.lightningfast.ui.manager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightningfast.R;
import com.lightningfast.base.BindingViewHolder;
import com.lightningfast.bean.OverdueBean;
import com.lightningfast.databinding.ItemLayoutOverdueBinding;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/25
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class OverdueAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemLayoutOverdueBinding>> {
    private List<OverdueBean.DataBean.CustomerInfoBean> mList;

    public OverdueAdapter(List<OverdueBean.DataBean.CustomerInfoBean> mList) {
        this.mList = mList;
    }

    @Override
    public BindingViewHolder<ItemLayoutOverdueBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_overdue,parent,false);
        return new BindingViewHolder<>(ItemLayoutOverdueBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ItemLayoutOverdueBinding> holder, int position) {
        OverdueBean.DataBean.CustomerInfoBean dataBean = mList.get(position);
        holder.getBinding().name.setText(dataBean.getCustomer_name());
        holder.getBinding().mobile.setText(dataBean.getMobile());
        holder.getBinding().money.setText("逾期金额"+dataBean.getOverPrice()+"元");
        holder.getBinding().qishu.setText("已逾期"+dataBean.getOverdue_nums()+"期");
        holder.getBinding().time.setText(dataBean.getCreate_time());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
