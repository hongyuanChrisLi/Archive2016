package com.web.param;

import java.sql.Connection;

public class DBLoginContainer {

	private String url;
	private String usr;
	private String passwd;
	private Connection cnx;
	private String msg;
	private int exitVal;
	
	public DBLoginContainer () {};
	public DBLoginContainer (String url, String usr, String passwd) {
		this.url = url;
		this.usr = usr;
		this.passwd = passwd;
	}
	
	public void setURL (String url) {
		this.url = url;
	}
	
	public void setUsr (String usr) {
		this.usr = usr;
	}
	
	public void setPasswd (String passwd) {
		this.passwd = passwd;
	}
	
	public void setCnx (Connection cnx) {
		this.cnx = cnx;
	}
	
	public void setMsg (String msg) {
		this.msg = msg;
	}
	
	public void setExitVal (int exitVal ) {
		this.exitVal = exitVal;
	}
	
	public String getURL () {
		return url;
	}
	
	public String getUsr() {
		return usr;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public Connection getCnx () {
		return cnx;
	}
	
	public String getMsg () {
		return msg;
	}
	
	public int getExitVal () {
		return exitVal;
	}
}
