package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/7/28
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class PersonInfoBean {

    /**
     * code : 1
     * msg : 查询成功!
     * data : {"customer":{"customer_id":1514,"academic":5,"marry":1,"residence":1,"is_car":1,"live_address_nature":1,"live_address":"erminyuan","provice_id":20,"city_id":270,"area_id":2263,"area_str":"宁夏银川西夏区","education_str":"高职","marry_str":"未婚","house_nature_str":"自管房产","car_str":"有","residence_str":"农业户口"}}
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
         * customer : {"customer_id":1514,"academic":5,"marry":1,"residence":1,"is_car":1,"live_address_nature":1,"live_address":"erminyuan","provice_id":20,"city_id":270,"area_id":2263,"area_str":"宁夏银川西夏区","education_str":"高职","marry_str":"未婚","house_nature_str":"自管房产","car_str":"有","residence_str":"农业户口"}
         */

        private CustomerBean customer;

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
        }

        public static class CustomerBean {
            /**
             * customer_id : 1514
             * academic : 5
             * marry : 1
             * residence : 1
             * is_car : 1
             * live_address_nature : 1
             * live_address : erminyuan
             * provice_id : 20
             * city_id : 270
             * area_id : 2263
             * area_str : 宁夏银川西夏区
             * education_str : 高职
             * marry_str : 未婚
             * house_nature_str : 自管房产
             * car_str : 有
             * residence_str : 农业户口
             */

            private int customer_id;
            private int academic;
            private int marry;
            private int residence;
            private int is_car;
            private int live_address_nature;
            private String live_address;
            private int provice_id;
            private int city_id;
            private int area_id;
            private String area_str;
            private String education_str;
            private String marry_str;
            private String house_nature_str;
            private String car_str;
            private String residence_str;

            public int getCustomer_id() {
                return customer_id;
            }

            public void setCustomer_id(int customer_id) {
                this.customer_id = customer_id;
            }

            public int getAcademic() {
                return academic;
            }

            public void setAcademic(int academic) {
                this.academic = academic;
            }

            public int getMarry() {
                return marry;
            }

            public void setMarry(int marry) {
                this.marry = marry;
            }

            public int getResidence() {
                return residence;
            }

            public void setResidence(int residence) {
                this.residence = residence;
            }

            public int getIs_car() {
                return is_car;
            }

            public void setIs_car(int is_car) {
                this.is_car = is_car;
            }

            public int getLive_address_nature() {
                return live_address_nature;
            }

            public void setLive_address_nature(int live_address_nature) {
                this.live_address_nature = live_address_nature;
            }

            public String getLive_address() {
                return live_address;
            }

            public void setLive_address(String live_address) {
                this.live_address = live_address;
            }

            public int getProvice_id() {
                return provice_id;
            }

            public void setProvice_id(int provice_id) {
                this.provice_id = provice_id;
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

            public String getArea_str() {
                return area_str;
            }

            public void setArea_str(String area_str) {
                this.area_str = area_str;
            }

            public String getEducation_str() {
                return education_str;
            }

            public void setEducation_str(String education_str) {
                this.education_str = education_str;
            }

            public String getMarry_str() {
                return marry_str;
            }

            public void setMarry_str(String marry_str) {
                this.marry_str = marry_str;
            }

            public String getHouse_nature_str() {
                return house_nature_str;
            }

            public void setHouse_nature_str(String house_nature_str) {
                this.house_nature_str = house_nature_str;
            }

            public String getCar_str() {
                return car_str;
            }

            public void setCar_str(String car_str) {
                this.car_str = car_str;
            }

            public String getResidence_str() {
                return residence_str;
            }

            public void setResidence_str(String residence_str) {
                this.residence_str = residence_str;
            }
        }
    }
}
