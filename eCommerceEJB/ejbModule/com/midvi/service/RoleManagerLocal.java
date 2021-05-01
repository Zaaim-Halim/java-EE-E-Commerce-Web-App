package com.midvi.service;

import java.util.List;

import javax.ejb.Local;

import com.midvi.entity.Role;

@Local
public interface RoleManagerLocal {
	public Role getRoleByName(String name);
	public Role getRoleById(Long id);
	public void deleteRoleById(Long id);
	public List<Role> getAllRoles();

}
