package com.example.jifenshop.bean;

public class ResBean {
    private int status;
    private String sphone;
    private String uphone;
    private String sname;
    private String data;

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return status;
    }
    public void setSname(String sname){
        this.sname = sname;
    }
    public String getSname(){
        return sname;
    }
    public void setData(String data){
        this.data = data;
    }
    public String getData(){
        return data;
    }
}
