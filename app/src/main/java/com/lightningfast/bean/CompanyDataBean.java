package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/7/15
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class CompanyDataBean {

    /**
     * code : 1
     * msg : 查询成功!
     * data : {"id":27,"company_name":"异次ci元","company_nature":1,"work_years":2,"salary":3,"company_address":"801基地","province_id":20,"city_id":270,"area_id":2263,"company_id":27,"company_nature_str":"国有企业","work_years_str":"1-2年","salary_str":"5000-10000元","area_str":"宁夏银川西夏区","contact_id":null}
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
         * id : 27
         * company_name : 异次ci元
         * company_nature : 1
         * work_years : 2
         * salary : 3
         * company_address : 801基地
         * province_id : 20
         * city_id : 270
         * area_id : 2263
         * company_id : 27
         * company_nature_str : 国有企业
         * work_years_str : 1-2年
         * salary_str : 5000-10000元
         * area_str : 宁夏银川西夏区
         * contact_id : null
         */

        private int id;
        private String company_name;
        private int company_nature;
        private int work_years;
        private String salary;
        private String company_address;
        private int province_id;
        private int city_id;
        private int area_id;
        private int company_id;
        private String company_nature_str;
        private String work_years_str;
        private String salary_str;
        private String area_str;
        private String contact_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public int getCompany_nature() {
            return company_nature;
        }

        public void setCompany_nature(int company_nature) {
            this.company_nature = company_nature;
        }

        public int getWork_years() {
            return work_years;
        }

        public void setWork_years(int work_years) {
            this.work_years = work_years;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getCompany_address() {
            return company_address;
        }

        public void setCompany_address(String company_address) {
            this.company_address = company_address;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public int getCompany_id() {
            return company_id;
        }

        public void setCompany_id(int company_id) {
            this.company_id = company_id;
        }

        public String getCompany_nature_str() {
            return company_nature_str;
        }

        public void setCompany_nature_str(String company_nature_str) {
            this.company_nature_str = company_nature_str;
        }

        public String getWork_years_str() {
            return work_years_str;
        }

        public void setWork_years_str(String work_years_str) {
            this.work_years_str = work_years_str;
        }

        public String getSalary_str() {
            return salary_str;
        }

        public void setSalary_str(String salary_str) {
            this.salary_str = salary_str;
        }

        public String getArea_str() {
            return area_str;
        }

        public void setArea_str(String area_str) {
            this.area_str = area_str;
        }

        public String getContact_id() {
            return contact_id;
        }

        public void setContact_id(String contact_id) {
            this.contact_id = contact_id;
        }
    }
}
