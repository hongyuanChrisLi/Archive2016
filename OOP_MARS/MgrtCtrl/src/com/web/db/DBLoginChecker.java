package com.web.db;

import com.web.param.DBInfoContainer;
import com.web.param.DBLoginContainer;;

public class DBLoginChecker {

	private String password;
	private String username;

	public DBLoginChecker(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public DBLoginContainer checkLogin(DBInfoContainer dbInfo) {
		
		String host = dbInfo.getHost();
		String port = dbInfo.getPort();
		String service = dbInfo.getService();
		
		String jdbcStr = "jdbc:oracle:thin:@//" + host + ":" + port
				+ "/" + service;
		System.out.println(jdbcStr + " " + username + " " + password);

		DBLoginContainer dbParm = new DBLoginContainer(jdbcStr, username, password);
		DBCnx.tryCnx(dbParm);
		
		/*if ( dbParm.getExitVal() == MgrtOpts.EXIT_SUCCESS) {
			DBCnx.closeCnx(dbParm.getCnx());
			System.out.println("Connection closed");
		}*/
		
		return dbParm;
	}

}