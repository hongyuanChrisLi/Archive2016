package com.web.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.web.util.*;

@SuppressWarnings("serial")
public class LoginChecker extends HttpServlet {
	
	private String username;
	private String password;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		username = request.getParameter("user");
		password = request.getParameter("pass");
		
		DBLoginChecker dblc = new DBLoginChecker(username, password);
		
		dblc.checkLogin();
		response.setContentType("text/html");
	
		
	}
	
}