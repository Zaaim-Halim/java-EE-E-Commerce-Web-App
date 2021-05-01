package com.midvi.dao;

import java.util.Date;

import com.midvi.entity.VirificationToken;

public interface VirificationTokenDAO extends GenericDAO<VirificationToken, Long>{
	VirificationToken findByToken(String token);
	void deleteVerificationTokenByDate(Date date);

}
