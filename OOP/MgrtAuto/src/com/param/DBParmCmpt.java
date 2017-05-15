package com.param;

import java.sql.Connection;

public interface DBParmCmpt {
	
	public Connection getCnx() ;

	public String getDBHost();

	public String getDBMetaOwner();

	public String getDBPassword();

	public String getDBPort();

	public String getDBService();

	public String getDBUsername();
	
	public void setCnx(Connection cnx);
}