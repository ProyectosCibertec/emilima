package pe.com.emilima.serviciodocumental.dto;

import java.util.Date;

public class Request {
	private int id;
	private String name;
	private String description;
	private Date creationDate;
	private String state;
	private String userId;

	public Request() {
		
	}

	public Request(int id, String name, String description, Date creationDate, String state, String userId) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.state = state;
		this.userId = userId;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
