<%@page contentType="text/html" import="java.util.Date,java.io.*"  %>

<font face="verdana" size="2">
Current Time : <%=12 %>
</font>
<%
BufferedReader input = new BufferedReader(new FileReader("C:/Software/apache-tomcat-7.0.69/webapps/HFSJSP/content.txt"));
String line = "";
while ((line = input.readLine()) != null) {
    out.println(line);
}
out.flush();
input.close();
%>