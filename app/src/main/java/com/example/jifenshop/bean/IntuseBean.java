package com.example.jifenshop.bean;

import java.sql.Date;

public class IntuseBean {
	private int iid;
	private int uid;
	private Date idate;
	private double inumber;
	private double last;
	private String use;
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Date getIdate() {
		return idate;
	}
	public void setIdate(Date idate) {
		this.idate = idate;
	}
	public double getInumber() {
		return inumber;
	}
	public void setInumber(double inumber) {
		this.inumber = inumber;
	}
	public double getLast() {
		return last;
	}
	public void setLast(double last) {
		this.last = last;
	}
	public String getUse() {
		return use;
	}
	public void setUse(String use) {
		this.use = use;
	}

}
