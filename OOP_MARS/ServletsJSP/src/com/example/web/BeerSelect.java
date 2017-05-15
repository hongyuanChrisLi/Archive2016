package com.example.web; 
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.example.model.*;

public class BeerSelect extends HttpServlet 
{
	public void doPost(HttpServletRequest request, 
		HttpServletResponse response)  
		throws IOException,ServletException 
	{
		BeerExpert beerexp = new BeerExpert();
		
		
		//response.setContentType("text/html");  
		//PrintWriter out = response.getWriter();
		//out.println("Beer Selection Advice<br>");
		String c = request.getParameter("color");
		List<String> result = beerexp.getBrands(c);
		
		//out.println("<br>Got beer color " + c);
		
		/*for (String beer : beerlst)
		{	
			out.println("<br> try: " + beer);
		}*/
		
		request.setAttribute("styles", result);
		
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
	}
}