package com.lightningfast.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightningfast.R;
import com.lightningfast.base.BindingViewHolder;
import com.lightningfast.bean.MyLoanBean;
import com.lightningfast.databinding.ItemLayoutMyLoanBinding;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/7/29
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class MyLoanAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemLayoutMyLoanBinding>> {
    private List<MyLoanBean.DataBean.OrderInfoBean>  mList;

    public MyLoanAdapter(List<MyLoanBean.DataBean.OrderInfoBean> mList) {
        this.mList = mList;
    }

    @Override
    public BindingViewHolder<ItemLayoutMyLoanBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_my_loan,parent,false);
        return new BindingViewHolder<>(ItemLayoutMyLoanBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ItemLayoutMyLoanBinding> holder, int position) {
        MyLoanBean.DataBean.OrderInfoBean bean = mList.get(position);
        if (bean.getOrder_sn()!=null){
            holder.getBinding().order.setText(bean.getOrder_sn()+"\n"+bean.getCreate_time());
        }else {
            holder.getBinding().order.setText("0"+"\n"+bean.getCreate_time());
        }
        holder.getBinding().date.setText(bean.getPast_number()+"/"+bean.getTotal_number());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
