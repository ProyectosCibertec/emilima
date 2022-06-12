package pe.com.emilima.serviciodocumental.util.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.com.emilima.serviciodocumental.dto.User;

public class SecurityUtils {
	public static void storeLoginedUser(HttpSession session, User user) {
		session.setAttribute("loginedUser", user);
	}
	
	public static User getLoginedUser(HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		return user;
	}
	
	public static void redirectAfterLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/");
	}
}
