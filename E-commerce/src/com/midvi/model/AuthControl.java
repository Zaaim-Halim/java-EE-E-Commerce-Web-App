package com.midvi.model;

public class AuthControl {
	private boolean admin;
	private String msg;
	public AuthControl() {
		
	}
	public AuthControl(boolean admin, String msg) {
		super();
		this.admin = admin;
		this.msg = msg;
	}
	public boolean isadmin() {
		return admin;
	}
	public void setadmin(boolean admin) {
		this.admin = admin;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
