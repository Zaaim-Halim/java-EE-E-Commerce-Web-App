package com.midvi.service;

import javax.ejb.Local;

@Local
public interface PasswordAuthenticatorManagerLocal {
	public boolean isPaswordCorrect(String email,String providedPassword);
	public String generateSecurePassword(String password);

}
