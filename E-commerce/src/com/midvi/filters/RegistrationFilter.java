package com.midvi.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.midvi.web.EmailValidator;
import com.midvi.web.PasswordValidator;

@WebFilter(filterName = "RegistrationFilter")
public class RegistrationFilter implements Filter {
	
	private EmailValidator emailValidator;
    private PasswordValidator passwordValidator;
    
	public RegistrationFilter() {

	}
	public void destroy() {

	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httprequest = (HttpServletRequest) request;
		String name, lastName, email, password, confirmedPassword;
		
		if (httprequest.getMethod().compareToIgnoreCase("POST") == 0) {
			name = request.getParameter("nom");
			lastName = request.getParameter("prenom");
			email = request.getParameter("email");
			password = request.getParameter("password");
			confirmedPassword = request.getParameter("passwordConfirm");
			if (name == null || lastName == null || password == null || confirmedPassword == null || email == null) {
				// forward an error message ... stating there is a filed empty
				
				System.out.println("one parameter is null!");
				request.getRequestDispatcher("WEB-INF/templates/Public/registration.jsp").forward(request, response);
				return;
			}
			if (!emailValidator.isValid(email)) {
				// forward an error message stating the email is not a valid email ;
				System.out.println("email not valid!");
				request.getRequestDispatcher("WEB-INF/templates/Public/registration.jsp").forward(request, response);
				return;
			}
			if (!isPasswordMatch(password, confirmedPassword)) {
				//password doesn't match
				System.out.println("password doesn't match !");
				request.getRequestDispatcher("WEB-INF/templates/Public/registration.jsp").forward(request, response);
				return;
			}
			// must be changed !!! bcuz it is very hard to give a valid pswd
			
			if (!passwordValidator.isValid(password)) {
				// invalid password
				System.out.println("invalid password! : " +password );
				
				request.getRequestDispatcher("WEB-INF/templates/Public/registration.jsp").forward(request, response);
				return;
			}
			chain.doFilter(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean isPasswordMatch(String password, String confirmedPassword) {
		if (password.equals(confirmedPassword))
			return true;
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		emailValidator = new EmailValidator();
		passwordValidator = new PasswordValidator();
	}

}
