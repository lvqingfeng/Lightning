package com.lightningfast.bean;

import java.util.List;

/**
 * 作者:吕清锋
 * 时间:2017/7/26
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class DatasBean {

    /**
     * code : 1
     * msg : 请求成功!
     * data : {"companyNature":[{"id":1,"name":"国有企业"},{"id":2,"name":"国有控股企业"},{"id":3,"name":"外资企业"},{"id":4,"name":"合资企业"},{"id":5,"name":"民营企业"}],"workYears":[{"id":1,"name":"1年以下"},{"id":2,"name":"1-2年"},{"id":3,"name":"2-3年"},{"id":4,"name":"3-5年"},{"id":5,"name":"5-10年"},{"id":6,"name":"10年以上"}],"salary":[{"id":1,"name":"1000-3000元"},{"id":2,"name":"3000-5000元"},{"id":3,"name":"5000-10000元"},{"id":4,"name":"10000元以上"}],"guanxi":[{"id":1,"name":"父子"},{"id":2,"name":"母子"},{"id":3,"name":"配偶"},{"id":4,"name":"兄弟"},{"id":5,"name":"兄妹"},{"id":6,"name":"姐妹"},{"id":7,"name":"姐弟"},{"id":8,"name":"其他"}],"education":[{"id":1,"name":"小学"},{"id":2,"name":"初中"},{"id":3,"name":"高中"},{"id":4,"name":"中专"},{"id":5,"name":"高职"},{"id":6,"name":"专科"},{"id":7,"name":"本科"},{"id":8,"name":"硕士研究生"},{"id":9,"name":"博士研究生"}],"house_nature":[{"id":1,"name":"自管房产"},{"id":2,"name":"直管房产"},{"id":3,"name":"国有房产"},{"id":4,"name":"集体房产"},{"id":5,"name":"军队房产"},{"id":6,"name":"私有房产"},{"id":7,"name":"期房"}],"marry":[{"id":1,"name":"未婚"},{"id":2,"name":"已婚"},{"id":3,"name":"离异"},{"id":4,"name":"再婚"},{"id":5,"name":"丧偶"}]}
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
        private List<CompanyNatureBean> companyNature;
        private List<WorkYearsBean> workYears;
        private List<SalaryBean> salary;
        private List<GuanxiBean> guanxi;
        private List<EducationBean> education;
        private List<HouseNatureBean> house_nature;
        private List<MarryBean> marry;

        public List<CompanyNatureBean> getCompanyNature() {
            return companyNature;
        }

        public void setCompanyNature(List<CompanyNatureBean> companyNature) {
            this.companyNature = companyNature;
        }

        public List<WorkYearsBean> getWorkYears() {
            return workYears;
        }

        public void setWorkYears(List<WorkYearsBean> workYears) {
            this.workYears = workYears;
        }

        public List<SalaryBean> getSalary() {
            return salary;
        }

        public void setSalary(List<SalaryBean> salary) {
            this.salary = salary;
        }

        public List<GuanxiBean> getGuanxi() {
            return guanxi;
        }

        public void setGuanxi(List<GuanxiBean> guanxi) {
            this.guanxi = guanxi;
        }

        public List<EducationBean> getEducation() {
            return education;
        }

        public void setEducation(List<EducationBean> education) {
            this.education = education;
        }

        public List<HouseNatureBean> getHouse_nature() {
            return house_nature;
        }

        public void setHouse_nature(List<HouseNatureBean> house_nature) {
            this.house_nature = house_nature;
        }

        public List<MarryBean> getMarry() {
            return marry;
        }

        public void setMarry(List<MarryBean> marry) {
            this.marry = marry;
        }

        public static class CompanyNatureBean {
            /**
             * id : 1
             * name : 国有企业
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class WorkYearsBean {
            /**
             * id : 1
             * name : 1年以下
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class SalaryBean {
            /**
             * id : 1
             * name : 1000-3000元
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class GuanxiBean {
            /**
             * id : 1
             * name : 父子
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class EducationBean {
            /**
             * id : 1
             * name : 小学
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class HouseNatureBean {
            /**
             * id : 1
             * name : 自管房产
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class MarryBean {
            /**
             * id : 1
             * name : 未婚
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
