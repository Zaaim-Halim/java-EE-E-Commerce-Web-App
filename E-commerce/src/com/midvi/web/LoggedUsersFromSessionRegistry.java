package com.midvi.web;

import java.util.ArrayList;
import java.util.List;

import com.midvi.entity.User;

public  class LoggedUsersFromSessionRegistry {
	List<User> usersFromSession; 
	
	
	public LoggedUsersFromSessionRegistry() {
		usersFromSession = new ArrayList<>();
	}

	public void bound(User user) {
		this.usersFromSession.add(user);
	}
	
	public void unbound(User user)
	{
		if (!this.usersFromSession.isEmpty()) {
			int index = usersFromSession.indexOf(user);
			this.usersFromSession.remove(index);
		}
	}

}
