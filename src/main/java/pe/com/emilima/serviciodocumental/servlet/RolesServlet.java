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

import pe.com.emilima.serviciodocumental.dto.Role;
import pe.com.emilima.serviciodocumental.service.mysql.RoleService;

/**
 * Servlet implementation class RolesServlet
 */
@WebServlet(urlPatterns = { "/roles", "/roles/*" })
public class RolesServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(RolesServlet.class.getName());
	private static final long serialVersionUID = 1L;
	
	RoleService roleService = new RoleService();
       
    public RolesServlet() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: UsersServlet ---");
		String pathInfo = request.getPathInfo();
		
		switch (pathInfo) {
		case "/":
			getRoles(request, response);
			break;
		default:
			break;
		}
	}

	private void getRoles(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			List<Role> roles = roleService.list();
			String rolesJson = gson.toJson(roles);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(rolesJson);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}
}
