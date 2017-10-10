package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/8/4
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class EmployeeBean {

    /**
     * code : 1
     * msg : 获取数据成功!
     * data : {"empInfo":[{"id":5,"employee_name":"root"},{"id":6,"employee_name":"like"}]}
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
        private List<EmpInfoBean> empInfo;

        public List<EmpInfoBean> getEmpInfo() {
            return empInfo;
        }

        public void setEmpInfo(List<EmpInfoBean> empInfo) {
            this.empInfo = empInfo;
        }

        public static class EmpInfoBean {
            /**
             * id : 5
             * employee_name : root
             */

            private String id;
            private String employee_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEmployee_name() {
                return employee_name;
            }

            public void setEmployee_name(String employee_name) {
                this.employee_name = employee_name;
            }
        }
    }
}
