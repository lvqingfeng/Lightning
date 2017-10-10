package com.lightningfast.uitls;

import android.content.Context;

import com.alibaba.fastjson.JSONReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/24.
 */
public class JsonFormFileUtils<T> {
    /**
     * 解析本地的JSON数据
     *
     * @param context 环境变量
     * @param path    文件地址
     * @return 返回传入类型的List集合
     */
    public static <T> List<T> getJsonArray(Context context, String path, Class<T> clazz) throws IOException {
        InputStream is = context.getAssets().open(path);
        return getJsonArray(is, "UTF-8", clazz);
    }

    public static <T> List<T> getJsonArray(InputStream is, String stringCoded, Class<T> clazz) throws IOException {
        List<T> array = new ArrayList<T>();
        JSONReader jsonReader = new JSONReader((new InputStreamReader(is, stringCoded)));
        jsonReader.startArray();
        while (jsonReader.hasNext()) {
            T value = jsonReader.readObject(clazz);
            array.add(value);
        }
        jsonReader.endArray();
        jsonReader.close();
        return array;
    }

    /**
     * 解析本地的JSON数据
     *
     * @param context 环境变量
     * @param path    文件地址
     * @return 返回传入的相应类型
     */
    public static <T> T getJsonObject(Context context, String path, Class<T> clazz) throws IOException {
        InputStream is = context.getAssets().open(path);
        return getJsonObject(is, "UTF-8", clazz);
    }

    public static <T> T getJsonObject(InputStream is, String stringCoded, Class<T> clazz) throws IOException {
        T value = null;
        JSONReader jsonReader = new JSONReader(new InputStreamReader(is, stringCoded));
        jsonReader.startObject();
        value = jsonReader.readObject(clazz);
        jsonReader.endObject();
        jsonReader.close();
        return value;
    }


    /**
     * 解析本地的JSON数据
     *
     * @param context 环境变量
     * @param path    文件地址
     * @return
     */
    public static String getJsonString(Context context, String path) {
        return getJsonString(context, path, "UTF-8");
    }

    /**
     * 解析本地的JSON数据
     *
     * @param context     环境变量
     * @param path        文件地址
     * @param stringCoded 解析的编码格式
     * @return
     */
    public static String getJsonString(Context context, String path, String stringCoded) {

        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = context.getAssets().open(path);

            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, stringCoded));
            }
            is.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
