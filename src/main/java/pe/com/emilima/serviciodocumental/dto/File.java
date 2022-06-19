package pe.com.emilima.serviciodocumental.dto;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import pe.com.emilima.serviciodocumental.util.Util;

public class File {
	private String id;
	private String filename;
	
	public File() {
		
	}

	public File(String id, String filename) {
		this.id = id;
		this.filename = filename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
