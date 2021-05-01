package com.midvi.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.midvi.entity.User;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null && session.getAttribute("user") != null)
		{
			System.out.println("removing a user from session ...");
			LoginController.loggedUsersFromSessionRegistry.unbound((User)(session.getAttribute("user")));
			session.invalidate();
			Cookie cookies[] = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("auth")) {
						c.setMaxAge(0);
						System.out.println("removing Cookie : " + c.getName());
						c.setValue("");
						response.addCookie(c);
					}
				} 
			}
			 	
		}
		String location = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/index";
		response.sendRedirect(location);	
	}

}
