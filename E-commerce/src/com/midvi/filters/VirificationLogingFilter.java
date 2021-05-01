package com.midvi.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.midvi.entity.User;
import com.midvi.service.PasswordAuthenticatorManagerLocal;
import com.midvi.service.UserManagerLocal;
import com.midvi.web.EmailValidator;

@WebFilter(filterName = "VirificationLogingFilter")
public class VirificationLogingFilter implements Filter {
	
	private EmailValidator emailValidator;
	@EJB
	private PasswordAuthenticatorManagerLocal passwordAuthenticatorManager;
	@EJB 
	private UserManagerLocal userManager;
	
	public VirificationLogingFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest HTTPreq = (HttpServletRequest) request;
		String method = HTTPreq.getMethod();
		String error = null ;
		
		if (method.compareToIgnoreCase("POST") == 0) {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			//make sure we won't be using null pointer ...
            if(email == null || password == null)
            {
            	error = "type your password and  email !";
				HTTPreq.setAttribute("error", error);
				HTTPreq.getRequestDispatcher("WEB-INF/templates/Public/login.jsp").forward(request, response);
				return;
            }
			if (isLegitUser(email, password)) {
				chain.doFilter(request, response);
				return;
			} else {

				error = "user doesn't exist !";
				HTTPreq.setAttribute("error", error);
				HTTPreq.getRequestDispatcher("WEB-INF/templates/Public/login.jsp").forward(request, response);
				return;
			}
		}

		HTTPreq.getRequestDispatcher("WEB-INF/templates/Public/login.jsp").forward(request, response);

	}

	public static boolean isUserCanAuthenticateFromCookie(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		List<Cookie> cs = Arrays.asList(cookies);

		if (cookies != null) {
			for (Cookie c : cs) {
				if (c.getName().equals("auth"))
					return true;
			}
		}
		return false;
	}

	private boolean isLegitUser(String email, String password) {
		if (!validate(email))
			return false;
		if (!isAvalidUser(email, password))
			return false;

		return true;
	}
	
	private boolean isAvalidUser(String email, String providedPassword) {
		final User user = userManager.findUserByEmail(email);
		
		if (user != null) {
			boolean pwdValidation = passwordAuthenticatorManager.isPaswordCorrect(user.getPassword(), providedPassword);
			if (user.getEmail().equals(email) && pwdValidation)
				return true;
			return false;
		}
		return false;
	}

	private boolean validate(String email) {
		return emailValidator.isValid(email);

	}

	public void init(FilterConfig fConfig) throws ServletException {
		emailValidator = new EmailValidator();
	}

}
