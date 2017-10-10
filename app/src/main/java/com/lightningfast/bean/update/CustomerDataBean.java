package com.lightningfast.bean.update;

import java.io.Serializable;

/**
 * 作者:吕清锋
 * 时间:2017/8/26
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class CustomerDataBean implements Serializable {
    private String cid;
    private String uid;
    private String order_id;
    private String remark;
    private String address;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
