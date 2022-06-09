package pe.com.emilima.serviciodocumental.service.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dao.mysql.IRequestDAO;
import pe.com.emilima.serviciodocumental.dto.Request;
import pe.com.emilima.serviciodocumental.factory.DAOFactory;
import pe.com.emilima.serviciodocumental.util.edt.DaoType;

public class RequestService implements IRequestDAO {
	private DAOFactory daoFactory = DAOFactory.getDAOFactory(DaoType.MYSQL);
	private IRequestDAO requestDao = daoFactory.requestDAO();
	
	@Override
	public List<Request> list() {
		// TODO Auto-generated method stub
		return requestDao.list();
	}

	@Override
	public Request get(int id) {
		// TODO Auto-generated method stub
		return requestDao.get(id);
	}

	@Override
	public int add(Request request) {
		// TODO Auto-generated method stub
		return requestDao.add(request);
	}

	@Override
	public int edit(Request request) {
		// TODO Auto-generated method stub
		return requestDao.edit(request);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return requestDao.delete(id);
	}

}
