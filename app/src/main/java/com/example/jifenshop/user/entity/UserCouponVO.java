package com.example.jifenshop.user.entity;

public class UserCouponVO {
    private String gname;
    private Double price;
    private String picture;
    private Integer ugnumber;

    public Integer getUgnumber() {
        return ugnumber;
    }

    public void setUgnumber(Integer ugnumber) {
        this.ugnumber = ugnumber;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
