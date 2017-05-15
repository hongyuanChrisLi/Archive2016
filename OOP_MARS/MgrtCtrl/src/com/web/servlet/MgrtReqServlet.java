package com.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.control.requests.MgrtReq;

@SuppressWarnings("serial")
public class MgrtReqServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		MgrtReq mgrtReq = new MgrtReq();
		mgrtReq.setOrignEnv(request.getParameter("ORIGN_ENV"));
		mgrtReq.setDestEnv(request.getParameter("DEST_ENV"));
		
		//System.out.println("Orign " + request.getParameter("ORIGN_ENV"));
		//System.out.println("Orign " + request.getParameter("DEST_ENV"));
		
	}
	
}