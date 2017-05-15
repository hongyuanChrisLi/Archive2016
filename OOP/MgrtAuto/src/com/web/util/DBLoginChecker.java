package com.web.util;

import java.sql.Connection;

import com.db.DBConnectFactory;
import com.db.util.DBCnx;;

public class DBLoginChecker{

	private String password;
	private String username;
	private String host = "mbcdwzcd-scan";
	private String port = "6001";
	private String service = "MBCDWZCDDB";
	
	public DBLoginChecker (String username, String password) {
		this.username = username;
		this.password = password;
		this.host = "mbcdwzcd-scan";
		this.port = "6001";
		this.service = "MBCDWZCDDB";
	}
	
	public int checkLogin () {
		String jdbcStr = "jdbc:oracle:thin:@//"
				+ this.host + ":" + this.port + "/" + this.service;
		System.out.println(jdbcStr + " " + username + " " + password);
		
		Connection cnx = DBCnx.getCnx(jdbcStr, username, password);
		
		
		if ( cnx != null ) {
			return 1;
		}
		
		return 0;
	}
	
}