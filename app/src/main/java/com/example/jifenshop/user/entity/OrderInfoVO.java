package com.example.jifenshop.user.entity;

public class OrderInfoVO {
    private String gname;
    private  Double price;
    private Integer number;
    private String okind;
    private Integer oid;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getOkind() {
        return okind;
    }

    public void setOkind(String okind) {
        this.okind = okind;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer id) {
        this.oid = id;
    }
}
