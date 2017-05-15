package foo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GetFriendList extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		// String hobby = request.getParameter("hobby");

		ArrayList<String> friends = new ArrayList<String>();
		friends.add("Fred");
		friends.add("Pradeep");
		friends.add("Philippe");

		request.setAttribute("names", friends);

		RequestDispatcher view = request
				.getRequestDispatcher("hobby-friends.jsp");
		view.forward(request, response);

	}
}