package com.midvi.service;

import javax.ejb.Remote;
import com.midvi.entity.User;

@Remote
public interface UserManagerRemote {
	public User findUserByEmail(String email);
	public User findUserById(Long id);
	public void deleteUserById(Long id);
	public void deleteUserByEmail(String email);
	public User updateUser(User user);
	public User enableUser(Long id);
	
	//registration 
	public void registerNewUser(User user,String appUrl);
	public boolean verifyNewUserRegistration(User user, String token);

}
