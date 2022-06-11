package pe.com.emilima.serviciodocumental.auth;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.emilima.serviciodocumental.dto.User;
import pe.com.emilima.serviciodocumental.util.security.SecurityUtils;

@WebFilter(filterName = "authFilter", dispatcherTypes = {
		DispatcherType.REQUEST }, description = "Filter for verify if a user is authenticated", urlPatterns = { "/*" })
public class AuthFilter extends HttpFilter implements Filter {
	private final Logger logger = Logger.getLogger(AuthFilter.class.getName());
	private static final long serialVersionUID = 1L;

	public AuthFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		logger.log(Level.INFO, "--- FILTER: AuthFilter ---");

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		User loginedUser = SecurityUtils.getLoginedUser(httpServletRequest.getSession());

		String servletPath = httpServletRequest.getServletPath();

		if (servletPath.equals("/login")) {
			chain.doFilter(request, response);

			return;
		}

		if (loginedUser == null) {
			httpServletResponse.sendRedirect("/login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
