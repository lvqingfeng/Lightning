package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/25
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class LoanBean {

    /**
     * code : 1
     * msg : 获取数据成功!
     * data : {"is_nums":0,"pages":{"page":1,"total":9},"customerInfo":[{"order_id":7,"need_price":"1000.00","customer_name":null,"mobile":"17010207171","customer_id":6,"fenqi_number":25,"past_number":1,"repayment_total":40},{"order_id":11,"need_price":"1000.00","customer_name":null,"mobile":"13369596154","customer_id":9,"fenqi_number":25,"past_number":0,"repayment_total":0},{"order_id":12,"need_price":"1000.00","customer_name":null,"mobile":"17809550955","customer_id":11,"fenqi_number":25,"past_number":0,"repayment_total":0},{"order_id":106,"need_price":"1000.00","customer_name":"王晓彤","mobile":"13895133263","customer_id":12,"fenqi_number":25,"past_number":0,"repayment_total":0},{"order_id":14,"need_price":"1000.00","customer_name":"李峰","mobile":"13079520005","customer_id":13,"fenqi_number":25,"past_number":0,"repayment_total":0},{"order_id":17,"need_price":"30000.00","customer_name":null,"mobile":"18895018063","customer_id":22,"fenqi_number":50,"past_number":0,"repayment_total":0},{"order_id":19,"need_price":"10000.00","customer_name":null,"mobile":"13014256879","customer_id":27,"fenqi_number":50,"past_number":0,"repayment_total":0},{"order_id":22,"need_price":"12000.00","customer_name":null,"mobile":"18104771688","customer_id":31,"fenqi_number":50,"past_number":0,"repayment_total":0},{"order_id":27,"need_price":"10000.00","customer_name":null,"mobile":"13313223098","customer_id":43,"fenqi_number":50,"past_number":0,"repayment_total":0}]}
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
         * is_nums : 0
         * pages : {"page":1,"total":9}
         * customerInfo : [{"order_id":7,"need_price":"1000.00","customer_name":null,"mobile":"17010207171","customer_id":6,"fenqi_number":25,"past_number":1,"repayment_total":40},{"order_id":11,"need_price":"1000.00","customer_name":null,"mobile":"13369596154","customer_id":9,"fenqi_number":25,"past_number":0,"repayment_total":0},{"order_id":12,"need_price":"1000.00","customer_name":null,"mobile":"17809550955","customer_id":11,"fenqi_number":25,"past_number":0,"repayment_total":0},{"order_id":106,"need_price":"1000.00","customer_name":"王晓彤","mobile":"13895133263","customer_id":12,"fenqi_number":25,"past_number":0,"repayment_total":0},{"order_id":14,"need_price":"1000.00","customer_name":"李峰","mobile":"13079520005","customer_id":13,"fenqi_number":25,"past_number":0,"repayment_total":0},{"order_id":17,"need_price":"30000.00","customer_name":null,"mobile":"18895018063","customer_id":22,"fenqi_number":50,"past_number":0,"repayment_total":0},{"order_id":19,"need_price":"10000.00","customer_name":null,"mobile":"13014256879","customer_id":27,"fenqi_number":50,"past_number":0,"repayment_total":0},{"order_id":22,"need_price":"12000.00","customer_name":null,"mobile":"18104771688","customer_id":31,"fenqi_number":50,"past_number":0,"repayment_total":0},{"order_id":27,"need_price":"10000.00","customer_name":null,"mobile":"13313223098","customer_id":43,"fenqi_number":50,"past_number":0,"repayment_total":0}]
         */

        private int is_nums;
        private PagesBean pages;
        private List<CustomerInfoBean> customerInfo;

        public int getIs_nums() {
            return is_nums;
        }

        public void setIs_nums(int is_nums) {
            this.is_nums = is_nums;
        }

        public PagesBean getPages() {
            return pages;
        }

        public void setPages(PagesBean pages) {
            this.pages = pages;
        }

        public List<CustomerInfoBean> getCustomerInfo() {
            return customerInfo;
        }

        public void setCustomerInfo(List<CustomerInfoBean> customerInfo) {
            this.customerInfo = customerInfo;
        }

        public static class PagesBean {
            /**
             * page : 1
             * total : 9
             */

            private int page;
            private int total;

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }
        }

        public static class CustomerInfoBean {
            /**
             * order_id : 7
             * need_price : 1000.00
             * customer_name : null
             * mobile : 17010207171
             * customer_id : 6
             * fenqi_number : 25
             * past_number : 1
             * repayment_total : 40
             */

            private String order_id;
            private String need_price;
            private String customer_name;
            private String mobile;
            private String customer_id;
            private String fenqi_number;
            private String past_number;
            private String repayment_total;
            private String create_time;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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

            public String getCustomer_name() {
                return customer_name;
            }

            public void setCustomer_name(String customer_name) {
                this.customer_name = customer_name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getCustomer_id() {
                return customer_id;
            }

            public void setCustomer_id(String customer_id) {
                this.customer_id = customer_id;
            }

            public String getFenqi_number() {
                return fenqi_number;
            }

            public void setFenqi_number(String fenqi_number) {
                this.fenqi_number = fenqi_number;
            }

            public String getPast_number() {
                return past_number;
            }

            public void setPast_number(String past_number) {
                this.past_number = past_number;
            }

            public String getRepayment_total() {
                return repayment_total;
            }

            public void setRepayment_total(String repayment_total) {
                this.repayment_total = repayment_total;
            }
        }
    }
}
