package pe.com.emilima.serviciodocumental.dao.mysql.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import pe.com.emilima.serviciodocumental.dao.mysql.IRequestStateDAO;
import pe.com.emilima.serviciodocumental.dto.RequestState;
import pe.com.emilima.serviciodocumental.util.connection.MySQLConnection;

public class RequestStateDAO implements IRequestStateDAO {
	private final Logger logger = Logger.getLogger(RequestStateDAO.class.getName());

	@Override
	public List<RequestState> list() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<RequestState> requestStates = null;
		RequestState requestState = null;

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM request_state");
			resultSet = preparedStatement.executeQuery();
			requestStates = new ArrayList<RequestState>();

			while (resultSet.next()) {
				requestState = new RequestState(resultSet.getInt(1), resultSet.getString(2));
				requestStates.add(requestState);
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

		return requestStates;
	}

	@Override
	public RequestState get(int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		RequestState requestState = null;

		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM request_state WHERE `id` = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			requestState = new RequestState();

			while (resultSet.next()) {
				requestState = new RequestState(resultSet.getInt(1), resultSet.getString(2));
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

		return requestState;
	}
}
