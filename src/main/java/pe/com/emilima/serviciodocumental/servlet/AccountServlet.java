package pe.com.emilima.serviciodocumental.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.emilima.serviciodocumental.dto.User;
import pe.com.emilima.serviciodocumental.service.mysql.UserService;
import pe.com.emilima.serviciodocumental.util.security.SecurityUtils;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet(description = "Servlet to change account properties", urlPatterns = { "/cuenta", "/cuenta/*" })
@MultipartConfig
public class AccountServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(AccountServlet.class.getName());
	private static final long serialVersionUID = 1L;

	UserService userService = new UserService();

	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: AccountServlet ---");
		showView(request, response);
	}

	private void showView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/account.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- POST: AccountServlet ---");
		updateAccount(request, response);
	}

	private void updateAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new User();
			String username = request.getParameter("user-name"), password = request.getParameter("user-password"),
					email = request.getParameter("user-email"), fileId = null;

			if (request.getPart("file") != null) {				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/subir");
				requestDispatcher.include(request, response);

				fileId = request.getAttribute("fileId").toString();
			}

			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setPhotoId(fileId);

			int accountsUpdated = 0;
			
			if (fileId != null) {
				accountsUpdated = userService.editAccountWithPhoto(user);
				SecurityUtils.getLoginedUser(request.getSession()).setPhotoId(fileId);				
			} else
				accountsUpdated = userService.editAccountWithoutPhoto(user);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(accountsUpdated);
			response.sendRedirect("/cuenta");
		} catch (NumberFormatException nfe) {
			logger.info(MessageFormat.format("NumberFormatException: {0}", nfe.getMessage()));
			nfe.printStackTrace();
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
