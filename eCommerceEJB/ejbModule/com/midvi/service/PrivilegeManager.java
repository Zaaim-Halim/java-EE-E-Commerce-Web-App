package com.midvi.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.midvi.entity.Privilege;


@Stateless
@LocalBean
public class PrivilegeManager implements PrivilegeManagerLocal {

	@PersistenceContext(name = "E-commerceEJB")
    private EntityManager em;
    public PrivilegeManager() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public Privilege findByName(String name) {
		
		return null;
	}
	@Override
	public Privilege findById(Long id) {
		
		return null;
	}
	@Override
	public void deleteById(Long id) {
		
		
	}

}
