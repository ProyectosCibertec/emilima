package pe.com.emilima.serviciodocumental.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Logger;

import javax.security.auth.login.AccountException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.JdbcUtils;

import com.mysql.cj.util.StringUtils;

import pe.com.emilima.serviciodocumental.util.connection.MySQLConnection;

public class AuthJdbcRealm extends JdbcRealm {
	private final Logger logger = Logger.getLogger(AuthJdbcRealm.class.getName());
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		
		if (StringUtils.isNullOrEmpty(username))
			logger.severe("No username");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		SimpleAuthenticationInfo info = null;
		
		try {
			connection = new MySQLConnection().getConnection();
			preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE username = ?");
			preparedStatement.setString(1, username);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
			}
			
		} catch (SQLException se) {
			logger.info(MessageFormat.format("SQL Exception: {0}", se.getMessage()));
			logger.info(MessageFormat.format("SQL state: {0}", se.getSQLState()));
			se.printStackTrace();
		} finally {
			JdbcUtils.closeConnection(connection);
		}
		
		return null;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		
		return super.doGetAuthorizationInfo(arg0);
	}	
}
