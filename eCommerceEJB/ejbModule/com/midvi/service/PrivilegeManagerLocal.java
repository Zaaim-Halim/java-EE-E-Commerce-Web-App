package com.midvi.service;

import javax.ejb.Local;

import com.midvi.entity.Privilege;

@Local
public interface PrivilegeManagerLocal {
	public Privilege findByName(String name);
	public Privilege findById(Long id);
	public void deleteById(Long id);

}
