package pe.com.emilima.serviciodocumental.servlet;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

import pe.com.emilima.serviciodocumental.service.mysql.FileService;

/**
 * Servlet implementation class UploadFilesServlet
 */
@WebServlet(description = "Servlet to upload files", urlPatterns = { "/subir" })
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class UploadFilesServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(UploadFilesServlet.class.getName());
	private static final long serialVersionUID = 1L;
	private static final String fileUploadsLocation = System.getProperty("user.home") + File.separator + "file-uploads";
	FileService fileService;
	
    public UploadFilesServlet() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		fileService = new FileService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.log(Level.INFO, "--- POST: UploadFilesServlet ---");
		uploadFile(request, response);
	}

	private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Part filePart = request.getPart("file");
		UUID uuid = UUID.randomUUID();
		String uuidString = uuid.toString();
		byte[] fileNameInBytes = filePart.getSubmittedFileName().getBytes();
		String fileName = new String(fileNameInBytes, "UTF-8");
		String fileNameWithUuid = uuidString + "." + FilenameUtils.getExtension(fileName);
		pe.com.emilima.serviciodocumental.dto.File file = new pe.com.emilima.serviciodocumental.dto.File();
		
		file.setId(uuidString);
		file.setFilename(fileName);
		filePart.write(fileUploadsLocation + File.separator + fileNameWithUuid);
		fileService.add(file);
		request.setAttribute("fileId", uuidString);
	}
}
