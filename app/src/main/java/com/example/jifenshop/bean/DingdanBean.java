package com.example.jifenshop.bean;

import java.util.List;

public class DingdanBean {
    private int status;
    private List<NewdingdanBean> data;
    public List<NewdingdanBean> getData() {
        return data;
    }

    public void setData(List<NewdingdanBean> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
