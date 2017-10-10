package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/7/15
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class ContantsListBean {

    /**
     * code : 1
     * msg : 查询成功!
     * data : {"contactInfo":{"contact_name":"李老大","mobile":"13239574839","live_address":"801","guanxi":"配偶"}}
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
         * contactInfo : {"contact_name":"李老大","mobile":"13239574839","live_address":"801","guanxi":"配偶"}
         */

        private ContactInfoBean contactInfo;

        public ContactInfoBean getContactInfo() {
            return contactInfo;
        }

        public void setContactInfo(ContactInfoBean contactInfo) {
            this.contactInfo = contactInfo;
        }

        public static class ContactInfoBean {
            /**
             * contact_name : 李老大
             * mobile : 13239574839
             * live_address : 801
             * guanxi : 配偶
             */

            private String contact_name;
            private String mobile;
            private String live_address;
            private String guanxi;

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

            public String getLive_address() {
                return live_address;
            }

            public void setLive_address(String live_address) {
                this.live_address = live_address;
            }

            public String getGuanxi() {
                return guanxi;
            }

            public void setGuanxi(String guanxi) {
                this.guanxi = guanxi;
            }
        }
    }
}
