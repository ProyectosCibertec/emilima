package pe.com.emilima.serviciodocumental.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.com.emilima.serviciodocumental.dto.User;
import pe.com.emilima.serviciodocumental.service.mysql.UserService;
import pe.com.emilima.serviciodocumental.util.Util;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet(description = "Servlet that manages all the functionalities and views of users", urlPatterns = {
		"/usuarios", "/usuarios/*" })
public class UsersServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(UsersServlet.class.getName());
	private static final long serialVersionUID = 1L;

	UserService userService = new UserService();

	public UsersServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: UsersServlet ---");
		String pathInfo = request.getPathInfo();

		switch (pathInfo) {
		case "/listar":
			showList(request, response);
			break;
		case "/":
			if (request.getParameter("username") != null) {
				getUser(request, response);
				break;
			}

			getUsers(request, response);
			break;
		default:
			showErrorPage404(request, response);
			break;
		}
	}

	private void getUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			User user = userService.get(request.getParameter("username"));
			String userJson = gson.toJson(user);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(userJson);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- POST: UsersServlet ---");
		registerUser(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- PUT: UsersServlet ---");
		editUser(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- DELETE: UsersServlet ---");
		deleteUser(request, response);
	}

	private void showList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher userDispatcher = request.getRequestDispatcher("/users.jsp");
		userDispatcher.forward(request, response);
	}

	private void showErrorPage404(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher userDispatcher = request.getRequestDispatcher("/error404.jsp");
		userDispatcher.forward(request, response);
	}

	private void getUsers(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			List<User> users = userService.list();
			String usersJson = gson.toJson(users);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(usersJson);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = new User();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			user.setRoleId(Integer.parseInt(request.getParameter("roleId")));
			int usersRegistered = userService.add(user);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(usersRegistered);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			String json = Util.readInputStream(request.getInputStream());
			User user = gson.fromJson(json, User.class);
			int usersEdited = userService.edit(user);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(usersEdited);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getPathInfo().substring(1);
			int usersDeleted = userService.delete(username);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(usersDeleted);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}
}
