package pe.com.emilima.serviciodocumental.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import pe.com.emilima.serviciodocumental.dto.File;
import pe.com.emilima.serviciodocumental.service.mysql.FileService;
import pe.com.emilima.serviciodocumental.util.Util;

/**
 * Servlet implementation class FilesServlet
 */
@WebServlet(description = "Servlet that manages all the files uploaded to the server", urlPatterns = { "/descargar/*" })
public class DownloadFilesServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(DownloadFilesServlet.class.getName());
	private static final long serialVersionUID = 1L;

	FileService fileService = new FileService();

	public DownloadFilesServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.INFO, "--- GET: DownloadFilesServlet ---");
		File file = fileService.get(request.getPathInfo().substring(1));

		downloadFile(file, request, response);
	}

	private void downloadFile(File fileToDownload, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		java.io.File file = new java.io.File(Util.FILE_UPLOAD_DIRECTORY + fileToDownload.getId() + "."
				+ FilenameUtils.getExtension(fileToDownload.getFilename()));
		byte[] bytes = FileUtils.readFileToByteArray(file);
		response.setContentType("application/octet-stream");
		response.addHeader("Pragma", "public");
		response.addHeader("Cache-Control", "max-age=0");
		response.addHeader("Content-Disposition", MessageFormat.format("attachment; filename={0}", fileToDownload.getFilename()));
		response.setContentLength((int) file.length());

		ServletOutputStream outputStream = response.getOutputStream();

		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	}
}
