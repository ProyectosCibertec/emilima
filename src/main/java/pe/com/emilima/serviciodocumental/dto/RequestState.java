package pe.com.emilima.serviciodocumental.dto;

public class RequestState {
	private int id;
	private String name;
	
	public RequestState() {
		
	}

	public RequestState(int id, String name) {
		this.id = id;
		this.name = name;
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
}
