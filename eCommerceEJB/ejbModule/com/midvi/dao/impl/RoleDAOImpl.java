package com.midvi.dao.impl;

import javax.ejb.Singleton;
import javax.persistence.Query;

import com.midvi.dao.GenericDAOImpl;
import com.midvi.dao.RoleDAO;
import com.midvi.entity.Role;

@Singleton
public class RoleDAOImpl extends GenericDAOImpl<Role, Long>
        implements RoleDAO{

	protected RoleDAOImpl() {
		super(Role.class);
		
	}

	@Override
	public Role findByName(String name) {
		Query query = em.createQuery("SELECT r From Role r WHERE r.name='"+name+"'");
		Role role = null;
		if(!query.getResultList().isEmpty())
		{
			role = (Role) query.getResultList().get(0);
		}
		return role;
	}

}
