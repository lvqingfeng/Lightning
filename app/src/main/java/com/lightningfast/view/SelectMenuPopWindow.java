package com.lightningfast.view;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.aigestudio.wheelpicker.core.AbstractWheelPicker;
import com.aigestudio.wheelpicker.view.WheelCurvedPicker;
import com.lightningfast.R;

import java.util.List;



/**
 * 作者：lv
 * 类描述：菜单选择的PopWindow,适用只需要对某些单个功能选择
 * <p>
 * ******************使用方式***************
 * 1.实例化当的PopWindow对象
 * 2.将数据与当前的PopWindow进行数据绑定{@link #bindingList(List)}数据为{@link String}类型的集合
 * 3.通过{@link #setOnClickSelectListener(OnClickSelectListener)}方法设置监听器，监听选择的内容
 * 修改时间：
 */

public class SelectMenuPopWindow extends AddPopWindow {

    private WheelCurvedPicker mSelectMenu;
    private String mSelectName;
    private OnClickSelectListener mSelectListener;
    private int position;
    /**
     * 初始化一个PopupWindow
     * @param context 上下文对象
     */
    public SelectMenuPopWindow(Activity context) {
        super(context, R.layout.layout_pop_select_menu);
        mSelectMenu = (WheelCurvedPicker) getWindowRootView().findViewById(R.id.wheel_menu);
        mSelectMenu.setTextSize(18);
        mSelectMenu.setItemCount(10);

        mSelectMenu.setOnWheelChangeListener(new AbstractWheelPicker.OnWheelChangeListener() {


            @Override
            public void onWheelScrolling(float deltaX, float deltaY) {

            }

            @Override
            public void onWheelSelected(int index, String data) {
                position=index;
                mSelectName = data;
            }

            @Override
            public void onWheelScrollStateChanged(int state) {

            }
        });
        TextView tvCancel = (TextView) getWindowRootView().findViewById(R.id.tv_cancel);
        TextView tvConfirm = (TextView) getWindowRootView().findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectMenuPopWindow.this.dismiss();
                mSelectListener.onClickSelectItem(mSelectName,position);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectMenuPopWindow.this.dismiss();
            }
        });

    }

    public SelectMenuPopWindow bindingList(List<String> menu) {
        mSelectMenu.setData(menu);
        return this;
    }

    public SelectMenuPopWindow setOnClickSelectListener(final OnClickSelectListener listener) {
        mSelectListener = listener;
        return this;
    }

    public interface OnClickSelectListener {
        void onClickSelectItem(String itemName,int position);
    }
}
