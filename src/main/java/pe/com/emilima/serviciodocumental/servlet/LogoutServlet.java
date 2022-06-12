package pe.com.emilima.serviciodocumental.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.emilima.serviciodocumental.dto.User;
import pe.com.emilima.serviciodocumental.service.mysql.UserService;
import pe.com.emilima.serviciodocumental.util.security.SecurityUtils;

@WebServlet(description = "Servlet used for end authentication", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(LogoutServlet.class.getName());
	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: LogoutServlet ---");
		logout(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- POST: LogoutServlet ---");
		this.doGet(request, response);
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();

		response.sendRedirect(request.getContextPath() + "/login");
	}
}
