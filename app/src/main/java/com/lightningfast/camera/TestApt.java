package com.lightningfast.camera;

import android.app.Application;

/**
 * 作者：吕振鹏
 * 创建时间：10月11日
 * 时间：15:04
 * 版本：v1.0.0
 * 类描述：
 * 修改时间：
 */

public class TestApt extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FileUtils.getInstance().init(this);
    }
}
