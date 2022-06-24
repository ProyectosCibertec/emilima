package pe.com.emilima.serviciodocumental.dao.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dto.User;

public interface IUserDAO {
	List<User> list();

	User get(String username);

	int add(User user);

	int edit(User user);
	
	int editAccountWithPhoto(User user);
	
	int editAccountWithoutPhoto(User user);

	int delete(String username);

	User login(String username, String password);
}
