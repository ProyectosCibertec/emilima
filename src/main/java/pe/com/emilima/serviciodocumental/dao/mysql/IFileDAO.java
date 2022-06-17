package pe.com.emilima.serviciodocumental.dao.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dto.File;

public interface IFileDAO {
	List<File> list();

	File get(int id);
	
	int add(File file);
}
