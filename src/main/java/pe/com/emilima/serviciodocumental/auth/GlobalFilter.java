package pe.com.emilima.serviciodocumental.auth;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.emilima.serviciodocumental.dto.User;
import pe.com.emilima.serviciodocumental.util.security.SecurityUtils;

@WebFilter(filterName = "globalFilter", dispatcherTypes = {
		DispatcherType.REQUEST }, description = "Filter for verify if a user is authenticated", urlPatterns = { "/*" })
public class GlobalFilter extends HttpFilter implements Filter {
	private final Logger logger = Logger.getLogger(GlobalFilter.class.getName());
	private static final long serialVersionUID = 1L;

	public GlobalFilter() {
		super();
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String requestURI = httpServletRequest.getRequestURI();
		String servletPath = httpServletRequest.getServletPath();

		logger.log(Level.INFO, "--- FILTER: AuthFilter --- URI: {0}", requestURI);

		if (isAuthenticatedUser(httpServletRequest) && servletPath.equals("/login")) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");

			return;
		}

		if (servletPath.equals("/login")) {
			chain.doFilter(request, response);

			return;
		}

		if (!isAuthenticatedUser(httpServletRequest) && !isResource(requestURI)) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");

			return;
		}

		if (isAuthenticatedUser(httpServletRequest)) {
			chain.doFilter(httpServletRequest, httpServletResponse);

			return;
		}

		if (isResource(requestURI)) {
			chain.doFilter(httpServletRequest, httpServletResponse);

			return;
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public boolean isAuthenticatedUser(HttpServletRequest request) {
		User user = SecurityUtils.getLoginedUser(request.getSession());

		if (user != null) {
			return true;
		}

		return false;
	}

	public boolean isResource(String uri) {
		if (uri.contains("/resources")) {
			return true;
		}

		return false;
	}

}
