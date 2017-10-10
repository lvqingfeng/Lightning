package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/7/18
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class LoginBean {
    /**
     * code : 1
     * msg : 登录成功!
     * data : {"customer_id":1507,"nick_name":"root","customer_name":null,"password":null,"mobile":"13709592201","mobile2":null,"server_password":null,"server_verify":null,"id_card":null,"bank":null,"bank_number":null,"reg_time":null,"update_time":null,"status":null,"employee_id":null,"credit_score":null,"wx_openid":null,"discount_credit":null,"id_card_front_img":null,"id_card_after_img":null,"bank_img":null,"person_avatar_card":null,"live_address":null,"card_address":null,"card_end_date":null,"gender":null,"birthday":null,"nation":null,"academic":null,"marry":null,"live_address_nature":null,"headimgurl":null,"is_certification":0,"provice_id":null,"city_id":null,"area_id":null,"token":"414e9620fd410632335881bc8f98c632","token_expire_time":"2017-08-02 17:10:55","last_login_ip":"192.168.1.1","last_login_time":"2017-07-18 17:10:55"}
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
         * customer_id : 1507
         * nick_name : root
         * customer_name : null
         * password : null
         * mobile : 13709592201
         * mobile2 : null
         * server_password : null
         * server_verify : null
         * id_card : null
         * bank : null
         * bank_number : null
         * reg_time : null
         * update_time : null
         * status : null
         * employee_id : null
         * credit_score : null
         * wx_openid : null
         * discount_credit : null
         * id_card_front_img : null
         * id_card_after_img : null
         * bank_img : null
         * person_avatar_card : null
         * live_address : null
         * card_address : null
         * card_end_date : null
         * gender : null
         * birthday : null
         * nation : null
         * academic : null
         * marry : null
         * live_address_nature : null
         * headimgurl : null
         * is_certification : 0
         * provice_id : null
         * city_id : null
         * area_id : null
         * token : 414e9620fd410632335881bc8f98c632
         * token_expire_time : 2017-08-02 17:10:55
         * last_login_ip : 192.168.1.1
         * last_login_time : 2017-07-18 17:10:55
         */

        private String customer_id;
        private String nick_name;
        private Object customer_name;
        private Object password;
        private String mobile;
        private Object mobile2;
        private Object server_password;
        private Object server_verify;
        private Object id_card;
        private Object bank;
        private Object bank_number;
        private Object reg_time;
        private Object update_time;
        private Object status;
        private Object employee_id;
        private Object credit_score;
        private Object wx_openid;
        private Object discount_credit;
        private Object id_card_front_img;
        private Object id_card_after_img;
        private Object bank_img;
        private Object person_avatar_card;
        private Object live_address;
        private Object card_address;
        private Object card_end_date;
        private Object gender;
        private Object birthday;
        private Object nation;
        private Object academic;
        private Object marry;
        private Object live_address_nature;
        private Object headimgurl;
        private int is_certification;
        private Object provice_id;
        private Object city_id;
        private Object area_id;
        private String token;
        private String token_expire_time;
        private String last_login_ip;
        private String last_login_time;

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public Object getCustomer_name() {
            return customer_name;
        }

        public void setCustomer_name(Object customer_name) {
            this.customer_name = customer_name;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getMobile2() {
            return mobile2;
        }

        public void setMobile2(Object mobile2) {
            this.mobile2 = mobile2;
        }

        public Object getServer_password() {
            return server_password;
        }

        public void setServer_password(Object server_password) {
            this.server_password = server_password;
        }

        public Object getServer_verify() {
            return server_verify;
        }

        public void setServer_verify(Object server_verify) {
            this.server_verify = server_verify;
        }

        public Object getId_card() {
            return id_card;
        }

        public void setId_card(Object id_card) {
            this.id_card = id_card;
        }

        public Object getBank() {
            return bank;
        }

        public void setBank(Object bank) {
            this.bank = bank;
        }

        public Object getBank_number() {
            return bank_number;
        }

        public void setBank_number(Object bank_number) {
            this.bank_number = bank_number;
        }

        public Object getReg_time() {
            return reg_time;
        }

        public void setReg_time(Object reg_time) {
            this.reg_time = reg_time;
        }

        public Object getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(Object update_time) {
            this.update_time = update_time;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getEmployee_id() {
            return employee_id;
        }

        public void setEmployee_id(Object employee_id) {
            this.employee_id = employee_id;
        }

        public Object getCredit_score() {
            return credit_score;
        }

        public void setCredit_score(Object credit_score) {
            this.credit_score = credit_score;
        }

        public Object getWx_openid() {
            return wx_openid;
        }

        public void setWx_openid(Object wx_openid) {
            this.wx_openid = wx_openid;
        }

        public Object getDiscount_credit() {
            return discount_credit;
        }

        public void setDiscount_credit(Object discount_credit) {
            this.discount_credit = discount_credit;
        }

        public Object getId_card_front_img() {
            return id_card_front_img;
        }

        public void setId_card_front_img(Object id_card_front_img) {
            this.id_card_front_img = id_card_front_img;
        }

        public Object getId_card_after_img() {
            return id_card_after_img;
        }

        public void setId_card_after_img(Object id_card_after_img) {
            this.id_card_after_img = id_card_after_img;
        }

        public Object getBank_img() {
            return bank_img;
        }

        public void setBank_img(Object bank_img) {
            this.bank_img = bank_img;
        }

        public Object getPerson_avatar_card() {
            return person_avatar_card;
        }

        public void setPerson_avatar_card(Object person_avatar_card) {
            this.person_avatar_card = person_avatar_card;
        }

        public Object getLive_address() {
            return live_address;
        }

        public void setLive_address(Object live_address) {
            this.live_address = live_address;
        }

        public Object getCard_address() {
            return card_address;
        }

        public void setCard_address(Object card_address) {
            this.card_address = card_address;
        }

        public Object getCard_end_date() {
            return card_end_date;
        }

        public void setCard_end_date(Object card_end_date) {
            this.card_end_date = card_end_date;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getBirthday() {
            return birthday;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public Object getNation() {
            return nation;
        }

        public void setNation(Object nation) {
            this.nation = nation;
        }

        public Object getAcademic() {
            return academic;
        }

        public void setAcademic(Object academic) {
            this.academic = academic;
        }

        public Object getMarry() {
            return marry;
        }

        public void setMarry(Object marry) {
            this.marry = marry;
        }

        public Object getLive_address_nature() {
            return live_address_nature;
        }

        public void setLive_address_nature(Object live_address_nature) {
            this.live_address_nature = live_address_nature;
        }

        public Object getHeadimgurl() {
            return headimgurl;
        }

        public void setHeadimgurl(Object headimgurl) {
            this.headimgurl = headimgurl;
        }

        public int getIs_certification() {
            return is_certification;
        }

        public void setIs_certification(int is_certification) {
            this.is_certification = is_certification;
        }

        public Object getProvice_id() {
            return provice_id;
        }

        public void setProvice_id(Object provice_id) {
            this.provice_id = provice_id;
        }

        public Object getCity_id() {
            return city_id;
        }

        public void setCity_id(Object city_id) {
            this.city_id = city_id;
        }

        public Object getArea_id() {
            return area_id;
        }

        public void setArea_id(Object area_id) {
            this.area_id = area_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken_expire_time() {
            return token_expire_time;
        }

        public void setToken_expire_time(String token_expire_time) {
            this.token_expire_time = token_expire_time;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }
    }
}
