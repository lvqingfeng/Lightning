package com.lightningfast.bean;

/**
 * 作者:吕清锋
 * 时间:2017/8/7
 * 版本:v1.0
 * 类描述：
 * 修改时间:
 */

public class CallInfo {
    public String name;
    public String number; // 号码
    public long date;     // 日期
    public int type;      // 类型：来电、去电、未接
    public String duration;
    private static CallInfo singleton=null;
    public static synchronized CallInfo getInstance(String name, String number, long date, int type, String duration){
//        if(singleton==null){
//            singleton = new CallInfo(name,number,date,type,duration);
//        }
        singleton = new CallInfo(name,number,date,type,duration);
        return singleton;
    }

    public CallInfo(String name, String number, long date, int type, String duration) {
        this.name = name;
        this.number = number;
        this.date = date;
        this.type = type;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
