package com.midvi.dao;

import com.midvi.entity.User;

public interface UserDAO extends GenericDAO<User, Long>{
	User findByEmail(String email);
	User enableUser(String email, boolean bool);
	User updtateUser(User user, User oldUser);
	User enableUserById(Long id);
	

}
