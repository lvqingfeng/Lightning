package com.lightningfast.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lightningfast.R;
import com.lightningfast.uitls.StatusbarColorUtils;


/**
 * Created by 崔浩 on 2017/3/18.
 */

public class BackView extends RelativeLayout {

    private TextView tv;
    private ImageView iv;
    private View head;
    private int height;
    private boolean isCustomer;
    private View v;

    public BackView(Context context) {
        this(context,null);
    }

    public BackView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BackView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        v = View.inflate(context, R.layout.view_backview,this);
        tv = (TextView) v.findViewById(R.id.view_backview_tv);
        iv = (ImageView) v.findViewById(R.id.view_backview_iv);
        head= v.findViewById(R.id.view_backview_head);
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finish();
            }
        });
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.BackView);
        String title=array.getString(R.styleable.BackView_text);
        int color=array.getColor(R.styleable.BackView_color,Color.WHITE);
        int backColor=array.getColor(R.styleable.BackView_backColor,Color.WHITE);
        tv.setTextColor(color);
        tv.setText(title);
        v.setBackgroundColor(backColor);
        head.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                if (isCustomer){
                    head.getLayoutParams().height= height;
                }else {
                    head.getLayoutParams().height= StatusbarColorUtils.getStatusHeight((Activity) getContext());
                }
                head.requestLayout();
                head.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

   
    public void setHeight(int height){
        this.height =height;
        isCustomer=true;
    }
    
    public void setTitleColor(int color){
        v.setBackgroundColor(color);
    }
    public void setTitleIMG(int id){
        iv.setImageResource(id);
    }
    public void setTitleText(String title){
        tv.setText(title);
    }
    public void setTitleTextColor(int color){
        tv.setTextColor(color);
    }
}
