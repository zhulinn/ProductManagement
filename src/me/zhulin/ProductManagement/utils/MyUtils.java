package me.zhulin.ProductManagement.utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.zhulin.ProductManagement.beans.UserAccount;

public class MyUtils {
	public static final String ATT_NAME_CONNECTION = "ATT_NAME_CONNECTION";
	private static final String ATT_NAME_USER_NAME = "ATT_NAME_USER_NAME";
	
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_NAME_CONNECTION, conn);
	}
	
	public static Connection getStoredConnection(ServletRequest request) {
		Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return conn;
	}
	
	// Store user info in session
	public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
		session.setAttribute("loginedUser", loginedUser);
	}
	
	public static UserAccount getUserAccount(HttpSession session) {
		UserAccount user = (UserAccount) session.getAttribute("loginedUser");
		return user;
	}
	
	//Store info in Cookie
	public static void storeUserCookie(HttpServletResponse response, UserAccount user) {
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
		cookieUserName.setMaxAge(60*60*24);
		response.addCookie(cookieUserName);
	}
	
	public static String getUserNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(ATT_NAME_USER_NAME.equals(cookie.getName())) return cookie.getValue();
			}
		}
		return null;
		
	}
	
	// Delete Cookie
	public static void deleteUserCookie(HttpServletResponse response) {
		Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, "");
		cookieUserName.setMaxAge(0);
		response.addCookie(cookieUserName);
	}
}
