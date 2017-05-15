<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="mgrt-header.jsp" %>

<% 
	List<String> orignEnvs= (List<String>) session.getAttribute("orginEnvs");
	request.setAttribute("orignEnvs", orignEnvs);
	List<String> destEnvs = (List<String>) session.getAttribute("destEnvs");
	request.setAttribute("destEnvs", destEnvs);
%>


	<DIV id=contentwrapper>
		<ul>
			<li><a class=active_item href="#">Home</a></li>
			<li><a href="system.jsp">System</a></li>
			<li><a href="monitor.jsp">Monitor</a></li>
		</ul>

		<DIV class=innertube>
		
			<p> Hello <%= session.getAttribute("username") %> </p>
			<P> Informatica PowerCenter is a widely used extraction, transformation and loading (ETL) tool used in building enterprise data warehouses. 
			</P>
			      
			<!--END Disclaimer -->
			<FORM id=ServiceRequest method=POST action="MgrtReq.do">
        <FIELDSET>
          <LEGEND>Migration Request</LEGEND>
          <P>
            <LABEL>From Env:</LABEL>
            <SELECT name=ORIGN_ENV>
              <c:forEach var="env" items="${orignEnvs}">
                <OPTION value="${env}"> ${env} </OPTION>
              </c:forEach>
            </SELECT>
           </P>
           <P>
             <LABEL>To Env:</LABEL>
             <SELECT name=DEST_ENV>
              <c:forEach var="env" items="${destEnvs}">
                <OPTION value="${env}"> ${env} </OPTION>
              </c:forEach>
             </SELECT>
            </P>
            <P>
              <LABEL>CRQ Number: </LABEL>
              <input type="text" name=CRQ>
            </P>
            <P>
              <LABEL>Comments:</LABEL>
              <input type="text" name=COMMENT>
            </P>
            <P>
              <LABEL>Is Group?</LABEL>
              <SELECT name=IS_GROUP>
                <OPTION selected 
                  value="N" > No
                </OPTION>
                <OPTION 
                  value="Y" > Yes
                </OPTION>
              </SELECT>
            </P>
            <P>
              <LABEL>Migration List:</LABEL>
              <TEXTAREA rows=10 name=DETAILS style="vertical-align: top"></TEXTAREA> 
            </P>
         </FIELDSET>
				<!--BEGIN Category -->
					<INPUT type=reset value=Clear class=clear> 
					<INPUT type=submit value=Submit class=submit> 
			</FORM>
			<!--BEGIN FootValidator -->
		</DIV>
	</DIV>
<%@ include file="footer.jsp" %>