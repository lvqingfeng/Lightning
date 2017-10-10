package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/3
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class RepaymentDetailsBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : {"detail":[{"id":401,"order_id":1,"need_price":"1.00","repayment":"1.00","status":2,"repayment_time":"2017-07-28","customer_id":1512,"create_time":"1970-01-01","now_number":1},{"id":403,"order_id":1,"need_price":null,"repayment":null,"status":null,"repayment_time":"1970-01-01","customer_id":1512,"create_time":"1970-01-01","now_number":2}]}
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
        private List<DetailBean> detail;

        public List<DetailBean> getDetail() {
            return detail;
        }

        public void setDetail(List<DetailBean> detail) {
            this.detail = detail;
        }

        public static class DetailBean {
            /**
             * id : 401
             * order_id : 1
             * need_price : 1.00
             * repayment : 1.00
             * status : 2
             * repayment_time : 2017-07-28
             * customer_id : 1512
             * create_time : 1970-01-01
             * now_number : 1
             */

            private String id;
            private String order_id;
            private String need_price;
            private String repayment;
            private String status;
            private String repayment_time;
            private String customer_id;
            private String create_time;
            private String now_number;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getNeed_price() {
                return need_price;
            }

            public void setNeed_price(String need_price) {
                this.need_price = need_price;
            }

            public String getRepayment() {
                return repayment;
            }

            public void setRepayment(String repayment) {
                this.repayment = repayment;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getRepayment_time() {
                return repayment_time;
            }

            public void setRepayment_time(String repayment_time) {
                this.repayment_time = repayment_time;
            }

            public String getCustomer_id() {
                return customer_id;
            }

            public void setCustomer_id(String customer_id) {
                this.customer_id = customer_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getNow_number() {
                return now_number;
            }

            public void setNow_number(String now_number) {
                this.now_number = now_number;
            }
        }
    }
}
