package com.example.jifenshop.bean;

public class ReturnMessage {
    private int password;
    private int id;
    private String sphone;
    private String uphone;
    private int status;

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

    public int getPassword() { return password; }
    public void setPassword(int password) {
        this.password = password;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
    public int getStatus() { return status; }
    public void setStatus(int status) {
        this.status = status;
    }
}
