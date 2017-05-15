package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;


public class MgrtSubmit extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String src_env = request.getParameter("SRC_ENV");
		String tgt_env = request.getParameter("TGT_ENV");
		String crq = request.getParameter("CRQ");
		String comment = request.getParameter("COMMENT");
		String isgroup = request.getParameter("IS_GROUP");
		String details = request.getParameter("DETAILS");
		
		out.println("Hello");
		
		out.println(src_env + "<br>");
		out.println(tgt_env + "<br>");
		out.println(crq + "<br>");
		out.println(comment + "<br>");
		out.println(isgroup + "<br>");
		out.println(details + "<br>");
	}
	
}