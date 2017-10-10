package com.lightningfast.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightningfast.R;
import com.lightningfast.base.BindingViewHolder;
import com.lightningfast.bean.RepaymentDetailsBean;
import com.lightningfast.databinding.ItemLayoutRepaymentDetailsBinding;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/3
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class RepaymentDetailsAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemLayoutRepaymentDetailsBinding>>{
    private List<RepaymentDetailsBean.DataBean.DetailBean> mList;

    public RepaymentDetailsAdapter(List<RepaymentDetailsBean.DataBean.DetailBean> mList) {
        this.mList = mList;
    }

    @Override
    public BindingViewHolder<ItemLayoutRepaymentDetailsBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_repayment_details,parent,false);
        return new BindingViewHolder<>(ItemLayoutRepaymentDetailsBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ItemLayoutRepaymentDetailsBinding> holder, int position) {
        RepaymentDetailsBean.DataBean.DetailBean detailBean = mList.get(position);
        holder.getBinding().date.setText(detailBean.getNow_number()+"期"+"\n"+detailBean.getRepayment_time());
        holder.getBinding().money.setText(detailBean.getNeed_price());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
