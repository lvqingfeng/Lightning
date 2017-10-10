package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/7/27
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class QuotaBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : {"min_money":"1000","max_money":"50000"}
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
         * min_money : 1000
         * max_money : 50000
         */

        private String min_money;
        private String max_money;

        public String getMin_money() {
            return min_money;
        }

        public void setMin_money(String min_money) {
            this.min_money = min_money;
        }

        public String getMax_money() {
            return max_money;
        }

        public void setMax_money(String max_money) {
            this.max_money = max_money;
        }
    }
}
