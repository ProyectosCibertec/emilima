package pe.com.emilima.serviciodocumental.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.com.emilima.serviciodocumental.dto.File;
import pe.com.emilima.serviciodocumental.service.mysql.FileService;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet(description = "Servlet to manage file data", urlPatterns = { "/archivo/*" })
public class FileServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(FileServlet.class.getName());
	private static final long serialVersionUID = 1L;

	FileService fileService = new FileService();

	public FileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: FileServlet ---");
		getFile(request, response);
	}

	private void getFile(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			String pathInfo = request.getPathInfo();
			String fileId = pathInfo.substring(1);
			File fileGot = fileService.get(fileId);
			String fileJson = gson.toJson(fileGot);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(fileJson);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}
}
