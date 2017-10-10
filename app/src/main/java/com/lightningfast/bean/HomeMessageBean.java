package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/7/21
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class HomeMessageBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"customer_id":1513,"need_price":"1560.00","customer_name":"呵***"},{"customer_id":1513,"need_price":"2000.00","customer_name":"呵***"},{"customer_id":1512,"need_price":"2000.00","customer_name":"嗨*"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * customer_id : 1513
         * need_price : 1560.00
         * customer_name : 呵***
         */

        private int customer_id;
        private String need_price;
        private String customer_name;

        public int getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
        }

        public String getNeed_price() {
            return need_price;
        }

        public void setNeed_price(String need_price) {
            this.need_price = need_price;
        }

        public String getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(String customer_name) {
            this.customer_name = customer_name;
        }
    }
}
