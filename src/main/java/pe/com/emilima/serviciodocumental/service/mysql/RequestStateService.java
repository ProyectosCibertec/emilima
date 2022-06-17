package pe.com.emilima.serviciodocumental.service.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dao.mysql.IRequestStateDAO;
import pe.com.emilima.serviciodocumental.dto.RequestState;
import pe.com.emilima.serviciodocumental.factory.DAOFactory;
import pe.com.emilima.serviciodocumental.util.edt.DaoType;

public class RequestStateService implements IRequestStateDAO {
	private DAOFactory daoFactory = DAOFactory.getDAOFactory(DaoType.MYSQL);
	private IRequestStateDAO requestStateDAO = daoFactory.requestStateDAO();
	
	@Override
	public List<RequestState> list() {
		// TODO Auto-generated method stub
		return requestStateDAO.list();
	}

	@Override
	public RequestState get(int id) {
		// TODO Auto-generated method stub
		return requestStateDAO.get(id);
	}

}
