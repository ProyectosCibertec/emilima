package pe.com.emilima.serviciodocumental.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.com.emilima.serviciodocumental.dto.RequestState;
import pe.com.emilima.serviciodocumental.service.mysql.RequestStateService;

/**
 * Servlet implementation class RequestState
 */
@WebServlet(description = "Servlet to manage the state of requests", urlPatterns = { "/estados-solicitudes", "/estados-solicitudes/*" })
public class RequestStateServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(RequestStateServlet.class.getName());
	private static final long serialVersionUID = 1L;
	
	RequestStateService requestStateService = new RequestStateService();
	
    public RequestStateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: RequestStateServlet ---");
		String pathInfo = request.getPathInfo();
		
		switch (pathInfo) {
		case "/":
			getRequestStates(request, response);
			break;
		default:
			break;
		}
	}
	
	private void getRequestStates(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			List<RequestState> requestStates = requestStateService.list();
			String requestStatesJson = gson.toJson(requestStates);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(requestStatesJson);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
