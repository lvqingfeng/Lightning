package com.lightningfast.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightningfast.R;
import com.lightningfast.base.BindingViewHolder;
import com.lightningfast.bean.EmployeeBean;
import com.lightningfast.databinding.ItemLayoutEmployeeBinding;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/4
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class EmployeeAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemLayoutEmployeeBinding>>{
    private List<EmployeeBean.DataBean.EmpInfoBean>  mList;

    public EmployeeAdapter(List<EmployeeBean.DataBean.EmpInfoBean> mList) {
        this.mList = mList;
    }

    @Override
    public BindingViewHolder<ItemLayoutEmployeeBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout_employee,parent,false);
        return new BindingViewHolder<>(ItemLayoutEmployeeBinding.bind(rootView));
    }

    @Override
    public void onBindViewHolder(BindingViewHolder<ItemLayoutEmployeeBinding> holder, int position) {
        EmployeeBean.DataBean.EmpInfoBean bean = mList.get(position);
        holder.getBinding().employee.setText(bean.getEmployee_name());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
