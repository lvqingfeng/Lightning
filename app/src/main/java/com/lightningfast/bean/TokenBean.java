package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/7/31
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class TokenBean {

    /**
     * code : 1
     * msg : 密码重置成功!
     * data : c41fe060103f3391dfdcf1d78875a5d6
     */

    private int code;
    private String msg;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
