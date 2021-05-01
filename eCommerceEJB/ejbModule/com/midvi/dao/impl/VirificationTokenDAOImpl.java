package com.midvi.dao.impl;

import java.util.Date;

import javax.ejb.Singleton;
import javax.persistence.Query;

import com.midvi.dao.GenericDAOImpl;
import com.midvi.dao.VirificationTokenDAO;
import com.midvi.entity.VirificationToken;

@Singleton
public class VirificationTokenDAOImpl  extends GenericDAOImpl<VirificationToken, Long>
   implements VirificationTokenDAO{

	protected VirificationTokenDAOImpl() {
		super(VirificationToken.class);
		
	}

	@Override
	public VirificationToken findByToken(String token) {
		Query query =  em.createQuery("SELECT vt From VirificationToken vt WHERE vt.token='"+token+"'"); 
		return (VirificationToken)query.getSingleResult();
	}

	@Override
	public void deleteVerificationTokenByDate(Date date) {
		Query query =  em.createQuery("SELECT vt From VirificationToken vt WHERE vt.date='"+date+"'"); 
		VirificationToken virificationToken = (VirificationToken) query.getSingleResult();
		delete(virificationToken);
		
	}	

}
