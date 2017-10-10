package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/25
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class OverdueBean {

    /**
     * code : 1
     * msg : 获取数据成功!
     * data : {"is_nums":0,"pages":{"page":1,"total":9},"customerInfo":[{"overdue_nums":5,"overPrice":0,"customer_name":null,"mobile":"17010207171","create_time":"2017-08-11 13:58:08"},{"overdue_nums":13,"overPrice":0,"customer_name":null,"mobile":"13369596154","create_time":"2017-08-11 16:52:44"},{"overdue_nums":13,"overPrice":0,"customer_name":null,"mobile":"17809550955","create_time":"2017-08-11 16:58:04"},{"overdue_nums":6,"overPrice":0,"customer_name":"王晓彤","mobile":"13895133263","create_time":"2017-08-18 18:08:14"},{"overdue_nums":12,"overPrice":0,"customer_name":"李峰","mobile":"13079520005","create_time":"2017-08-12 12:11:45"},{"overdue_nums":12,"overPrice":0,"customer_name":null,"mobile":"18895018063","create_time":"2017-08-12 13:59:26"},{"overdue_nums":12,"overPrice":0,"customer_name":null,"mobile":"13014256879","create_time":"2017-08-12 14:12:48"},{"overdue_nums":12,"overPrice":0,"customer_name":null,"mobile":"18104771688","create_time":"2017-08-12 15:31:55"},{"overdue_nums":11,"overPrice":0,"customer_name":null,"mobile":"13313223098","create_time":"2017-08-13 09:48:01"}]}
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
         * customerInfo : [{"overdue_nums":5,"overPrice":0,"customer_name":null,"mobile":"17010207171","create_time":"2017-08-11 13:58:08"},{"overdue_nums":13,"overPrice":0,"customer_name":null,"mobile":"13369596154","create_time":"2017-08-11 16:52:44"},{"overdue_nums":13,"overPrice":0,"customer_name":null,"mobile":"17809550955","create_time":"2017-08-11 16:58:04"},{"overdue_nums":6,"overPrice":0,"customer_name":"王晓彤","mobile":"13895133263","create_time":"2017-08-18 18:08:14"},{"overdue_nums":12,"overPrice":0,"customer_name":"李峰","mobile":"13079520005","create_time":"2017-08-12 12:11:45"},{"overdue_nums":12,"overPrice":0,"customer_name":null,"mobile":"18895018063","create_time":"2017-08-12 13:59:26"},{"overdue_nums":12,"overPrice":0,"customer_name":null,"mobile":"13014256879","create_time":"2017-08-12 14:12:48"},{"overdue_nums":12,"overPrice":0,"customer_name":null,"mobile":"18104771688","create_time":"2017-08-12 15:31:55"},{"overdue_nums":11,"overPrice":0,"customer_name":null,"mobile":"13313223098","create_time":"2017-08-13 09:48:01"}]
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
             * overdue_nums : 5
             * overPrice : 0
             * customer_name : null
             * mobile : 17010207171
             * create_time : 2017-08-11 13:58:08
             */

            private String overdue_nums;
            private String overPrice;
            private String customer_name;
            private String mobile;
            private String create_time;

            public String getOverdue_nums() {
                return overdue_nums;
            }

            public void setOverdue_nums(String overdue_nums) {
                this.overdue_nums = overdue_nums;
            }

            public String getOverPrice() {
                return overPrice;
            }

            public void setOverPrice(String overPrice) {
                this.overPrice = overPrice;
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

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
