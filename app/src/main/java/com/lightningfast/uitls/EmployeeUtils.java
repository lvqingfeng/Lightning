package com.lightningfast.uitls;

import android.content.Context;
import android.content.SharedPreferences;

import com.lightningfast.bean.Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 作者:吕清锋
 * 时间:2017/8/19
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class EmployeeUtils {
    private static final String SHARED_NAME = "employee_Shared";
    private static final String SHARE_PASSWORD = "employee_password";
    private static final String FILE_NAME = "sdkEmployee.txt";

    /**
     * 保存user到本地文件
     *
     * @param context
     * @param user
     */
    public static boolean saveUser(Context context, Employee user) {

        //先判断该对象是否为空，不为空时直接删除
        if (getUser(context) != null) {
            quit(context);
        }
        try {
            FileOutputStream fos = new FileOutputStream(context.getFilesDir()
                    + "/" + FILE_NAME);
            ObjectOutputStream os = new ObjectOutputStream(fos);

            os.writeObject(user);
            os.flush();
            os.close();
            System.out.println("--------保存对象完成-------");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从本地文件获取user对象
     *
     * @param context
     * @return
     */
    public static Employee getUser(Context context) {
        Employee user = new Employee();
        try {
            FileInputStream fos = new FileInputStream(context.getFilesDir()
                    + "/" + FILE_NAME);
            ObjectInputStream os = new ObjectInputStream(fos);
            user = (Employee) os.readObject();
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 清除本地user对象
     *
     * @param context
     * @return
     */
    public static boolean removeUser(Context context) {
        boolean isDelete = false;
        File file = new File(context.getFilesDir() + "/" + FILE_NAME);
        if (file.exists()) {
            isDelete = file.delete();
        }
        return isDelete;
    }

    public static boolean quit(Context context) {
        return removeUser(context);
    }

    public static void putUserName(Context context, String userName) {
        SharedPreferences preferences = context.getSharedPreferences(
                SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("userName", userName);
        boolean flag = edit.commit();
        System.out.println("----是否保存成功----" + flag);
    }

    public static String getUserName(Context context) {

        SharedPreferences preferences = context.getSharedPreferences(
                SHARED_NAME, Context.MODE_PRIVATE);

        return preferences.getString("userName", "");

    }

    public static void setSavePassword(Context context, boolean isSave) {
        SharedPreferencesUtils.setParam(context, "is_save_password", isSave);
    }

    public static boolean isSavePassword(Context context) {
        return (boolean) SharedPreferencesUtils.getParam(context, "is_save_password", true);
    }

    public static void putUserPassword(Context context, String password) {
        SharedPreferencesUtils.setParam(context, SHARE_PASSWORD, password);
    }

    public static String getUserPassword(Context context) {
        if (isSavePassword(context))
            return (String) SharedPreferencesUtils.getParam(context, SHARE_PASSWORD, "");
        else
            return "";
    }
}
