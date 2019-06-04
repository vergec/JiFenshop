package com.example.jifenshop.bean;

public class NewdingdanBean {
//    private int gid;
    private String gname;
    private int sellnum;
    private double price;
    private int number;
    private String picture;
    private String okind;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public int getSellnum() {
        return sellnum;
    }

    public void setSellnum(int sellnum) {
        this.sellnum = sellnum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOkind() {
        return okind;
    }

    public void setOkind(String okind) {
        this.okind = okind;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
