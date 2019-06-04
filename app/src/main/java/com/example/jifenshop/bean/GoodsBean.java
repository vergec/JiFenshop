package com.example.jifenshop.bean;

import java.util.List;

public class GoodsBean {
	private int status;
	private List<NewgoodsBean> data;
	public List<NewgoodsBean> getData() {
		return data;
	}

	public void setData(List<NewgoodsBean> data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
