<%@ include file="header.jsp"%>

<LINK rel=stylesheet type=text/css href="WEB-STYLE/CDWMgrtStyle.css">
<LINK rel=stylesheet type=text/css href="WEB-STYLE/CDWFontStyle.css">
<%
	if (session.getAttribute("username") == null) {
		response.sendRedirect("index.jsp");
		return;
	}
%>