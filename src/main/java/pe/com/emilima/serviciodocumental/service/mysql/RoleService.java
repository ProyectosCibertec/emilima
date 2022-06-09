package pe.com.emilima.serviciodocumental.service.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dao.mysql.IRoleDAO;
import pe.com.emilima.serviciodocumental.dto.Role;
import pe.com.emilima.serviciodocumental.factory.DAOFactory;
import pe.com.emilima.serviciodocumental.util.edt.DaoType;

public class RoleService implements IRoleDAO {
	private DAOFactory daoFactory = DAOFactory.getDAOFactory(DaoType.MYSQL);
	private IRoleDAO roleDAO = daoFactory.roleDAO();
	
	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return roleDAO.list();
	}

	@Override
	public Role get(int id) {
		// TODO Auto-generated method stub
		return roleDAO.get(id);
	}

}
