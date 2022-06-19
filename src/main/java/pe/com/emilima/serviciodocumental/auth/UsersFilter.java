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
import pe.com.emilima.serviciodocumental.service.mysql.RoleService;
import pe.com.emilima.serviciodocumental.util.security.SecurityUtils;

@WebFilter(filterName = "usersFilter", dispatcherTypes = {
		DispatcherType.REQUEST }, description = "Filter to give access to users module", urlPatterns = { "/usuarios",
				"/usuarios/*" })
public class UsersFilter extends HttpFilter implements Filter {
	private final Logger logger = Logger.getLogger(UsersFilter.class.getName());
	private static final long serialVersionUID = 1L;

	RoleService roleService;

	public UsersFilter() {
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
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		String requestURI = httpServletRequest.getRequestURI();

		logger.log(Level.INFO, "--- FILTER: UsersFilter ---\n URI: {0}", requestURI);

		if (!isAdminUser(httpServletRequest)) {
			RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher("/error401.jsp");
			requestDispatcher.include(httpServletRequest, httpServletResponse);
			httpServletResponse.setStatus(401);
			
			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		roleService = new RoleService();
	}

	public boolean isAdminUser(HttpServletRequest request) {
		User user = SecurityUtils.getLoginedUser(request.getSession());

		if (user.getRoleId() != roleService.get(1).getId()) {
			return false;
		}

		return true;
	}
}
