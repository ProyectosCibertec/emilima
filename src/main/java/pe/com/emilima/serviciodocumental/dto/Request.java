package pe.com.emilima.serviciodocumental.dto;

import java.util.Date;

public class Request {
	private int id;
	private String name;
	private String description;
	private Date creationDate;
	private int requestStateId;
	private String userId;
	private int documentId;

	public Request() {
		
	}

	public Request(int id, String name, String description, Date creationDate, int requestStateId, String userId,
			int documentId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.requestStateId = requestStateId;
		this.userId = userId;
		this.documentId = documentId;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getRequestStateId() {
		return requestStateId;
	}

	public void setRequestStateId(int requestStateId) {
		this.requestStateId = requestStateId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
}
