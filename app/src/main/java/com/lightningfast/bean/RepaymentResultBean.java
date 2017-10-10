package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/8/17
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class RepaymentResultBean {

    /**
     * code : 1
     * msg : 操作成功
     * data : {"status":-1,"fail_reason":"余额不足"}
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
         * status : -1
         * fail_reason : 余额不足
         */

        private int status;
        private String fail_reason;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getFail_reason() {
            return fail_reason;
        }

        public void setFail_reason(String fail_reason) {
            this.fail_reason = fail_reason;
        }
    }
}
