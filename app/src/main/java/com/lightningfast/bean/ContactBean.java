package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/7/27
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class ContactBean {

    /**
     * code : 1
     * msg : 查询成功!
     * data : [{"contact_name":"王五","mobile":"17010207174","guanxi":6,"id":86,"guanxi_str":"姐妹"},{"contact_name":"李四","mobile":"17010207173","guanxi":5,"id":85,"guanxi_str":"兄妹"},{"contact_name":"张三","mobile":"17010207172","guanxi":4,"id":84,"guanxi_str":"兄弟"}]
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
         * contact_name : 王五
         * mobile : 17010207174
         * guanxi : 6
         * id : 86
         * guanxi_str : 姐妹
         */

        private String contact_name;
        private String mobile;
        private String guanxi;
        private String id;
        private String guanxi_str;

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getGuanxi() {
            return guanxi;
        }

        public void setGuanxi(String guanxi) {
            this.guanxi = guanxi;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGuanxi_str() {
            return guanxi_str;
        }

        public void setGuanxi_str(String guanxi_str) {
            this.guanxi_str = guanxi_str;
        }
    }
}
