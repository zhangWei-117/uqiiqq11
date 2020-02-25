package com.cblue.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Shop {
    private Integer sid;
    private String sname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outtime;
    private Integer num;
    private String img;
    private Integer lid;
    private Type type;

    @Override
    public String toString() {
        return "Shop{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", outtime=" + outtime +
                ", num=" + num +
                ", img='" + img + '\'' +
                ", lid=" + lid +
                ", type=" + type +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getOuttime() {
        return outtime;
    }

    public void setOuttime(Date outtime) {
        this.outtime = outtime;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
