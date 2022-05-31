package pe.com.emilima.serviciodocumental.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import pe.com.emilima.serviciodocumental.dto.Document;
import pe.com.emilima.serviciodocumental.service.mysql.DocumentService;

/**
 * Servlet implementation class DocumentsServlet
 */
@WebServlet(description = "Servlet that manages all the access to documents' APIs and views.", urlPatterns = {
		"/documentos", "/documentos/*" })
@MultipartConfig(location = "./", maxFileSize = 1024*1024*10)
public class DocumentsServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(DocumentsServlet.class.getName());
	private static final long serialVersionUID = 1L;

	DocumentService documentService = new DocumentService();

	public DocumentsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		switch (pathInfo) {
		case "/listar":
			showList(request, response);
			break;
		case "/":
			getDocuments(request, response);
			break;
		default:
			System.out.println("No response");
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		registerDocument(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void showList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/documents.jsp");
		requestDispatcher.forward(request, response);
	}

	private void getDocuments(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			List<Document> documents = documentService.list();
			String documentsJson = gson.toJson(documents);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(documentsJson);
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}
	

	private void registerDocument(HttpServletRequest request, HttpServletResponse response) {
		try {
			Document document = new Document();
			
			document.setName(request.getParameter("name"));
			document.setDescription(request.getParameter("description"));
			document.setUploadDate(new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("uploadDate")));
			// document.setRequestId(Integer.parseInt(request.getParameter("requestId")));
			
			Part documentFilePart = request.getPart("documentFile");
			byte[] fileNameInBytes = documentFilePart.getSubmittedFileName().getBytes();
			String fileName = new String(fileNameInBytes, "UTF-8");
			documentFilePart.write(fileName);
		} catch (NumberFormatException nfe) {
			logger.info(MessageFormat.format("NumberFormatException: {0}", nfe.getMessage()));
			nfe.printStackTrace();
		} catch (ParseException pe) {
			logger.info(MessageFormat.format("ParseException: {0}", pe.getMessage()));
			pe.printStackTrace();
		} catch (IOException ioe) {
			logger.info(MessageFormat.format("IOException: {0}", ioe.getMessage()));
			ioe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
	}
}