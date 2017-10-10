package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/7/19
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class StagesBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"id":7,"name":"25天","fenqi_num":25},{"id":8,"name":"50天","fenqi_num":50}]
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
         * id : 7
         * name : 25天
         * fenqi_num : 25
         */

        private int id;
        private String name;
        private int fenqi_num;

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

        public int getFenqi_num() {
            return fenqi_num;
        }

        public void setFenqi_num(int fenqi_num) {
            this.fenqi_num = fenqi_num;
        }
    }
}
