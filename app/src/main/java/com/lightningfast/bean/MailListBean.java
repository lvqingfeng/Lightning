package com.lightningfast.bean;

/**
 * 项目名称：
 * 类描述：通讯录好友的实体类
 * 创建人：吕清锋
 * 创建时间：2017/3/21 15:13
 * 修改备注：
 */
public class MailListBean {
    private String name;
    private String phoneNum;
    public MailListBean(){

    }
    public MailListBean(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
