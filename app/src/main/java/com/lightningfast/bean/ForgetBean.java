package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/8/5
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class ForgetBean {

    /**
     * code : 0
     * msg : 短信验证码输入不正确!
     * data : {"atool_timestamp":"1501897103","mobile":"17010207172","captcha":"3934","password":"123"}
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
         * atool_timestamp : 1501897103
         * mobile : 17010207172
         * captcha : 3934
         * password : 123
         */

        private String atool_timestamp;
        private String mobile;
        private String captcha;
        private String password;

        public String getAtool_timestamp() {
            return atool_timestamp;
        }

        public void setAtool_timestamp(String atool_timestamp) {
            this.atool_timestamp = atool_timestamp;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCaptcha() {
            return captcha;
        }

        public void setCaptcha(String captcha) {
            this.captcha = captcha;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
