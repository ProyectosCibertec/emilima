package pe.com.emilima.serviciodocumental.factory;

import pe.com.emilima.serviciodocumental.dao.mysql.IDocumentDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.IFileDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.IRequestDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.IRequestStateDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.IRoleDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.IUserDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.imp.DocumentDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.imp.FileDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.imp.RequestDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.imp.RequestStateDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.imp.RoleDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.imp.UserDAO;

public class MySQLDAOFactory extends DAOFactory {
	@Override
	public IRoleDAO roleDAO() {
		return new RoleDAO();
	}

	@Override
	public IUserDAO userDAO() {
		// TODO Auto-generated method stub
		return new UserDAO();
	}

	@Override
	public IRequestDAO requestDAO() {
		// TODO Auto-generated method stub
		return new RequestDAO();
	}

	@Override
	public IDocumentDAO documentDAO() {
		// TODO Auto-generated method stub
		return new DocumentDAO();
	}

	@Override
	public IRequestStateDAO requestStateDAO() {
		// TODO Auto-generated method stub
		return new RequestStateDAO();
	}

	@Override
	public IFileDAO fileDAO() {
		// TODO Auto-generated method stub
		return new FileDAO();
	}
	
}
