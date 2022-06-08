package pe.com.emilima.serviciodocumental.dao.mysql.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import pe.com.emilima.serviciodocumental.dao.mysql.IUserDAO;
import pe.com.emilima.serviciodocumental.dto.User;
import pe.com.emilima.serviciodocumental.util.connection.MySQLConnection;

public class UserDAO implements IUserDAO {
	private final Logger logger = Logger.getLogger(MySQLConnection.class.getName());

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> users = null;
		User user = null;

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM user");
			resultSet = preparedStatement.executeQuery();
			users = new ArrayList<User>();

			while (resultSet.next()) {
				user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4));
				users.add(user);
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

		return users;
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = new User();

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getInt(4));
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

		return user;
	}

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO user(`name`, `description`, `upload_date`, `request_id`) VALUES (?, ?, ?, ?)");

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setInt(4, user.getRoleId());

			result = preparedStatement.executeUpdate();
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

		return result;
	}

	@Override
	public int edit(User user) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement(
					"UPDATE user SET name = ?, description = ?, upload_date = ?, request_id = ? WHERE id = ?");

			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setInt(4, user.getRoleId());

			result = preparedStatement.executeUpdate();
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

		return result;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?");

			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();
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

		return result;
	}
	
}
