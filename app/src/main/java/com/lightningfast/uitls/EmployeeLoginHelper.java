package com.lightningfast.uitls;

import android.content.Context;

import com.lightningfast.bean.Employee;

/**
 * 作者:吕清锋
 * 时间:2017/8/19
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class EmployeeLoginHelper {
    private static EmployeeLoginHelper sInstance;
    private Context mContext;
    private Employee mUserBean;
    private boolean isOnline;

    private EmployeeLoginHelper() {
    }

    public static EmployeeLoginHelper getInstance() {
        if (sInstance == null) {
            sInstance = new EmployeeLoginHelper();
        }
        return sInstance;
    }

    public EmployeeLoginHelper init(Context context) {
        mContext = context;
        isOnline = checkIsOnline();
        return this;
    }

    public boolean checkIsOnline() {
        boolean isOnline;
        Employee user = EmployeeUtils.getUser(mContext);
        System.out.println("---获取到的用户的key--" + user.getToken());
        if (user.getToken() != null) {
            isOnline = true;
            System.out.println("--用户id为" + user.getToken());
        } else {
            System.out.println("--用户对象为空");
            isOnline = false;
        }

        if (isOnline) {
            mUserBean = user;
        }
        return isOnline;
    }


    public boolean userExit() {
        mUserBean = null;
        return UserUtils.quit(mContext);
    }

    public Employee getUserBean() {
        return mUserBean;
    }

    public String getUserToken() {
        if (mUserBean != null)
            return mUserBean.getToken();
        return "";
    }
    public String getUserId(){
        if (mUserBean != null)
            return mUserBean.getUid();
        return "";
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        if (online) {
            mUserBean = EmployeeUtils.getUser(mContext);
        } else {
            userExit();
        }
        isOnline = online;
    }

}
