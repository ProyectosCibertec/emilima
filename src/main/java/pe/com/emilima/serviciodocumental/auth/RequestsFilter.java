package pe.com.emilima.serviciodocumental.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import pe.com.emilima.serviciodocumental.service.mysql.RoleService;
import pe.com.emilima.serviciodocumental.util.security.SecurityUtils;

/**
 * Servlet Filter implementation class RequestsFilter
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST }, description = "Filter to avoid illegal function access", urlPatterns = {
				"/solicitudes", "/solicitudes/*" })
public class RequestsFilter extends HttpFilter implements Filter {
	private final Logger logger = Logger.getLogger(RequestsFilter.class.getName());
	private static final long serialVersionUID = 1L;
	private static final List<String> methodsNotAllowedByOrganicUnit = new ArrayList<>(
			Arrays.asList(new String[] { "PUT" }));
	private static final List<String> methodsNotAllowedByTechnical = new ArrayList<>(
			Arrays.asList(new String[] { "POST", "PUT" }));
	private static final List<String> methodsNotAllowedByGeneralSecretary = new ArrayList<>(
			Arrays.asList(new String[] { "PUT", "POST", "DELETE" }));

	RoleService roleService;

	public RequestsFilter() {
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
		String pathInfo = httpServletRequest.getPathInfo();

		logger.log(Level.INFO, "--- FILTER: RequestsFilter ---\n URI: {0}", requestURI);

		if (methodsNotAllowedByOrganicUnit.contains(httpServletRequest.getMethod())) {
			if (isOrganicUnitUser(httpServletRequest) && (pathInfo.equalsIgnoreCase("/validar/")
					|| pathInfo.equalsIgnoreCase("/autorizar/") || pathInfo.equalsIgnoreCase("/aprobar/"))) {
				httpServletResponse.setStatus(401);

				return;
			}
		}

		if (methodsNotAllowedByTechnical.contains(httpServletRequest.getMethod())) {
			if (isTechnicalUser(httpServletRequest) && (pathInfo.equalsIgnoreCase("/autorizar/"))) {
				httpServletResponse.setStatus(401);

				return;
			}
		}

		if (methodsNotAllowedByGeneralSecretary.contains(httpServletRequest.getMethod())) {
			if (isGeneralSecretaryUser(httpServletRequest)
					&& (pathInfo.equalsIgnoreCase("/validar/") || pathInfo.equalsIgnoreCase("/aprobar/"))) {
				httpServletResponse.setStatus(401);

				return;
			}
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

	public boolean isOrganicUnitUser(HttpServletRequest request) {
		User user = SecurityUtils.getLoginedUser(request.getSession());

		if (user.getRoleId() != roleService.get(2).getId()) {
			return false;
		}

		return true;
	}

	public boolean isTechnicalUser(HttpServletRequest request) {
		User user = SecurityUtils.getLoginedUser(request.getSession());

		if (user.getRoleId() != roleService.get(3).getId()) {
			return false;
		}

		return true;
	}

	public boolean isGeneralSecretaryUser(HttpServletRequest request) {
		User user = SecurityUtils.getLoginedUser(request.getSession());

		if (user.getRoleId() != roleService.get(4).getId()) {
			return false;
		}

		return true;
	}
}
