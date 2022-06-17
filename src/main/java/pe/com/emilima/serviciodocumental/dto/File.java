package pe.com.emilima.serviciodocumental.dto;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import pe.com.emilima.serviciodocumental.util.Util;

public class File {
	private int id;
	private String filename;
	
	public File() {
		
	}

	public File(int id, String filename) {
		this.id = id;
		this.filename = filename;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
		java.io.File file = new java.io.File(Util.FILE_UPLOAD_DIRECTORY + this.filename);
		byte[] bytes = FileUtils.readFileToByteArray(file);
		response.reset();
		response.setContentType("application/octet-stream");
		response.addHeader("Pragma", "public");
		response.addHeader("Cache-Control", "max-age=0");
		response.addHeader("Content-Disposition", "attachment");
		response.setContentLength((int) file.length());

		ServletOutputStream outputStream = response.getOutputStream();
		
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	}
}
