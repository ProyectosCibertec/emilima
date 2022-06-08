package pe.com.emilima.serviciodocumental.dao.mysql.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import pe.com.emilima.serviciodocumental.dao.mysql.IRoleDAO;
import pe.com.emilima.serviciodocumental.dto.Role;
import pe.com.emilima.serviciodocumental.util.connection.MySQLConnection;

public class RoleDAO implements IRoleDAO {
	private final Logger logger = Logger.getLogger(RoleDAO.class.getName());

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Role> roles = null;
		Role role = null;

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM role");
			resultSet = preparedStatement.executeQuery();
			roles = new ArrayList<Role>();

			while (resultSet.next()) {
				role = new Role(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
				roles.add(role);
			}
		} catch (SQLException se) {
			logger.info(MessageFormat.format("SQL Exception: {0}", se.getMessage()));
			logger.info(MessageFormat.format("SQL state: {0}", se.getSQLState()));
			se.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException se) {
				logger.info(MessageFormat.format("SQL Exception: {0}", se.getMessage()));
				logger.info(MessageFormat.format("SQL state: {0}", se.getSQLState()));
				se.printStackTrace();
			} catch (Exception e) {
				logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
				e.printStackTrace();
			}
		}

		return roles;
	}

	@Override
	public Role get(int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Role role = null;

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM role WHERE `id` = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			role = new Role();

			while (resultSet.next()) {
				role = new Role(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
			}
		} catch (SQLException se) {
			logger.info(MessageFormat.format("SQL Exception: {0}", se.getMessage()));
			logger.info(MessageFormat.format("SQL state: {0}", se.getSQLState()));
			se.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException se) {
				logger.info(MessageFormat.format("SQL Exception: {0}", se.getMessage()));
				logger.info(MessageFormat.format("SQL state: {0}", se.getSQLState()));
				se.printStackTrace();
			} catch (Exception e) {
				logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
				e.printStackTrace();
			}
		}

		return role;
	}

}
