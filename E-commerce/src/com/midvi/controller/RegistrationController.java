
package com.midvi.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.midvi.entity.User;
import com.midvi.service.PasswordAuthenticatorManagerLocal;
import com.midvi.service.UserManagerLocal;
import com.midvi.web.GenericResponse;

@WebServlet({"/registration","/verifyRegistration"})
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB 
	private UserManagerLocal userManager;
	@EJB
	private PasswordAuthenticatorManagerLocal passwordAuthenticatorManager;
	
    public RegistrationController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		if(token != null)
		{
			// verify registration ....
		}
		else {
			request.getRequestDispatcher("WEB-INF/templates/Public/registration.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		
		if (userManager.findUserByEmail(request.getParameter("email")) == null) {
			user.setEmail(request.getParameter("email"));
			user.setNom(request.getParameter("nom"));
			user.setPrenom(request.getParameter("prenom"));
			user.setPassword(passwordAuthenticatorManager.generateSecurePassword(request.getParameter("password")));
			userManager.registerNewUser(user,getAppUrl(request));
			//add a succes message
			GenericResponse genericresponse = new GenericResponse("registration success log in !", 200);
			request.setAttribute("response", genericresponse);
			request.getRequestDispatcher("WEB-INF/templates/Public/registration.jsp").forward(request, response);
			return;
		}
		GenericResponse genericresponse = new GenericResponse("email already exist!", 200);
		request.setAttribute("response", genericresponse);
		request.getRequestDispatcher("WEB-INF/templates/Public/registration.jsp").forward(request, response);
	}

	
	//No API
		private String getAppUrl(HttpServletRequest request) {
			return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}
}
