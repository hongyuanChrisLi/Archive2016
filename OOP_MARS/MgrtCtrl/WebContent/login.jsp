<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
<LINK rel=stylesheet type=text/css href="WEB-STYLE/CDWLogin.css">
<script type="text/javascript" src="JS/login.js"></script>

<DIV class=login-card>
 <h1>Log-in</h1><br> 
 <DIV><font size="3" color="red">
 	<% if(null!=request.getAttribute("errorMessage")){
					out.println(request.getAttribute("errorMessage"));
			}
	%></font>
	</DIV>
	<form method=POST action="Login.do">
		<input type="text" name="user" placeholder="Username">
    <input type="password" name="pass" placeholder="Password">
		<input type="submit" name="login" class="login login-submit" value="login">
	</form>
	<!--BEGIN FootValidator -->
</DIV>
<%@ include file="footer.jsp"%>