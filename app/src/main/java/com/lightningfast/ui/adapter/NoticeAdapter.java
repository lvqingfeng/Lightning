package com.lightningfast.ui.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lightningfast.R;
import com.lightningfast.base.BindingViewHolder;
import com.lightningfast.bean.NoticeBean;
import com.lightningfast.databinding.ItemLayoutNoticeBinding;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/18
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class NoticeAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemLayoutNoticeBinding>>{
    private List<NoticeBean.DataBean.ListBean> mList;
    private SparseBooleanArray mCollapsedStatus;

    public NoticeAdapter(List<NoticeBean.DataBean.ListBean> mList) {
        this.mList = mList;
        mCollapsedStatus=new SparseBooleanArray();
    }

    @Override
    public BindingViewHolder<ItemLayoutNoticeBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_notice,parent,false);
        return new BindingViewHolder<>(ItemLayoutNoticeBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(final BindingViewHolder<ItemLayoutNoticeBinding> holder, int position) {
        NoticeBean.DataBean.ListBean listBean = mList.get(position);
        holder.getBinding().tvMessage.setText(listBean.getContent(),mCollapsedStatus,position);
        holder.getBinding().time.setText(listBean.getCreate_time());
        if (listBean.getIs_read()==1){
            holder.getBinding().isRead.setText("已读");
            holder.getBinding().isRead.setTextColor(ContextCompat.getColor(holder.mContext,R.color.gray_background));
        }else {
            holder.getBinding().isRead.setText("标为已读?");
            holder.getBinding().isRead.setTextColor(ContextCompat.getColor(holder.mContext,R.color.BaseColor));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
