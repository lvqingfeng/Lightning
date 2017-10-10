package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/8/26
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class EmployeeDataBean {

    /**
     * code : 1
     * msg : 获取数据成功!
     * data : {"info":{"employee_name":"admin","mobile":"15909581102","gender":null,"birth":"2015-06-10 00:00:00","deposit":"0.00","getder_str":"女"}}
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
         * info : {"employee_name":"admin","mobile":"15909581102","gender":null,"birth":"2015-06-10 00:00:00","deposit":"0.00","getder_str":"女"}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * employee_name : admin
             * mobile : 15909581102
             * gender : null
             * birth : 2015-06-10 00:00:00
             * deposit : 0.00
             * getder_str : 女
             */

            private String employee_name;
            private String mobile;
            private String gender;
            private String birth;
            private String deposit;
            private String getder_str;
            private String address;
            private String risk;

            public String getRisk() {
                return risk;
            }

            public void setRisk(String risk) {
                this.risk = risk;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getEmployee_name() {
                return employee_name;
            }

            public void setEmployee_name(String employee_name) {
                this.employee_name = employee_name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            public String getDeposit() {
                return deposit;
            }

            public void setDeposit(String deposit) {
                this.deposit = deposit;
            }

            public String getGetder_str() {
                return getder_str;
            }

            public void setGetder_str(String getder_str) {
                this.getder_str = getder_str;
            }
        }
    }
}
