package pe.com.emilima.serviciodocumental.dto;

public class User {
	private String username;
	private String password;
	private String email;
	private int roleId;
	private String photoId;

	public User() {
		
	}

	public User(String username, String password, String email, int roleId, String photoId) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.roleId = roleId;
		this.photoId = photoId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
}
