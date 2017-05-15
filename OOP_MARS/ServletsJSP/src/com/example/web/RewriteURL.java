package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

public class RewriteURL extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.println("<html><body>");
		out.println("<a href=\"" + response.encodeURL("CheckSession.do")
				+ "\">click me</a>");
		out.println("</body></html>");
	}
}