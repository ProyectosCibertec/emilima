package pe.com.emilima.serviciodocumental.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.emilima.serviciodocumental.dto.File;
import pe.com.emilima.serviciodocumental.service.mysql.FileService;

/**
 * Servlet implementation class FilesServlet
 */
@WebServlet(description = "Servlet that manages all the files uploaded to the server", urlPatterns = { "/descargar/*",
		"/archivo/*" })
public class FilesServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(FilesServlet.class.getName());
	private static final long serialVersionUID = 1L;

	FileService fileService = new FileService();
	
	public FilesServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: FilesServlet ---");
		File file = fileService
				.get(Integer.parseInt(request.getPathInfo().substring(1)));
		
		file.download(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
