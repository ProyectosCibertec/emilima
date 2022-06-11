package pe.com.emilima.serviciodocumental.util.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import pe.com.emilima.serviciodocumental.dto.User;

public class SecurityUtils {
	private static int REDIRECT_ID = 0;

	private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
	private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();

	public static void storeLoginedUser(HttpSession session, User user) {
		session.setAttribute("loginedUser", user);
	}
	
	public static User getLoginedUser(HttpSession session) {
		User user = (User) session.getAttribute("loginedUser");
		return user;
	}
	
	public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
		Integer id = uri_id_map.get(requestUri);

		if (id == null) {
			id = REDIRECT_ID++;

			uri_id_map.put(requestUri, id);
			id_uri_map.put(id, requestUri);
			return id;
		}

		return id;
	}

	public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
		String url = id_uri_map.get(redirectId);
		if (url != null) {
			return url;
		}
		return null;
	}
}
