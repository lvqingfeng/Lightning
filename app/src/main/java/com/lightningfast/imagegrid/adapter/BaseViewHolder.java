package com.lightningfast.imagegrid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者：lv
 * 创建时间：08月01日
 * 时间：14:45
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */
public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private OnItemClickListener mListener;


    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.onClick(this, v);
        }
    }

    public interface OnItemClickListener {
        void onClick(RecyclerView.ViewHolder vh, View v);

        void onClickCamera(RecyclerView.ViewHolder vh, View v);
    }

}
