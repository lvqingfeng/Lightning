package com.lightningfast.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lightningfast.R;
import com.lightningfast.base.BaseActivity;
import com.lightningfast.databinding.ActivityCameraBinding;

public class CameraActivity extends BaseActivity<ActivityCameraBinding> {
    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, CameraActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setupTitle() {
//        openTitleLeftView(true);
//        setTextTitleView("相机",DEFAULT_TITLE_TEXT_COLOR);
    }

    @Override
    protected void initViews() {
        mDataBinding.takePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDataBinding.llCamera.takePicture();
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera;
    }
}
