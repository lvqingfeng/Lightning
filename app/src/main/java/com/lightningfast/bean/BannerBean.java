package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/7/19
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class BannerBean {

    /**
     * code : 1
     * msg : 获取成功!
     * data : [{"id":1186,"pic_title":"广告图片1","path":"http://ogsr1alf5.bkt.clouddn.com/banner1.png","url":"http://img.baidu.com","category_id":1,"md5":"","sha1":"","status":0,"create_time":"1970-01-01 08:00:00","token":null,"system":0},{"id":1187,"pic_title":"广告图片2","path":"http://ogsr1alf5.bkt.clouddn.com/banner2.png","url":"http://img.baidu.com","category_id":1,"md5":"","sha1":"","status":0,"create_time":"1970-01-01 08:00:00","token":null,"system":0}]
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
         * id : 1186
         * pic_title : 广告图片1
         * path : http://ogsr1alf5.bkt.clouddn.com/banner1.png
         * url : http://img.baidu.com
         * category_id : 1
         * md5 :
         * sha1 :
         * status : 0
         * create_time : 1970-01-01 08:00:00
         * token : null
         * system : 0
         */

        private int id;
        private String pic_title;
        private String path;
        private String url;
        private int category_id;
        private String md5;
        private String sha1;
        private int status;
        private String create_time;
        private Object token;
        private int system;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic_title() {
            return pic_title;
        }

        public void setPic_title(String pic_title) {
            this.pic_title = pic_title;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getSha1() {
            return sha1;
        }

        public void setSha1(String sha1) {
            this.sha1 = sha1;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public int getSystem() {
            return system;
        }

        public void setSystem(int system) {
            this.system = system;
        }
    }
}
