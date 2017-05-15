package com.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.control.MgrtOpts;
import com.web.db.DBLoginChecker;
import com.web.file.WebConfigLoader;
import com.web.param.DBLoginContainer;
import com.web.param.WebConfigContainer;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	private String username;
	private String password;
	private String rootDir="C:/Users/C2Lv/GoogleDrive/JavaProjects2016/OOP_MARS/MgrtCtrl/";
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		username = request.getParameter("user");
		password = request.getParameter("pass");

		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		WebConfigLoader webConfLoader = new WebConfigLoader ();
		WebConfigContainer webConf = webConfLoader.loadWebConfig( rootDir + "WebContent/WEB-INF/config.xml");
		
		//System.out.println(webConf.getDestEnvList().size());
		//System.out.println(webConf.getOrignEnvList().size());
		
		DBLoginChecker dblc = new DBLoginChecker(username, password);
		DBLoginContainer dbParm = dblc.checkLogin(webConf.getDBList().get(0));
		
		if (dbParm.getExitVal() != MgrtOpts.EXIT_SUCCESS ) {
			dbParm = dblc.checkLogin(webConf.getDBList().get(1));
		}
		
		response.setContentType("text/html");
		
		if ( dbParm.getExitVal() == MgrtOpts.EXIT_SUCCESS) {
			//request.setAttribute("dbUser", username);
			//request.setAttribute("dbPasswd", password);
			session.setAttribute("orignEnvs", webConf.getOrignEnvList());
			session.setAttribute("destEnvs", webConf.getDestEnvList());
			String encodedURL = response.encodeRedirectURL("mgrt-req.jsp");
			response.sendRedirect(encodedURL);
			//RequestDispatcher rd = request.getRequestDispatcher("/mgrt-req.jsp");
			//rd.forward(request, response);
			
		} else  {
			request.setAttribute("errorMessage", dbParm.getMsg());
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		} 
		
	}
}