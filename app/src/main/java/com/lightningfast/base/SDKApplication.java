package com.lightningfast.base;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import com.jingewenku.abrahamcaijin.commonutil.application.AppUtils;
import com.lightningfast.camera.FileUtils;
import com.lightningfast.imagegrid.bean.ImageBucket;
import com.lightningfast.imagegrid.utils.AlbumHelper;
import com.lightningfast.uitls.EmployeeLoginHelper;
import com.lightningfast.uitls.LoginHelper;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by Administrator on 2017/7/5.
 */

public class SDKApplication extends Application {
    public static int sWindowWidth;
    public static int sWindowHeight;
    private static SDKApplication instance;

    private List<ImageBucket> mImageBucket;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        initWindowSize();
        AppUtils.init(instance);
    }

    private void init() {
        //初始化获取文件存储位置的工具类
        RxPermissions.getInstance(this).request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean){
                            try {
                                FileUtils.getInstance().init(SDKApplication.this);
                                LoginHelper.getInstance().init(instance);
                                EmployeeLoginHelper.getInstance().init(instance);
                                initPhoto();
                            } catch (Exception e) {
                                Toast.makeText(SDKApplication.this, "请前往设置中心打开权限", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }else {
                            Toast.makeText(SDKApplication.this, "请前往设置中心打开权限", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
    }

    public static SDKApplication getApplication(){
        return instance;
    }
    private void initWindowSize() {

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        sWindowWidth = metrics.widthPixels;
        sWindowHeight = metrics.heightPixels;

    }
    private void initPhoto() {
        AlbumHelper helper = AlbumHelper.getHelper();
        helper.init(this);
        mImageBucket = helper.getImagesBucketList(false);
    }
    public List<ImageBucket> getImageBucket() {
        if (mImageBucket == null)
            return new ArrayList<>();
        return mImageBucket;
    }
}
