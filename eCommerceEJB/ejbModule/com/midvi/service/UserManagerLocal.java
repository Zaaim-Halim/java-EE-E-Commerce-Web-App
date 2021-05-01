package com.midvi.service;

import java.util.List;

import javax.ejb.Local;
import com.midvi.entity.User;

@Local
public interface UserManagerLocal {
	public User findUserByEmail(String email);
	public User findUserById(Long id);
	public void deleteUserById(Long id);
	public void deleteUserByEmail(String email);
	public User updateUser(User user);
	public User enableUser(Long id);
	//registration 
	public void registerNewUser(User user, String appURL);
	public boolean verifyNewUserRegistration(User user, String token);
	public List<User> getAllUsers();

}
