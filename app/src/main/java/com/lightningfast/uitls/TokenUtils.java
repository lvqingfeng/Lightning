package com.lightningfast.uitls;

/**
 * 作者:吕清锋
 * 时间:2017/7/13
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class TokenUtils {
    private String username="fenqithink";
    private String client_key="miniprograms";
    private String token=null;
    private static TokenUtils sInstance;

    public TokenUtils() {

    }
    public static TokenUtils getInstance() {
        if (sInstance == null) {
            sInstance = new TokenUtils();
        }
        return sInstance;
    }
    public String getToken(){
        long timeMillis = System.currentTimeMillis();
        String yyyy=DateUtils.getDateYToString(timeMillis);
        token=username+yyyy+client_key;
        return MD5Util.encrypt(token);
    }
}
