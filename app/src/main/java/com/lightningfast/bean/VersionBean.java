package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/9/20
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class VersionBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"id":6,"version":"1.0.1","year":2016,"create_time":"2017-08-26 17:13:28","log":"修改Cookie认证方式。","version_value":2}
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
         * id : 6
         * version : 1.0.1
         * year : 2016
         * create_time : 2017-08-26 17:13:28
         * log : 修改Cookie认证方式。
         * version_value : 2
         */

        private int id;
        private String version;
        private int year;
        private String create_time;
        private String log;
        private int version_value;
        private String download_url;

        public String getDownload_url() {
            return download_url;
        }

        public void setDownload_url(String download_url) {
            this.download_url = download_url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getLog() {
            return log;
        }

        public void setLog(String log) {
            this.log = log;
        }

        public int getVersion_value() {
            return version_value;
        }

        public void setVersion_value(int version_value) {
            this.version_value = version_value;
        }
    }
}
