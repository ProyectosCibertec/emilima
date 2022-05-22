package com.emilima.serviciodocumental.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;

import com.emilima.serviciodocumental.auth.AuthJdbcRealm;

@WebServlet(description = "Servlet used for authentication", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private final Logger logger = Logger.getLogger(AuthJdbcRealm.class.getName());
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsernamePasswordToken token = new UsernamePasswordToken(request.getParameter("username"), request.getParameter("password"));
		
		try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            logger.severe(MessageFormat.format("Error authenticating user: {0}.", token.getUsername()));
        }
	}

}
