package pe.com.emilima.serviciodocumental.dao.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dto.RequestState;

public interface IRequestStateDAO {
	List<RequestState> list();

	RequestState get(int id);
}
