package pe.com.emilima.serviciodocumental.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet(description = "Servlet to return the session values", urlPatterns = { "/sesion" })
public class SessionServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(SessionServlet.class.getName());
	private static final long serialVersionUID = 1L;
	
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Gson gson = new Gson();
			HttpSession session = request.getSession();
			String sessionJson = gson.toJson(session);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(sessionJson);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}
}
