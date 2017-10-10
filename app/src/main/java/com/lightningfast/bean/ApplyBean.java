package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/25
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class ApplyBean {

    /**
     * code : 1
     * msg : 获取数据成功!
     * data : {"is_nums":0,"pages":{"page":1,"total":1},"customerInfo":[{"order_id":2,"need_price":"1000.00","create_time":"2017-08-10 17:24:50","customer_name":null,"mobile":"18095594615","customer_id":1}]}
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
         * pages : {"page":1,"total":1}
         * customerInfo : [{"order_id":2,"need_price":"1000.00","create_time":"2017-08-10 17:24:50","customer_name":null,"mobile":"18095594615","customer_id":1}]
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
             * total : 1
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
             * order_id : 2
             * need_price : 1000.00
             * create_time : 2017-08-10 17:24:50
             * customer_name : null
             * mobile : 18095594615
             * customer_id : 1
             */

            private String order_id;
            private String need_price;
            private String create_time;
            private String customer_name;
            private String mobile;
            private String customer_id;

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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
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
        }
    }
}
