package com.example.jifenshop.user.entity;

import java.util.List;

public class PointsShopVO {
    private List<SimplePointsProductInfoVO> gb;
    private Integer point;

    public List<SimplePointsProductInfoVO> getData() {
        return gb;
    }

    public void setData(List<SimplePointsProductInfoVO> data) {
        this.gb = data;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}
