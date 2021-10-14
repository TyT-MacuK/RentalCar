package by.training.carrent.controller.filter;

import static by.training.carrent.controller.command.SessionAttribute.IS_AUTHENTICATED;
import static by.training.carrent.controller.command.SessionAttribute.LOCALE;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "locale", value = "en", description = "Default language"),
		@WebInitParam(name = "isAuthenticated", value = "false", description = "User is not authenticated") })
public class InitializeDefaultValuesFilter implements Filter {
	private static final String ENGLISH = "en";
	String locale;
	boolean isAuthenticated;

	public void init(FilterConfig fConfig) throws ServletException {
		locale = fConfig.getInitParameter(LOCALE);
		isAuthenticated = Boolean.parseBoolean(fConfig.getInitParameter(IS_AUTHENTICATED));
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		if (session.getAttribute(LOCALE) == null) {
			session.setAttribute(LOCALE, ENGLISH);
		}
		if (session.getAttribute(IS_AUTHENTICATED) == null) {
			session.setAttribute(IS_AUTHENTICATED, false);
		}
		chain.doFilter(request, response);

	}

	public void destroy() {
		locale = null;
		isAuthenticated = false;
	}
}
