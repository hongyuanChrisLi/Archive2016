package com.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DBCnx {
	
	public static Connection getCnx (String jdbcStr, String username, String password ) {
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			//System.out.println("Where is your Oracle JDBC Driver?");
			//exitValue = MgrtOpts.EXIT_FAIL;
			e.printStackTrace();
			return null;
		}

		// System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;
		//System.out.println(username + ", " + password);

		 try {
			connection = DriverManager.getConnection(jdbcStr, username, password);

		} catch (SQLException e) {

			//System.out.println("Connection Failed! Check output console");
			//exitValue = MgrtOpts.EXIT_FAIL;
			e.printStackTrace();
			return null;

		}

		if (connection != null) {
			//System.out.println("Connected to " + this.service);
		} else {
			//System.out.println("Failed to make connection!");
			//exitValue = 3;
		}

		return connection;
		
	}
}
