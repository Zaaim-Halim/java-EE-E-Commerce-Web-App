package com.midvi.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.midvi.entity.User;

@WebFilter(filterName = "GlobalFilter")
public class GlobalFilter implements Filter {
	private List<String> specialURL = new ArrayList<String>();

	public GlobalFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String ApplicationName = httpRequest.getContextPath().replace("/", "");
		String url = null;
		int index = httpRequest.getRequestURI().indexOf(ApplicationName);
		index = index + ApplicationName.length();
		url = httpRequest.getRequestURI().substring(index, httpRequest.getRequestURI().length());

		if ((isSecuredView(url) && !isAuthenticatedUser(httpRequest))) {
			String message = " you have to log in first to acces this resource !";
			httpRequest.setAttribute("message", message);
			httpRequest.getRequestDispatcher("WEB-INF/templates/Public/login.jsp").forward(httpRequest, response);
			return;
		}
		if (isStaticPrivateResource(url) && !isAuthenticatedUser(httpRequest)) {
			// this a bug bcuz what to do with static reserved resource for /login
			chain.doFilter(request, response);
			return;
		}

		if (!isSecuredView(url) || !isStaticPrivateResource(url)
				|| isStaticResource(url) && isStaticPrivateResource(url)) {
			// passe to the next chain filters
			chain.doFilter(request, response);
			return;
		}
		if ((isSecuredView(url) && isAuthenticatedUser(httpRequest))) {

			chain.doFilter(request, response);
			return;
		}

	}

	private boolean isAuthenticatedUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			if ((User) (session.getAttribute("user")) != null
					|| VirificationLogingFilter.isUserCanAuthenticateFromCookie(request)) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
		initSpecialURL();
		
	}

	private boolean isSecuredView(String url) {
		if (url.startsWith("/Admin") || url.startsWith("/Client")) {
			return true;
		}
		return false;
	}

	protected boolean isStaticPrivateResource(String uri) {
		if (uri.contains("static/Admin") || uri.contains("static/Client"))
			return true;
		return false;
	}

	protected boolean isStaticResource(String uri) {
		if (uri.contains("static"))
			return true;
		return false;
	}

	protected void initSpecialURL() {
		specialURL.add("/login");
		specialURL.add("/registration");
		specialURL.add("/bad-user");
		specialURL.add("/error");
		specialURL.add("invalideSession");
		specialURL.add("changePassword");
	}
}
