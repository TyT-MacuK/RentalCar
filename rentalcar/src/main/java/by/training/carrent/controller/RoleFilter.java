package by.training.carrent.controller;

import java.io.IOException;

import by.training.carrent.model.entity.User;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/admin/")
public class RoleFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(true);
		User user = (User) session.getAttribute("user");                 //TODO
		if (user == null || user.getRole() != User.UserRole.ADMIN) {
			httpResponse.sendRedirect("error_page");                     //TODO
		} else {
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}
}
