package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/8/4
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class MoneyBean {

    /**
     * code : 1
     * msg : 获取数据成功!
     * data : {"repay_price":100}
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
         * repay_price : 100
         */

        private String repay_price;

        public String getRepay_price() {
            return repay_price;
        }

        public void setRepay_price(String repay_price) {
            this.repay_price = repay_price;
        }
    }
}
