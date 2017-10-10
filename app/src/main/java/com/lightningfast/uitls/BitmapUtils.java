package com.lightningfast.uitls;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;

import com.lightningfast.api.ApiClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.OnCompressListener;

/**
 * 作者:吕清锋
 * 时间:2017/7/10
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class BitmapUtils {
    private static List<String> mImageUrl;
    private static OnPhotoCompressionCallback mCallback;
    private static List<String> mComppressionImageUrl;
    private static Context mContext;

    public static void pictureCompression(Context context, List<String> imageUrl, OnPhotoCompressionCallback callback) {
        mContext = context;
        mCallback = callback;
        mComppressionImageUrl = new ArrayList<>();
        mImageUrl = new ArrayList<>();
        for (String s : imageUrl) {
            File file = new File(s);
            if (file.length() >= 1024 * 2) {
                mImageUrl.add(s);
            } else {
                mComppressionImageUrl.add(file.getAbsolutePath());
            }
        }
        DialogUtils.showProgressDialog(context, "请稍后....", "", "");
        beginPhotoCompression();
    }

    private static void beginPhotoCompression() {
        if (mImageUrl.size() > 0) {
            String imageUrl = mImageUrl.remove(0);
            ApiClient.pictureCompression(mContext, new File(imageUrl), new OnCompressListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onSuccess(File file) {
                    mComppressionImageUrl.add(file.getAbsolutePath());
                    beginPhotoCompression();
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        } else {
            DialogUtils.hideDialog(DialogUtils.LoadCompleteType.Error);
            mCallback.onCompressionCallback(mComppressionImageUrl);
        }

    }


    public interface OnPhotoCompressionCallback {
        void onCompressionCallback(List<String> imageUrl);
    }
    public static Bitmap blurImageAmeliorate(Bitmap bmp)
    {
        long start = System.currentTimeMillis();
        // 高斯矩阵
        int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int delta = 8; // 值越小图片会越亮，越大则越暗

        int idx = 0;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++)
        {
            for (int k = 1, len = width - 1; k < len; k++)
            {
                idx = 0;
                for (int m = -1; m <= 1; m++)
                {
                    for (int n = -1; n <= 1; n++)
                    {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (int) (pixR * gauss[idx]);
                        newG = newG + (int) (pixG * gauss[idx]);
                        newB = newB + (int) (pixB * gauss[idx]);
                        idx++;
                    }
                }

                newR /= delta;
                newG /= delta;
                newB /= delta;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        long end = System.currentTimeMillis();
        Log.d("may", "used time="+(end - start));
        return bitmap;
    }
    /**
     * 根据图片的url路径获得Bitmap对象
     * @param path
     * @return
     */
    public static Bitmap decodeUriAsBitmapFromNet(String path)  throws IOException {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200){
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        return null;
    }
}
