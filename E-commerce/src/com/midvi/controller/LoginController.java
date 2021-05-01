package com.midvi.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.midvi.entity.User;
import com.midvi.model.AuthControl;
import com.midvi.service.ProductManagerLocal;
import com.midvi.service.UserManagerLocal;
import com.midvi.web.LoggedUsersFromSessionRegistry;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static LoggedUsersFromSessionRegistry loggedUsersFromSessionRegistry = new LoggedUsersFromSessionRegistry();
	
	@EJB 
	private UserManagerLocal userManager;
	@EJB
	private ProductManagerLocal productManager;
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/templates/Public/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession(true);
	    String email = request.getParameter("email");
	    User user = userManager.findUserByEmail(email);
	    System.out.println(user.toString());
	    if(session != null)
	    {
	    	if(request.getParameter("remember-me") != null)
	 	    {
	 	    	Cookie cookie = new Cookie("auth",user.getEmail());
	 	    	cookie.setMaxAge(60*60);
	 	    	cookie.setHttpOnly(true);
	 	    	response.addCookie(cookie);
	 	    }
	    	session.setAttribute("user", user);
	    	// test if the user is Admin or a regular user first !!
	    	session.setAttribute("authControl",new AuthControl(true, user.getEmail()) );
	    	loggedUsersFromSessionRegistry.bound(user);
	    }	
	    request.setAttribute("products", productManager.getAllProductOrderDesc());
		request.getRequestDispatcher("WEB-INF/templates/Admin/index.jsp").forward(request, response);
	}

}
