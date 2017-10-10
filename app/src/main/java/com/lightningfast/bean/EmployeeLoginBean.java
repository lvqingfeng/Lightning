package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/8/19
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class EmployeeLoginBean {

    /**
     * code : 1
     * msg : 登录成功!
     * data : {"detail":{"id":27,"employee_name":"13709592201","age":null,"gender":null,"email":null,"birth":null,"status":1,"token":"eacbd179fc363b64c11962c722bdc40e"},"auth":{"id":1,"title":"超级管理员"}}
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
         * detail : {"id":27,"employee_name":"13709592201","age":null,"gender":null,"email":null,"birth":null,"status":1,"token":"eacbd179fc363b64c11962c722bdc40e"}
         * auth : {"id":1,"title":"超级管理员"}
         */

        private DetailBean detail;
        private AuthBean auth;

        public DetailBean getDetail() {
            return detail;
        }

        public void setDetail(DetailBean detail) {
            this.detail = detail;
        }

        public AuthBean getAuth() {
            return auth;
        }

        public void setAuth(AuthBean auth) {
            this.auth = auth;
        }

        public static class DetailBean {
            /**
             * id : 27
             * employee_name : 13709592201
             * age : null
             * gender : null
             * email : null
             * birth : null
             * status : 1
             * token : eacbd179fc363b64c11962c722bdc40e
             */

            private String id;
            private String employee_name;
            private String age;
            private String gender;
            private String email;
            private String birth;
            private String status;
            private String token;

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

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getBirth() {
                return birth;
            }

            public void setBirth(String birth) {
                this.birth = birth;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }

        public static class AuthBean {
            /**
             * id : 1
             * title : 超级管理员
             */

            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
