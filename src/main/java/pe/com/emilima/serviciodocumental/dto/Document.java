package pe.com.emilima.serviciodocumental.dto;

import java.util.Date;

public class Document {
	private int id;
	private String name;
	private String description;
	private Date uploadDate;
	private String documentName;
	private int requestId;

	public Document() {

	}

	public Document(int id, String name, String description, Date uploadDate, String documentName, int requestId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.uploadDate = uploadDate;
		this.documentName = documentName;
		this.requestId = requestId;
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

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
}
