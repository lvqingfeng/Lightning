package com.lightningfast.uitls;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.lightningfast.bean.CallInfo;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;


/**
 * 作者:吕清锋
 * 时间:2017/8/7
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class CallInfoService {

    private static CallInfo instance=null;
    /**
     * 获取通话记录
     *
     * @param context 上下文。通话记录需要从系统的【通话应用】中的内容提供者中获取，内容提供者需要上下文。通话记录保存在联系人数据库中：data/data/com.android.provider.contacts/databases/contacts2.db库中的calls表。
     * @return 包含所有通话记录的一个集合
     */
    public static List<CallInfo> getCallInfos(final Context context) {
        final List<CallInfo> infos = new ArrayList<>();
        final List<CallInfo> newList = new ArrayList<>();
        RxPermissions.getInstance(context).request(Manifest.permission.READ_CALL_LOG)
                .subscribe(new Action1<Boolean>() {

                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            ContentResolver resolver = context.getContentResolver();
                            // uri的写法需要查看源码JB\packages\providers\ContactsProvider\AndroidManifest.xml中内容提供者的授权
                            // 从清单文件可知该提供者是CallLogProvider，且通话记录相关操作被封装到了Calls类中
                            Uri uri = CallLog.Calls.CONTENT_URI;
                            String[] projection = new String[]{
                                    CallLog.Calls.CACHED_NAME,//姓名
                                    CallLog.Calls.NUMBER, // 号码
                                    CallLog.Calls.DATE,   // 日期
                                    CallLog.Calls.TYPE,    // 类型：来电、去电、未接
                                    CallLog.Calls.DURATION
                            };
                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            try {
                                Cursor cursor = resolver.query(uri, projection, null, null, null);
                                while (cursor.moveToNext()) {
                                    if (infos.size()>2000){
                                        break;
                                    }
//                                    instance = CallInfo.getInstance();
//                                    instance.setName(cursor.getString(0));
//                                    instance.setNumber(cursor.getString(1));
//                                    instance.setDate(cursor.getLong(2));
//                                    instance.setType(cursor.getInt(3));
//                                    instance.setDuration(cursor.getString(4));
//                                    infos.add(instance);
                                    String name = cursor.getString(0);
                                    String number = cursor.getString(1);
                                    long date = cursor.getLong(2);
                                    int type = cursor.getInt(3);
                                    String duration=cursor.getString(4);
                                    infos.add(CallInfo.getInstance(name,number,date,type,duration));
//                                    infos.add(new CallInfo(name, number, date, type,duration));
                                }
                                cursor.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            for (int i = 0; i < infos.size(); i++) {
                                for (int j = i + 1; j < infos.size(); j++) {
                                    if (infos.get(i).getNumber().equals(infos.get(j).getNumber())) {
                                        newList.add(infos.get(i));
                                        break;
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(context, "请开启通话记录权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return newList;
    }
}
