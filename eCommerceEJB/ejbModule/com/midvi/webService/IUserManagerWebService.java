package com.midvi.webService;

import javax.ejb.Local;

import com.midvi.entity.User;

@Local
public interface IUserManagerWebService {
	public User findUserByEmail(String email);
	User findUserById(Long id);
	public void registerNewUser(User user, String appUrl);
	public boolean isUserExist(String email);
	public boolean canAuthenticate(String email,String password);
   
}
