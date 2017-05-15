package com.control.requests;

import java.util.*;

public class MigrationRequest {
	
	private String dbUser;
	
	private String orignEnv;
	private String orignInfaUser;
	private String orignInfaPasswd;
	
	private String destEnv;
	private String destInfauUser;
	private String destInfaPasswd;
	
	private String crq;
	private String requester;
	private String adminUser;
	private boolean isGroup;
	private boolean ignoreError;

	
	private List<String> migrateList = new ArrayList<String>();

	public void addInfaObject (String line) {
		migrateList.add(line);
	}
	
	public String getAdminUser () {
		return adminUser;
	}
	
	public String getCRQ() {
		return crq;
	}
	
	public String getDBUser () {
		return dbUser;
	}
	
	
	public String getDestEnv () {
		return destEnv;
	}
	
	public String getDestInfaPasswd () {
		return destInfaPasswd;
	}
	
	public String getDestInfaUser () {
		return destInfauUser;
	}
	
	public boolean getIsGroup () {
		return isGroup;
	}
	
	public List<String> getMigrateList () {
		return migrateList;
	}
	
	public String getOrignEnv () {
		return orignEnv;
	}
	
	public String getOrignInfaPasswd () {
		return orignInfaPasswd;
	}
	
	public String getOrignInfaUser () {
		return orignInfaUser;
	}
	
	public String getRequester () {
		return requester;
	}
	
	public void setAdminUser (String adminUser) {
		this.adminUser = adminUser;
	}
	
	public void setCRQ ( String crq ) {
		this.crq = crq;
	}
	
	public void setDBUser (String user ) {
		this.dbUser = user;
	}
	
	
	public void setDestEnv (String env) {
		this.destEnv = env;
	}
	
	public void setDestInfaPass (String passwd) {
		this.destInfaPasswd = passwd;
	}
	
	public void setDestInfaUser (String user) {
		this.destInfauUser = user;
	}	
	
	public void setIsGroup (boolean isGroup){
		this.isGroup = isGroup;
	}
	
	public void setOrignEnv (String env) {
		this.orignEnv = env;
	}
	
	public void setOrignInfaPass (String passwd) {
		this.orignInfaPasswd = passwd;
	}
	
	public void setOrignInfaUser (String user) {
		this.orignInfaUser = user;
	}
	
	public void setRequester (String requester) {
		this.requester = requester;
	}
	
	
	public void setDisplayLog() {
		
	}
	
	public String  getDisployLog() {
		return "C:\\Software\\apache-tomcat-7.0.69\\webapps\\HFSJSP\\content.txt";
	}
}