package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/8/18
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class MyLoanTotalBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : {"total_payment":"1000.00","total_past_payment":0}
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
         * total_payment : 1000.00
         * total_past_payment : 0
         */

        private String total_payment;
        private String total_past_payment;

        public String getTotal_payment() {
            return total_payment;
        }

        public void setTotal_payment(String total_payment) {
            this.total_payment = total_payment;
        }

        public String getTotal_past_payment() {
            return total_past_payment;
        }

        public void setTotal_past_payment(String total_past_payment) {
            this.total_past_payment = total_past_payment;
        }
    }
}
