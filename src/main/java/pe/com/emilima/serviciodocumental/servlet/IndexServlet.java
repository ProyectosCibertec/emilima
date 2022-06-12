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

@WebServlet(description = "Index servlet", urlPatterns = { "//" })
public class IndexServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(IndexServlet.class.getName());
	private static final long serialVersionUID = 1L;

	public IndexServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: IndexServlet ---");

		showIndex(request, response);
	}

	private void showIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher userDispatcher = request.getRequestDispatcher("/index.jsp");
		userDispatcher.include(request, response);
	}
}
