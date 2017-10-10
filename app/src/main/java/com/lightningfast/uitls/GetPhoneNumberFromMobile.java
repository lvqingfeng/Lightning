package com.lightningfast.uitls;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.lightningfast.bean.MailListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/7/28
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class GetPhoneNumberFromMobile {
    private List<MailListBean> mList;

    public List<MailListBean> getPhoneNumberFromMobile(Context mContext) {
        mList = new ArrayList<>();
        Cursor cursor = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        //moveToNext方法返回的是一个boolean类型的数据
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            MailListBean mailListBean = new MailListBean(name, number);
            mList.add(mailListBean);
        }
        cursor.close();
        return mList;

    }

    public String getMailList(Context mContext) {
        Cursor cursor = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        //moveToNext方法返回的是一个boolean类型的数据
        StringBuilder builder=new StringBuilder();
        while (cursor.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            builder.append(name).append("|").append(number).append(",");
        }

        cursor.close();
        return builder.toString();
    }
}
