package pe.com.emilima.serviciodocumental.dao.mysql;

import java.util.List;

import pe.com.emilima.serviciodocumental.dto.Role;

public interface IRoleDAO {
	List<Role> list();

	Role get(int id);
}
