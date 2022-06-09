package pe.com.emilima.serviciodocumental.util.http;

public class ServiceResponse {
	private Object data;
	private int httpCode;
	private String exception;
	
	public ServiceResponse(Object data, int httpCode, String exception) {
		this.data = data;
		this.httpCode = httpCode;
		this.exception = exception;
	}

	public static ServiceResponse setContent(Object data, int httpCode, String exception) {
		return new ServiceResponse(data, httpCode, exception);
	}
}
