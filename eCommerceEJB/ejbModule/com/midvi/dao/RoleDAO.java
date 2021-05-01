package com.midvi.dao;

import com.midvi.entity.Role;

public interface RoleDAO extends  GenericDAO<Role, Long>{
	Role findByName(String name);
	

}
