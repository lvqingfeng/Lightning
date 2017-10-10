package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/7/14
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class RegisterBean {

    /**
     * code : 1
     * msg : 注册成功!
     * data : {"uid":"1505"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 1505
         */

        private String uid;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
