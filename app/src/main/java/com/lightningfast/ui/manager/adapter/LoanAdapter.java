package com.lightningfast.ui.manager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightningfast.R;
import com.lightningfast.base.BindingViewHolder;
import com.lightningfast.bean.LoanBean;
import com.lightningfast.databinding.ItemLayoutLoanBinding;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/25
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class LoanAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemLayoutLoanBinding>> {
    private List<LoanBean.DataBean.CustomerInfoBean> mList;

    public LoanAdapter(List<LoanBean.DataBean.CustomerInfoBean> mList) {
        this.mList = mList;
    }

    @Override
    public BindingViewHolder<ItemLayoutLoanBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_loan,parent,false);
        return new BindingViewHolder<>(ItemLayoutLoanBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ItemLayoutLoanBinding> holder, int position) {
        LoanBean.DataBean.CustomerInfoBean bean = mList.get(position);
        holder.getBinding().name.setText(bean.getCustomer_name());
        holder.getBinding().mobile.setText(bean.getMobile());
        holder.getBinding().time.setText(bean.getCreate_time());
        holder.getBinding().qishu.setText(bean.getPast_number()+"/"+bean.getFenqi_number()+"期");
        holder.getBinding().money.setText(bean.getRepayment_total()+"/"+bean.getNeed_price()+"元");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
