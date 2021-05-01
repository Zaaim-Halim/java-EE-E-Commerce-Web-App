package com.midvi.dao.impl;

import javax.ejb.Singleton;
import javax.persistence.Query;

import com.midvi.dao.GenericDAOImpl;
import com.midvi.dao.UserDAO;
import com.midvi.entity.User;

@Singleton
public class UserDAOImpl extends GenericDAOImpl<User, Long>
     implements UserDAO{

	protected UserDAOImpl() {
		super(User.class);
		
	}

	@Override
	public User findByEmail(String email) {
		User user = null;
		Query query = em.createQuery("SELECT u FROM User u WHERE u.email ='"+email+"'");
		
		if(!query.getResultList().isEmpty())
		{
			user =  (User) query.getResultList().get(0);
		}
		return user;
	}

	@Override
	public User enableUser(String email, boolean bool) {
		User user = em.find(User.class, email);
		em.getTransaction().begin();
		user.setEnabled(bool);
		em.getTransaction().commit();
		return user;
	}

	@Override
	public User updtateUser(User user, User OldUser) {
		OldUser.setEmail(user.getEmail());
		OldUser.setNom(user.getNom());
		OldUser.setPrenom(user.getPrenom());
		OldUser.setRoles(user.getRoles());
        em.merge(OldUser);
		return OldUser;
	}

	@Override
	public User enableUserById(Long id) {
		User user = em.find(User.class, id);
		user.setEnabled(true);
		em.merge(user);
		return user;
	}

}
