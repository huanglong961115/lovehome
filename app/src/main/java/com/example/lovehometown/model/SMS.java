package com.example.lovehometown.model;

/**
 * Created by Administrator on 2016/7/20.
 */
public class SMS {
    /**
     * code : 0
     * count : 1
     * fee : 0.055
     * mobile : 13332598897
     * msg : 发送成功
     * sid : 8827036176
     * unit : RMB
     */

    private int code;
    private int count;
    private double fee;
    private String mobile;
    private String msg;
    private long sid;
    private String unit;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
