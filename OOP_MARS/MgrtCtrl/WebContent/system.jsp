<%@ page import="java.util.List,com.web.param.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="mgrt-header.jsp"%>

<%
  List<String> destEnvs = (List<String>) session.getAttribute("destEnvs");
  request.setAttribute("destEnvs", destEnvs);
  SysPrefContainer sysPref = (SysPrefContainer) session
      .getAttribute("sysPref");
%>
<SCRIPT src="JS/system.js"></SCRIPT>

<DIV id=contentwrapper>
  <ul>
    <li><a href="mgrt-req.jsp">Home</a></li>
    <li><a class=active_item href="#">System</a></li>
    <li><a href="monitor.jsp">Monitor</a></li>
  </ul>

  <DIV class=innertube>

    <!--END Disclaimer -->
    <FORM id=ServiceRequest method=post action="SysSave.do">
      <!--BEGIN Category -->
      <%
        int count = 0;
      %>
      <FIELDSET>
        <LEGEND>Informatica</LEGEND>
        <c:forEach var="env" items="${destEnvs}" varStatus="envStatus">
          <DIV class="paragraph">
            <LABEL class="env">${env}</LABEL>
            <c:choose>
              <c:when test="${envStatus.index != 0}">
                <INPUT class="checkbox" type="checkbox"
                  id="${env}_CHECK" name="${env}_CHECK"
                  checked="checked">

                <span id="${env}_CHECK_INPUT" class="CHECK_INPUT">
                  <INPUT class="username" type="text" id="${env}_USR"
                  name="${env}_USR" placeholder="Username"> <INPUT
                  class="password" type="password" id="${env}_PWD"
                  name="${env}_PWD" placeholder="Password">
                </span>
                <!--INPUT class="test_button" type=submit value=Test-->

                <span id="${env}_CHECK_SELECT" class="CHECK_SELECT">
                  Same as <SELECT name="${env}_PRE_ENV">
                    <%
                      List<String> preEnvs = destEnvs.subList(0, count);
                            request.setAttribute("preEnvs", preEnvs);
                    %>
                    <c:forEach var="preEnv" items="${preEnvs}">
                      <OPTION value="${preEnv}">${preEnv}</OPTION>
                    </c:forEach>
                </SELECT>
                </span>
              </c:when>
              <c:otherwise>
                <INPUT class="checkbox" name="${env}_CHECK"
                  type="checkbox" disabled>
                <span id="${env}_CHECK_INPUT" class="INPUT"> <INPUT
                  class="username" type="text" id="${env}_USR"
                  name="${env}_USR" placeholder="Username" value=<%="" %>> <INPUT
                  class="password" type="password" id="${env}_PWD"
                  name="${env}_PWD" placeholder="Password">
                </span>
              </c:otherwise>
            </c:choose>

          </DIV>
          <%
            count += 1;
          %>
        </c:forEach>
      </FIELDSET>
      <br> <INPUT type=submit value=Save class=submit> <br>
    </FORM>
    <!--BEGIN FootValidator -->
  </DIV>
</DIV>

<%@ include file="footer.jsp"%>