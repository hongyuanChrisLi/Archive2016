package com.web.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.control.MgrtOpts;
import com.web.param.DBLoginContainer;

public final class DBCnx {

	public static void tryCnx(DBLoginContainer dbParm) {

		Connection cnx = null;
		
		// Default message
		dbParm.setMsg("Ops. There database are issues.");
		dbParm.setExitVal(MgrtOpts.EXIT_FAIL);
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			dbParm.setMsg("Ops. Driver Issue. Contact your Admin");
			dbParm.setExitVal(MgrtOpts.EXIT_FAIL);
			return ;
		}

		try {
			cnx = DriverManager.getConnection(dbParm.getURL(),
					dbParm.getUsr(), dbParm.getPasswd());
		} catch (SQLException e) {
			int errCode = e.getErrorCode();
			System.out.println(errCode);
			if ( errCode == 1017) {
				dbParm.setMsg("Invalid Username or Password");
			} else {
				dbParm.setMsg("Database Not Available");
			}
			dbParm.setExitVal(MgrtOpts.EXIT_FAIL);
			return;
		}

		if (cnx != null) {
			dbParm.setExitVal(MgrtOpts.EXIT_SUCCESS);
			System.out.println("Acquired connection");
		} 
		
		return ;
	}
	
	public static void closeCnx (Connection cnx) {
		try {
			cnx.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
