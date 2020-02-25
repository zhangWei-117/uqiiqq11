package com.cblue.domain;

public class Type {
    private Integer lid;
    private String lname;

    @Override
    public String toString() {
        return "Type{" +
                "lid=" + lid +
                ", lname='" + lname + '\'' +
                '}';
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
