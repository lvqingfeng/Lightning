package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/7/29
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class MyLoanBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : {"is_nums":0,"pages":1,"order_info":[{"order_sn":"20170818090355974418","id":239,"create_time":"2017-08-18 09:03:55","total_number":10,"past_number":10},{"order_sn":"20170818102245580603","id":240,"create_time":"2017-08-15 10:22:45","total_number":25,"past_number":0}]}
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
         * pages : 1
         * order_info : [{"order_sn":"20170818090355974418","id":239,"create_time":"2017-08-18 09:03:55","total_number":10,"past_number":10},{"order_sn":"20170818102245580603","id":240,"create_time":"2017-08-15 10:22:45","total_number":25,"past_number":0}]
         */

        private int is_nums;
        private int pages;
        private List<OrderInfoBean> order_info;

        public int getIs_nums() {
            return is_nums;
        }

        public void setIs_nums(int is_nums) {
            this.is_nums = is_nums;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<OrderInfoBean> getOrder_info() {
            return order_info;
        }

        public void setOrder_info(List<OrderInfoBean> order_info) {
            this.order_info = order_info;
        }

        public static class OrderInfoBean {
            /**
             * order_sn : 20170818090355974418
             * id : 239
             * create_time : 2017-08-18 09:03:55
             * total_number : 10
             * past_number : 10
             */

            private String order_sn;
            private int id;
            private String create_time;
            private int total_number;
            private int past_number;

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getTotal_number() {
                return total_number;
            }

            public void setTotal_number(int total_number) {
                this.total_number = total_number;
            }

            public int getPast_number() {
                return past_number;
            }

            public void setPast_number(int past_number) {
                this.past_number = past_number;
            }
        }
    }
}
