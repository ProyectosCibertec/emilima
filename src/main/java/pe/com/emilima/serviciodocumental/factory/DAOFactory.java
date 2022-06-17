package pe.com.emilima.serviciodocumental.factory;

import pe.com.emilima.serviciodocumental.dao.mysql.IDocumentDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.IFileDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.IRequestDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.IRequestStateDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.IRoleDAO;
import pe.com.emilima.serviciodocumental.dao.mysql.IUserDAO;
import pe.com.emilima.serviciodocumental.util.edt.*;

public abstract class DAOFactory {
	public abstract IRoleDAO roleDAO();
	public abstract IUserDAO userDAO();
	public abstract IRequestDAO requestDAO();
	public abstract IDocumentDAO documentDAO();
	public abstract IRequestStateDAO requestStateDAO();
	public abstract IFileDAO fileDAO();
	
	public static DAOFactory getDAOFactory(DaoType daoType) {
		switch (daoType) {
		case MYSQL:
			return new MySQLDAOFactory();
		default:
			return null;
		}
	}
}
