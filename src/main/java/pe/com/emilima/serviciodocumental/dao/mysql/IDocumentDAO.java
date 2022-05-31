package pe.com.emilima.serviciodocumental.dao.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dto.Document;

public interface IDocumentDAO {
	List<Document> list();
	Document get(int id);
	int add(Document document);
	int edit(Document document);
	int delete(int id);
}
