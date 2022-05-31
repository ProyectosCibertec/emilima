package pe.com.emilima.serviciodocumental.util.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class MySQLConnection {
	private final Logger logger = Logger.getLogger(MySQLConnection.class.getName());
	private Connection connection = null;
	private Dotenv dotenv = Dotenv.configure()
			.directory(System.getProperty("user.home"))
			.filename(".env")
			.load();
	final String MYSQL_URL = dotenv.get("MYSQL_DB_URL");
	final String MYSQL_USER = dotenv.get("MYSQL_DB_USER");
	final String MYSQL_PASSWORD = dotenv.get("MYSQL_DB_PASSWORD");
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
		} catch (SQLException se) {
			logger.info(MessageFormat.format("SQL Exception: {0}", se.getMessage()));
			logger.info(MessageFormat.format("SQL state: {0}", se.getSQLState()));
			se.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			logger.info(MessageFormat.format("ClassNotFound Exception: {0}", cnfe.getMessage()));
			cnfe.printStackTrace();
		} catch (Exception e) {
			logger.info(MessageFormat.format("Exception: {0}", e.getMessage()));
			e.printStackTrace();
		}
		
		return connection;
	}
}
