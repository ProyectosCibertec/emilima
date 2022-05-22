package com.emilima.serviciodocumental.factory;

import com.emilima.serviciodocumental.util.edt.*;

public abstract class DAOFactory {
	public abstract IRoleDAO roleDAO();
	public abstract IUserDAO userDAO();
	public abstract IRequestDAO requestDAO();
	public abstract IDocumentDAO documenDAO();
	
	public static DAOFactory getDAOFactory(DaoType daoType) {
		switch (daoType) {
		case MYSQL:
			return new MySQLDAOFactory();
		default:
			return null;
		}
	}
}
