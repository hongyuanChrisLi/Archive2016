package com.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.control.MigrationTask;
import com.file.FileWriterFactory;
import com.control.*;


public abstract class DBConnectFactory implements MigrationTask {

	protected String host;
	protected String port;
	protected String service;
	protected String username;
	protected String password;
	protected Connection cnx;
	protected FileWriterFactory displayLogWriter;
	protected Thread mgrtFlowThread;
	private int exitValue = MgrtOpts.EXIT_SUCCESS;

	private Connection Connect() {
		
		// System.out.println("-------- Oracle JDBC Connection Testing ------");
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			//System.out.println("Where is your Oracle JDBC Driver?");
			exitValue = MgrtOpts.EXIT_FAIL;
			e.printStackTrace();
			return null;
		}

		// System.out.println("Oracle JDBC Driver Registered!");

		Connection connection = null;
		//System.out.println(username + ", " + password);

		 try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//"
					+ this.host + ":" + this.port + "/" + this.service,
					this.username, this.password);

		} catch (SQLException e) {

			//System.out.println("Connection Failed! Check output console");
			exitValue = MgrtOpts.EXIT_FAIL;
			e.printStackTrace();
			return null;

		}

		if (connection != null) {
			//System.out.println("Connected to " + this.service);
		} else {
			//System.out.println("Failed to make connection!");
			exitValue = 3;
		}

		return connection;
	}
	
	@Override
	public int execute() {
		printStart();
		cnx = Connect();
		setConnection();
		printEnd();
		return exitValue;
	}
	
	
	@Override
	public void printStart() {
		try {
			displayLogWriter.append("Connecting to Database " + this.service + " ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void printEnd() {
		
		try {
			if (exitValue == MgrtOpts.EXIT_SUCCESS) {
				displayLogWriter.append("Connected to " + this.service);
			}else {
				displayLogWriter.append("Failed to connect to " + this.service);
				mgrtFlowThread.interrupt();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		execute();
	}
	
	protected abstract void setConnection ();

}
