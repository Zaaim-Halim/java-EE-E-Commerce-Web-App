package com.midvi.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import com.midvi.dao.UserDAO;
import com.midvi.dao.VirificationTokenDAO;
import com.midvi.entity.Role;
import com.midvi.entity.User;
import com.midvi.event.OnRegistrationCompleteEvent;

@Stateless
@LocalBean
public class UserManager implements UserManagerRemote, UserManagerLocal {
	@Inject
	private UserDAO userDao;
	@EJB
	private RoleManagerLocal roleManager;
	@EJB
	private VirificationTokenDAO verificationTokenDao;
	// for event firing **************************************
	@Inject
	private Event<OnRegistrationCompleteEvent> eventPublisher;

	//********************************************************
    public UserManager() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public User findUserByEmail(String email) {
		
		return userDao.findByEmail(email);
	}

	@Override
	public User findUserById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public void deleteUserById(Long id) {
		User user = findUserById(id);
		userDao.delete(user);
	}

	@Override
	public void deleteUserByEmail(String email) {
		User user = findUserByEmail(email);
		userDao.delete(user);
		
	}

	@Override
	public User updateUser(User user) {
		User OldUser = userDao.findByEmail(user.getEmail());
		return userDao.updtateUser(user, OldUser);
	}

	@Override
	public User enableUser(Long id) {
		
		return userDao.enableUserById(id);
	}

	@Override
	public void registerNewUser(User user,String appURL) {
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleManager.getRoleByName("USER_ROLE"));
		user.setRoles(roles);
		user.setEnabled(false);
		// create a token for the user 
		//eventPublisher.fire(new OnRegistrationCompleteEvent(user,UUID.randomUUID().toString(),appURL));
		userDao.save(user);
		
	}

	@Override
	public boolean verifyNewUserRegistration(User user, String token) {
		if(verificationTokenDao.findByToken(token) != null)
			return true;
	    return false;
		
	}

	@Override
	public List<User> getAllUsers() {
		
		return userDao.findAll();
	}

}
