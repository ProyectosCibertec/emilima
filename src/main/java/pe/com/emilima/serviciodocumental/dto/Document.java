package pe.com.emilima.serviciodocumental.dto;

import java.util.Date;

public class Document {
	private int id;
	private String name;
	private String description;
	private Date uploadDate;
	private int fileId;

	public Document() {

	}

	public Document(int id, String name, String description, Date uploadDate, int fileId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.uploadDate = uploadDate;
		this.fileId = fileId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
}
