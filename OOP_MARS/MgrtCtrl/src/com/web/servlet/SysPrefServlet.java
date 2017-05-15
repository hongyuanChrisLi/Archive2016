package com.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.param.InfaCredContainer;
import com.web.param.SysPrefContainer;

@SuppressWarnings("serial")
public class SysPrefServlet extends HttpServlet {

  SysPrefContainer sysPref = new SysPrefContainer();
  
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    HttpSession session = request.getSession();

    @SuppressWarnings("unchecked")
    List<String> destEnvs = (List<String>) session.getAttribute("destEnvs");

    updInfaCredMap(destEnvs, request);
    
    session.setAttribute("sysPref", sysPref);
    request.getRequestDispatcher("/system.jsp").forward(request, response);;
  }

  private void updInfaCredMap(List<String> envs , HttpServletRequest request) {
    for (String env : envs) {

      String checked = request.getParameter(env + "_CHECK");
      System.out.println(env + " " + checked);

      if (checked != null && checked.equals("on")) {

        String preEnv = request.getParameter(env + "_PRE_ENV");
        /*
         * System.out.println("same as " + preEnv ); System.out.println(
         * "pre user: " + sysPref.getInfaCred(preEnv).getUser());
         * System.out.println("pre pass: " +
         * sysPref.getInfaCred(preEnv).getPasswd());
         */
        sysPref.putInfaCred(env, sysPref.getInfaCred(preEnv));

      } else {

        String user = request.getParameter(env + "_USR");
        String passwd = request.getParameter(env + "_PWD");
        /*
         * System.out.println("  user: " + user); System.out.println("  pass: "
         * + passwd);
         */

        InfaCredContainer infaCred = new InfaCredContainer();
        infaCred.setUser(user);
        infaCred.setPasswd(passwd);

        sysPref.putInfaCred(env, infaCred);
      }
    }
  }

}