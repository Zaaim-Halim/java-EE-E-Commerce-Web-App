package com.midvi.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.midvi.entity.Privilege;
import com.midvi.entity.Role;
import com.midvi.entity.User;


@Singleton
@LocalBean
@Startup
public class DataLoader implements DataLoaderLocal {
    
	@PersistenceContext(unitName = "E-commerceEJB")
    private EntityManager em;
	@EJB
	private RoleManagerLocal roleManager;
	@EJB
	private UserManagerLocal userManager;
	@EJB
	private PasswordAuthenticatorManagerLocal passwordAuthManager;
	
	private final String ADMIN_ROLE = " ROLE_ADMIN";
	private final String USER_ROLE = "ROLE_USER";
	//private final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";
	
	private final String WRITE_PRIVELEGE = "WRITE_PRIVILEGE";
	private final String READ_PRIVILEGE = "READ_PRIVILEGE";
	private final String CHANGE_PASSWORD_PRIVILEGE = "CHANGE_PASSWORD_PRIVILEGE";
    public DataLoader() {
      
    }
    @PostConstruct
    public void initialiser(){
      //add Roles and Privilege 
    	createAdminRoleIfnotExist();
    	creatUserRoleIfNotExist();
    	creatAppAdminIfNotExist("halim","zaaim","zaaim1halim@gmail.com","Optimisation1");
    	  	
    }
	
	private void creatAppAdminIfNotExist(String firstName, String lastName, String email, String password) {
		//System.out.println("\n"+userManager.findUserByEmail(email).toString()+"\n");
		if(userManager.findUserByEmail(email) == null)
		{
			User user = new User();
			Set<Role> roles = new HashSet<Role>();
			Role roleUser = roleManager.getRoleByName(this.USER_ROLE);
			Role roleAdmin = roleManager.getRoleByName(this.ADMIN_ROLE);
			roles.add(roleAdmin);roles.add(roleUser);
			user.setEmail(email);
			user.setNom(lastName);
			user.setPrenom(firstName);
			user.setEnabled(true);
			user.setPassword(passwordAuthManager.generateSecurePassword(password));
			user.setRoles(roles);
			em.persist(user);
		}
	}
	private void creatUserRoleIfNotExist() {
		
		 Role roleUser;
		 if (roleManager.getRoleByName(USER_ROLE) == null ) {
			roleUser = new Role(this.USER_ROLE);
			List<Privilege> userPrivileges = Arrays.asList(new Privilege(this.READ_PRIVILEGE),
					new Privilege(this.CHANGE_PASSWORD_PRIVILEGE));
			roleUser.setPrivileges(userPrivileges);
			em.persist(roleUser);
		}
		
	}
	private void createAdminRoleIfnotExist() {
		
		if (roleManager.getRoleByName(ADMIN_ROLE) == null) {
			List<Privilege> adminPrivileges = Arrays.asList(new Privilege(WRITE_PRIVELEGE),
					new Privilege(this.READ_PRIVILEGE), new Privilege(this.CHANGE_PASSWORD_PRIVILEGE));
			Role roleAdmin = new Role(this.ADMIN_ROLE);
			roleAdmin.setPrivileges(adminPrivileges);
			em.persist(roleAdmin);
		}
	}
}
