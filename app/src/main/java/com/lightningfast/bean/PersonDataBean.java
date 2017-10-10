package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/7/14
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class PersonDataBean {

    /**
     * code : 1
     * msg : 查询成功!
     * data : {"customer":{"customer_id":1545,"customer_name":"吕","gender":1,"card_address":"北京北京朝阳区PK哦YY","id_card":"622822199005163918","bank":"建行","bank_number":"6217004260001672047","credit_score":0,"employee_id":5,"gender_str":"男","employee_mobile":"15909581102"}}
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
         * customer : {"customer_id":1545,"customer_name":"吕","gender":1,"card_address":"北京北京朝阳区PK哦YY","id_card":"622822199005163918","bank":"建行","bank_number":"6217004260001672047","credit_score":0,"employee_id":5,"gender_str":"男","employee_mobile":"15909581102"}
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
             * customer_id : 1545
             * customer_name : 吕
             * gender : 1
             * card_address : 北京北京朝阳区PK哦YY
             * id_card : 622822199005163918
             * bank : 建行
             * bank_number : 6217004260001672047
             * credit_score : 0
             * employee_id : 5
             * gender_str : 男
             * employee_mobile : 15909581102
             */

            private int customer_id;
            private String customer_name;
            private int gender;
            private String card_address;
            private String id_card;
            private String bank;
            private String bank_number;
            private String credit_score;
            private String employee_id;
            private String gender_str;
            private String employee_mobile;
            private String card_end_date;
            private String bank_telephone_no;
            private String bank_subbranch;
            private String bank_city;
            private String cert_expire;
            private int is_order;

            public int getIs_order() {
                return is_order;
            }

            public void setIs_order(int is_order) {
                this.is_order = is_order;
            }

            public String getCert_expire() {
                return cert_expire;
            }

            public void setCert_expire(String cert_expire) {
                this.cert_expire = cert_expire;
            }

            public String getBank_city() {
                return bank_city;
            }

            public void setBank_city(String bank_city) {
                this.bank_city = bank_city;
            }

            public String getBank_telephone_no() {
                return bank_telephone_no;
            }

            public void setBank_telephone_no(String bank_telephone_no) {
                this.bank_telephone_no = bank_telephone_no;
            }

            public String getBank_subbranch() {
                return bank_subbranch;
            }

            public void setBank_subbranch(String bank_subbranch) {
                this.bank_subbranch = bank_subbranch;
            }

            public String getCard_end_date() {
                return card_end_date;
            }

            public void setCard_end_date(String card_end_date) {
                this.card_end_date = card_end_date;
            }

            public int getCustomer_id() {
                return customer_id;
            }

            public void setCustomer_id(int customer_id) {
                this.customer_id = customer_id;
            }

            public String getCustomer_name() {
                return customer_name;
            }

            public void setCustomer_name(String customer_name) {
                this.customer_name = customer_name;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getCard_address() {
                return card_address;
            }

            public void setCard_address(String card_address) {
                this.card_address = card_address;
            }

            public String getId_card() {
                return id_card;
            }

            public void setId_card(String id_card) {
                this.id_card = id_card;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
            }

            public String getBank_number() {
                return bank_number;
            }

            public void setBank_number(String bank_number) {
                this.bank_number = bank_number;
            }

            public String getCredit_score() {
                return credit_score;
            }

            public void setCredit_score(String credit_score) {
                this.credit_score = credit_score;
            }

            public String getEmployee_id() {
                return employee_id;
            }

            public void setEmployee_id(String employee_id) {
                this.employee_id = employee_id;
            }

            public String getGender_str() {
                return gender_str;
            }

            public void setGender_str(String gender_str) {
                this.gender_str = gender_str;
            }

            public String getEmployee_mobile() {
                return employee_mobile;
            }

            public void setEmployee_mobile(String employee_mobile) {
                this.employee_mobile = employee_mobile;
            }
        }
    }
}
