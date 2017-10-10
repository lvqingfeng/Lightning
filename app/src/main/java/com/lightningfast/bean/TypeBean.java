package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/7/19
 * 版本:v1.0
 * 类描述：分期类型
 * 修改时间:
 */

public class TypeBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"id":4,"name":"按天分期","create_time":"2017-05-26 09:33:16","status":1,"type_days":1},{"id":5,"name":"按周分期","create_time":"2017-06-03 11:46:03","status":1,"type_days":7}]
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
         * id : 4
         * name : 按天分期
         * create_time : 2017-05-26 09:33:16
         * status : 1
         * type_days : 1
         */

        private int id;
        private String name;
        private String create_time;
        private int status;
        private int type_days;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType_days() {
            return type_days;
        }

        public void setType_days(int type_days) {
            this.type_days = type_days;
        }
    }
}
