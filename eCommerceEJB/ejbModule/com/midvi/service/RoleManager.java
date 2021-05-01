package com.midvi.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.midvi.dao.RoleDAO;
import com.midvi.entity.Role;

@Stateless
@LocalBean
public class RoleManager implements RoleManagerLocal {

	@Inject
	private RoleDAO roleDao;
	@Override
	public Role getRoleByName(String name) {
		
		return roleDao.findByName(name);
	}

	@Override
	public Role getRoleById(Long id) {
		
		return roleDao.findById(id);
	}

	@Override
	public void deleteRoleById(Long id) {
		Role role = getRoleById(id);
		roleDao.delete(role);
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDao.findAll();
	}

}
