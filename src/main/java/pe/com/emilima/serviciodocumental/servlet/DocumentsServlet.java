package pe.com.emilima.serviciodocumental.servlet;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import pe.com.emilima.serviciodocumental.dto.Document;
import pe.com.emilima.serviciodocumental.service.mysql.DocumentService;

/**
 * Servlet implementation class DocumentsServlet
 */
@WebServlet(description = "Servlet that manages all the access to documents' APIs and views.", urlPatterns = {
		"/documentos", "/documentos/*" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class DocumentsServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(DocumentsServlet.class.getName());
	private static final long serialVersionUID = 1L;
	private static final String fileUploadsLocation = System.getProperty("user.home") + File.separator + "file-uploads";

	DocumentService documentService = new DocumentService();

	public DocumentsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: DocumentsServlet ---");
		String pathInfo = request.getPathInfo();

		switch (pathInfo) {
		case "/listar":
			showList(request, response);
			break;
		case "/":
			if (request.getParameter("id") != null) {
				getDocument(request, response);
				break;
			}
			
			getDocuments(request, response);
			break;
		default:
			showErrorPage404(request, response);
			break;
		}
	}

	private void getDocument(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			Document document = documentService.get(Integer.parseInt(request.getParameter("id")));
			String documentJson = gson.toJson(document);

			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(documentJson);
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
		logger.log(Level.INFO, "--- POST: DocumentsServlet ---");
		addDocument(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- PUT: DocumentsServlet ---");
		updateDocument(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- DELETE: DocumentsServlet ---");
		deleteDocument(request, response);
	}

	private void showList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/documents.jsp");
		requestDispatcher.forward(request, response);
	}

	private void showErrorPage404(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error404.jsp");
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

	private void addDocument(HttpServletRequest request, HttpServletResponse response) {
		try {
			Document document = new Document();
			String name = request.getParameter("name"), description = request.getParameter("description"),
					uploadDate = request.getParameter("uploadDate");

			document.setName(name);
			document.setDescription(description);
			document.setUploadDate(new SimpleDateFormat("yyyy-MM-dd").parse(uploadDate));

			Part documentFilePart = request.getPart("documentFile");
			byte[] fileNameInBytes = documentFilePart.getSubmittedFileName().getBytes();
			String fileName = new String(fileNameInBytes, "UTF-8");
			documentFilePart.write(fileUploadsLocation + File.separator + fileName);
			
			// document.setDocumentName(fileName);
			
			int documentsRegistered = documentService.add(document);
			
			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(documentsRegistered);
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

	private void deleteDocument(HttpServletRequest request, HttpServletResponse response) {
		try {
			Gson gson = new Gson();
			int documentId = Integer.parseInt(request.getPathInfo().substring(1));
			int documentsDeleted = documentService.delete(documentId);
			String usersJson = gson.toJson(documentsDeleted);

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

	private void updateDocument(HttpServletRequest request, HttpServletResponse response) {

	}
}
