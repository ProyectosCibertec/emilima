package pe.com.emilima.serviciodocumental.dao.mysql.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;

import pe.com.emilima.serviciodocumental.dao.mysql.IFileDAO;
import pe.com.emilima.serviciodocumental.dto.File;
import pe.com.emilima.serviciodocumental.util.connection.MySQLConnection;

public class FileDAO implements IFileDAO {
	private final Logger logger = Logger.getLogger(FileDAO.class.getName());

	@Override
	public List<File> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public File get(int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		File file = null;

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM file WHERE `id` = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			file = new File();

			while (resultSet.next()) {
				file = new File(resultSet.getInt(1), resultSet.getString(2));
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

		return file;
	}

	@Override
	public int add(File file) {
		// TODO Auto-generated method stub
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO file(`name`) VALUES (?)");

			preparedStatement.setString(1, file.getFilename());

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
