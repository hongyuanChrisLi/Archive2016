package com.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.control.MgrtOpts;
import com.control.MigrationTask;

public abstract class SQLTaskFactory implements MigrationTask {

	protected int exitValue = MgrtOpts.EXIT_SUCCESS;
	private ResultSet res;
	protected Connection cnx;
	protected String sqlStr;

	@Override
	public int execute() {
		printStart();
		preRun();
		
		if (exitValue == MgrtOpts.EXIT_SUCCESS) {
			runSQL();
			postRun();
		}
		
		printEnd();
		return exitValue;
	}

	@Override
	public void run() {
		execute();

	}

	protected void runSQL() {
		Statement stmt = null;
		try {
			stmt = cnx.createStatement();
			res = stmt.executeQuery(sqlStr);
			while (res.next()) {
				processRecord(res.getString("RES"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected abstract void preRun();

	// protected abstract void runSQL ();
	protected abstract void postRun();

	protected abstract void processRecord(String record);
}