package pe.com.emilima.serviciodocumental.service.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dao.mysql.IUserDAO;
import pe.com.emilima.serviciodocumental.dto.User;
import pe.com.emilima.serviciodocumental.factory.DAOFactory;
import pe.com.emilima.serviciodocumental.util.edt.DaoType;

public class UserService implements IUserDAO {
	private DAOFactory daoFactory = DAOFactory.getDAOFactory(DaoType.MYSQL);
	private IUserDAO userDao = daoFactory.userDAO();

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

	@Override
	public User get(String username) {
		// TODO Auto-generated method stub
		return userDao.get(username);
	}

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return userDao.add(user);
	}

	@Override
	public int edit(User user) {
		// TODO Auto-generated method stub
		return userDao.edit(user);
	}

	@Override
	public int delete(String username) {
		// TODO Auto-generated method stub
		return userDao.delete(username);
	}

	@Override
	public User login(String username, String password) {
		return userDao.login(username, password);
	}
}
