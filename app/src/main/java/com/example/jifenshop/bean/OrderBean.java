package com.example.jifenshop.bean;

import java.sql.Date;

public class OrderBean {
	private int id;
	private int uid;
	private int gid;
	private String color;
	private int size;
	private int number;
	private String address;
	private Date odate;
	private String okind;
	public int getOid() {
		return id;
	}
	public void setOid(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getOdate() {
		return odate;
	}
	public void setOdate(Date odate) {
		this.odate = odate;
	}
	public String getOkind() {
		return okind;
	}
	public void setOkind(String okind) {
		this.okind = okind;
	}

}
