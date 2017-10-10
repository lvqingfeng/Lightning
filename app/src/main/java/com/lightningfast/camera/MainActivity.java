package com.lightningfast.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 这是一个简单例子可以根据这个编写
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnAlbum;
    private Button mBtnCamera;
    private ImageView mIvResultPhoto;

    private SinglePictureSelectHelper mSinglePictureSelectHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//在调用之前一定要先获取实例，并调用init方法
        mSinglePictureSelectHelper = SinglePictureSelectHelper.getInstance().init(this);
        initViewId();

        mBtnAlbum.setOnClickListener(this);
        mBtnCamera.setOnClickListener(this);

    }

    private void initViewId() {
        //初始化按钮的id;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imagePath = mSinglePictureSelectHelper.onActivityResult(requestCode, resultCode, data, mIvResultPhoto,false);
        System.out.println("---- 图片的地址 ---" + imagePath);
    }

    @Override
    public void onClick(View v) {
      //  switch (v.getId()) {
           // case R.id.btn_album:
                mSinglePictureSelectHelper.openAlbum();
                //break;
         //   case R.id.btn_camera:
                //openCamera();
                mSinglePictureSelectHelper.openCameraForFile();
           //     break;
     //   }

    }

}
