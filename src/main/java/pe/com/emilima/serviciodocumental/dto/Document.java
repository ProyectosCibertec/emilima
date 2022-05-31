package pe.com.emilima.serviciodocumental.dto;

import java.util.Date;

public class Document {
	private int id;
	private String name;
	private String description;
	private Date uploadDate;
	private int requestId;

	public Document() {
		super();
	}

	public Document(int id, String name, String description, Date uploadDate, int requestId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.uploadDate = uploadDate;
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

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
}
