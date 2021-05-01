package com.midvi.event;

import javax.inject.Named;

import com.midvi.entity.User;

@Named
public class OnRegistrationCompleteEvent {
	private User user;
    private String token;
    private String appAdress;
    
	public String getAppAdress() {
		return appAdress;
	}

	public void setAppAdress(String appAdress) {
		this.appAdress = appAdress;
	}

	public OnRegistrationCompleteEvent(User user) {
		super();
		this.user = user;
	}
  
	public OnRegistrationCompleteEvent(User user, String token, String appAdress) {
		super();
		this.user = user;
		this.token = token;
		this.appAdress = appAdress;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
