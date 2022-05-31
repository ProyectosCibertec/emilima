package pe.com.emilima.serviciodocumental.service.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dao.mysql.IDocumentDAO;
import pe.com.emilima.serviciodocumental.dto.Document;
import pe.com.emilima.serviciodocumental.factory.DAOFactory;
import pe.com.emilima.serviciodocumental.util.edt.DaoType;

public class DocumentService implements IDocumentDAO {
	private DAOFactory daoFactory = DAOFactory.getDAOFactory(DaoType.MYSQL);
	private IDocumentDAO documentDao = daoFactory.documentDAO();
	
	@Override
	public List<Document> list() {
		// TODO Auto-generated method stub
		return documentDao.list();
	}

	@Override
	public Document get(int id) {
		// TODO Auto-generated method stub
		return documentDao.get(id);
	}

	@Override
	public int add(Document document) {
		// TODO Auto-generated method stub
		return documentDao.add(document);
	}

	@Override
	public int edit(Document document) {
		// TODO Auto-generated method stub
		return documentDao.edit(document);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return documentDao.delete(id);
	}

}
