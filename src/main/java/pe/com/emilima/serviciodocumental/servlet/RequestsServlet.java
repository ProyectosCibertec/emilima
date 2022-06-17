package pe.com.emilima.serviciodocumental.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
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

import pe.com.emilima.serviciodocumental.dto.Request;
import pe.com.emilima.serviciodocumental.dto.User;
import pe.com.emilima.serviciodocumental.service.mysql.RequestService;
import pe.com.emilima.serviciodocumental.util.Util;

/**
 * Servlet implementation class RequestsServlet
 */
@WebServlet(description = "Servlet that manages all the requests' views and functionalities", urlPatterns = {
		"/solicitudes", "/solicitudes/*" })
public class RequestsServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(RequestsServlet.class.getName());
	private static final long serialVersionUID = 1L;

	RequestService requestService = new RequestService();

	public RequestsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: RequestsServlet ---");
		String pathInfo = request.getPathInfo();

		switch (pathInfo) {
		case "/listar":
			showList(request, response);
			break;
		case "/":
			if (request.getParameter("id") != null) {
				getRequest(request, response);
				break;
			}

			getRequests(request, response);
			break;
		default:
			showErrorPage404(request, response);
			break;
		}
	}

	private void getRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			Request request1 = requestService.get(Integer.parseInt(request.getParameter("id")));
			String requestJson = gson.toJson(request1);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(requestJson);
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
		logger.log(Level.INFO, "--- POST: RequestsServlet ---");
		registerRequest(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- PUT: RequestsServlet ---");
		String pathInfo = request.getPathInfo();

		switch (pathInfo) {
		case "/validar/":
			validateRequest(request, response);
			break;
		case "/autorizar/":
			authorizeRequest(request, response);
			break;
		case "/aprobar/":
			approveRequest(request, response);
			break;
		case "/":
			editRequest(request, response);
		}
	}

	private void approveRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int requestsApproved = requestService.approve(id);

		response.setStatus(200);
		response.setHeader("Content-Type", "application/json");
		response.getOutputStream().println(requestsApproved);
	}

	private void authorizeRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int requestsAuthorized = requestService.authorize(id);

		response.setStatus(200);
		response.setHeader("Content-Type", "application/json");
		response.getOutputStream().println(requestsAuthorized);
	}

	private void validateRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int requestsValidated = requestService.validate(id);

		response.setStatus(200);
		response.setHeader("Content-Type", "application/json");
		response.getOutputStream().println(requestsValidated);
	}

	private void editRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			String json = Util.readInputStream(request.getInputStream());
			
			Request request1 = gson.fromJson(json, Request.class);
			int requestsRegistered = requestService.edit(request1);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(requestsRegistered);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- DELETE: RequestsServlet ---");
		deleteRequest(request, response);
	}

	private void showList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/requests.jsp");
		requestDispatcher.forward(request, response);
	}

	private void showErrorPage404(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error404.jsp");
		requestDispatcher.forward(request, response);
	}

	private void getRequests(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			List<Request> requests = requestService.list();
			String requestsJson = gson.toJson(requests);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(requestsJson);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}

	private void registerRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			Request request1 = new Request();
			request1.setName(request.getParameter("name"));
			request1.setDescription(request.getParameter("description"));
			request1.setCreationDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("creationDate")));
			request1.setRequestStateId(1);
			request1.setUserId(request.getParameter("user"));
			int requestsRegistered = requestService.add(request1);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(requestsRegistered);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}

	private void deleteRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getPathInfo().substring(1);
			int requestsDeleted = requestService.delete(Integer.parseInt(id));

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(requestsDeleted);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}
}
