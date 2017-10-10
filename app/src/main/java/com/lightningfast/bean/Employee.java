package com.lightningfast.bean;

import java.io.Serializable;

/**
 * 作者:吕清锋
 * 时间:2017/8/19
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class Employee implements Serializable {
    /**
     * uid 个人ID
     * phone手机号,账号
     * password 密码
     */
    private String token;
    private String uid;
    private String phone;
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
