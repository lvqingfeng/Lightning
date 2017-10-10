package com.lightningfast.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.lightningfast.R;
import com.lightningfast.camera.sd_card_utils.SDCardUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * 作者:吕清锋
 * 时间:2017/9/14
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class DownloadService extends Service{
    private String mDownloadUrl;//APK的下载路径
    private NotificationManager mNotificationManager;
    private Notification mNotification;

    public static void actionStart(Context context, String apkUrl) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra("apkUrl", apkUrl);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            notifyMsg("温馨提醒", "文件下载失败", 0);
            stopSelf();//杀死Service
        }
        mDownloadUrl = intent.getStringExtra("apkUrl");//获取下载APK的链接
        downloadFile(mDownloadUrl);//下载APK
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void notifyMsg(String title, String content, int progress) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.download_white_arrows).setLargeIcon(BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_launcher)).setContentTitle(title);
        if (progress > 0 && progress < 100) {
            //下载进行中
            builder.setProgress(100, progress, false);
        } else {
            builder.setProgress(0, 0, false);
        }
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        builder.setContentText(content);
        if (progress >= 100) {
            //下载完成
            builder.setContentIntent(getInstallIntent());
        }
        mNotification = builder.build();
        mNotificationManager.notify(0, mNotification);

    }

    /**
     * 安装apk文件
     *
     * @return
     */
    private PendingIntent getInstallIntent() {
        File file = new File(SDCardUtils.getFileStoragePath()
                + mDownloadUrl.substring(mDownloadUrl.lastIndexOf("/")));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + file.getAbsolutePath())
                , "application/vnd.android.package-archive");
        PendingIntent pendingIntent = PendingIntent.getActivity(this
                , 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }


    /**
     * 下载apk文件
     *
     * @param url
     */
    private void downloadFile(String url) {
        if (SDCardUtils.isCanDownloadFile(SDCardUtils
                .getFileStoragePath() + mDownloadUrl.substring(
                mDownloadUrl.lastIndexOf("/")))) {

            OkHttpUtils.get().url(url).build().execute(new FileCallBack(SDCardUtils
                    .getFileStoragePath(),
                    mDownloadUrl.substring(mDownloadUrl.lastIndexOf("/"))) {
                @Override
                public void onError(Call call, Exception e) {
                    notifyMsg("APPName", "文件下载失败", 0);
                    stopSelf();
                }

                @Override
                public void onResponse(Call call, File file) {
                    //当文件下载完成后回调
                    notifyMsg("APPName", "文件下载已完成", 100);
                    stopSelf();
                    Toast.makeText(getApplicationContext(), "文件下载完成", Toast
                            .LENGTH_SHORT).show();
                    File fileIntent = new File(SDCardUtils.getFileStoragePath() + mDownloadUrl
                            .substring(mDownloadUrl.lastIndexOf("/")));
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(Uri.parse("file://" + fileIntent.getAbsolutePath()),
                            "application/vnd.android.package-archive");
                    startActivity(intent);
                    mNotificationManager.cancel(0);
                }

                @Override
                public void inProgress(float progress, long total) {
                    //progress*100为当前文件下载进度，total为文件大小
                    if ((int) (progress * 100) % 10 == 0) {
                        //避免频繁刷新View，这里设置每下载10%提醒更新一次进度
                        notifyMsg("APPName", "文件正在下载..", (int) (progress * 100));
                    }
                }
            });
        }
    }
}
