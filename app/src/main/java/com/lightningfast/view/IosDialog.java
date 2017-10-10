package com.lightningfast.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


import com.lightningfast.R;



/**
 * 作者：lv清锋
 * 创建时间：10月29日
 * 时间：19:21
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */

public class IosDialog extends Dialog {

    private TextView mTvTitle;
    private TextView mTvMessage;
    private TextView mTvCancel;
    private TextView mTvConfirm;

    private CharSequence mTitleText;
    private CharSequence mMessageText;
    private String mCancelText;
    private String mConfirmText;

    private OnClickListener mCancelListener;
    private OnClickListener mConfirmListener;

    public IosDialog(Context context) {
        super(context);
        setCancelable(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog_ios);

        mTvTitle = (TextView) findViewById(R.id.tv_dialog_title);
        mTvMessage = (TextView) findViewById(R.id.tv_dialog_message);
        mTvCancel = (TextView) findViewById(R.id.tv_dialog_cancel);
        mTvConfirm = (TextView) findViewById(R.id.tv_dialog_confirm);

        setTitleText(mTitleText);
        setMessageText(mMessageText);
        setCancelListener(mCancelText, mCancelListener);
        setConfirmListener(mConfirmText, mConfirmListener);
    }


    public IosDialog setTitleText(CharSequence title) {
        mTitleText = title;
        if (mTitleText != null && mTvTitle != null) {
            mTvTitle.setText(title);
        }
        return this;
    }

    public IosDialog setMessageText(CharSequence message) {
        mMessageText = message;
        if (mMessageText != null && mTvMessage != null) {
            mTvMessage.setText(message);
        }
        return this;
    }

    public IosDialog setCancelListener(String cancelText, OnClickListener clickListener) {
        if (TextUtils.isEmpty(cancelText))
            cancelText = "取消";
        mCancelListener = clickListener;
        mCancelText = cancelText;
        if (mTvCancel != null && mCancelListener != null) {
            mTvCancel.setText(cancelText);
            mTvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCancelListener.onClick(IosDialog.this);
                }
            });
        }


        return this;
    }

    public IosDialog setConfirmListener(String confirmText, OnClickListener clickListener) {
        if (TextUtils.isEmpty(confirmText))
            confirmText = "确认";
        mConfirmText = confirmText;
        mConfirmListener = clickListener;
        if (mTvConfirm != null && mConfirmListener != null) {
            mTvConfirm.setText(confirmText);
            mTvConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mConfirmListener.onClick(IosDialog.this);
                }
            });
        }
        return this;
    }

    public interface OnClickListener {
        void onClick(IosDialog dialog);
    }
}
