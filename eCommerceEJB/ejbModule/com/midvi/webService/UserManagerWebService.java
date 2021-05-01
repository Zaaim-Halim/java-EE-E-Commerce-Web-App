package com.midvi.webService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.midvi.dao.UserDAO;
import com.midvi.entity.User;
import com.midvi.service.PasswordAuthenticatorManager;

@Stateless
@Path("users")
public class UserManagerWebService implements IUserManagerWebService {
    @Inject
    private UserDAO userDao;
    @Inject
    private PasswordAuthenticatorManager passwordAuthenticatorManager;
	@Override
	@GET
	@Path("/user/{email}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public User findUserByEmail(@PathParam("email") String email) {
		return userDao.findByEmail(email);
	}

	@Override
	@GET
	@Path("/user/{id}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public User findUserById(@PathParam("id")Long id) {
		
		return userDao.findById(id);
	}

	
	@POST
	@Path("/addUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerNewUser(User user, String appUrl) {
		userDao.save(user);
	}

	@Override
	public boolean isUserExist(String email) {
		if(userDao.findByEmail(email) != null)
			return true;
		return false;
	}

	@Override
	@POST
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean canAuthenticate(@FormParam("email") String email, @FormParam("password") String password) {
		User user = userDao.findByEmail(email);
		if(user != null)
		{
			if(!passwordAuthenticatorManager.verifyUserPassword(password, user.getPassword()))
				return false;
			else
				return true;
		}
		else
			return false;
		
	}

	
}
