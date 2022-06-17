package pe.com.emilima.serviciodocumental.dao.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dto.Request;

public interface IRequestDAO {
	List<Request> list();

	Request get(int id);

	int add(Request request);

	int edit(Request request);

	int delete(int id);
	
	int validate(int id);
	
	int approve(int id);
	
	int authorize(int id);
}
