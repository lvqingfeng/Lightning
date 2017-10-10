package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/9/11
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class AuditingBean {

    /**
     * code : 1
     * msg : 请求成功
     * data : {"status":3,"info1":"待审核","info2":"审核成功","info3":"放款成功","need_price":"1000.00"}
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
         * status : 3
         * info1 : 待审核
         * info2 : 审核成功
         * info3 : 放款成功
         * need_price : 1000.00
         */

        private int status;
        private String info1;
        private String info2;
        private String info3;
        private String need_price;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getInfo1() {
            return info1;
        }

        public void setInfo1(String info1) {
            this.info1 = info1;
        }

        public String getInfo2() {
            return info2;
        }

        public void setInfo2(String info2) {
            this.info2 = info2;
        }

        public String getInfo3() {
            return info3;
        }

        public void setInfo3(String info3) {
            this.info3 = info3;
        }

        public String getNeed_price() {
            return need_price;
        }

        public void setNeed_price(String need_price) {
            this.need_price = need_price;
        }
    }
}
