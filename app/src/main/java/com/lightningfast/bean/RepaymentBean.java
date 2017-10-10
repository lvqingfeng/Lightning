package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/8/2
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class RepaymentBean {

    /**
     * code : 1
     * msg : 获取数据成功!
     * data : {"total_payment":"2000.00","remainder":1998,"fenqi_nums":50,"fenqi_remainder":50,"order_id":2}
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
         * total_payment : 2000.00
         * remainder : 1998
         * fenqi_nums : 50
         * fenqi_remainder : 50
         * order_id : 2
         */

        private String total_payment;
        private String remainder;
        private String fenqi_nums;
        private String fenqi_remainder;
        private String order_id;
        private String due_qi_number;

        public String getDue_qi_number() {
            return due_qi_number;
        }

        public void setDue_qi_number(String due_qi_number) {
            this.due_qi_number = due_qi_number;
        }

        public String getTotal_payment() {
            return total_payment;
        }

        public void setTotal_payment(String total_payment) {
            this.total_payment = total_payment;
        }

        public String getRemainder() {
            return remainder;
        }

        public void setRemainder(String remainder) {
            this.remainder = remainder;
        }

        public String getFenqi_nums() {
            return fenqi_nums;
        }

        public void setFenqi_nums(String fenqi_nums) {
            this.fenqi_nums = fenqi_nums;
        }

        public String getFenqi_remainder() {
            return fenqi_remainder;
        }

        public void setFenqi_remainder(String fenqi_remainder) {
            this.fenqi_remainder = fenqi_remainder;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
