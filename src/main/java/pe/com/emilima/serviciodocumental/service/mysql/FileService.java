package pe.com.emilima.serviciodocumental.service.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dao.mysql.IFileDAO;
import pe.com.emilima.serviciodocumental.dto.File;
import pe.com.emilima.serviciodocumental.factory.DAOFactory;
import pe.com.emilima.serviciodocumental.util.edt.DaoType;

public class FileService implements IFileDAO {
	private DAOFactory daoFactory = DAOFactory.getDAOFactory(DaoType.MYSQL);
	private IFileDAO fileDAO = daoFactory.fileDAO();
	
	@Override
	public List<File> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File get(int id) {
		// TODO Auto-generated method stub
		return fileDAO.get(id);
	}

	@Override
	public int add(File file) {
		// TODO Auto-generated method stub
		return fileDAO.add(file);
	}

}
