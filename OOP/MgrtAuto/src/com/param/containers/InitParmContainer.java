package com.param.containers;

import com.control.requests.MigrationRequest;

public class InitParmContainer  {

	String rootDir;
	String startUTCStr;
	String imptCtrlStr;
	
	EnvContainer envs;
	MigrationRequest mgrtReq;
	
	public InitParmContainer () {
	
		this.rootDir = "C:/Users/C2Lv/GoogleDrive/JavaProjects2016/OOP/MgrtAuto";
		this.startUTCStr = String.valueOf(System.currentTimeMillis() / 1000);
		this.imptCtrlStr = "C:\\\\Informatica\\\\9.6.1\\\\clients\\\\PowerCenterClient\\\\client\\\\bin\\\\impcntl.dtd";
	}
	
	public void setEnvContainer (EnvContainer envs) {
		this.envs = envs;
	}
	
	public void setMigrationRequest (MigrationRequest mgrtReq)  {
		this.mgrtReq = mgrtReq;
	}
	
	public void setRootDir (String dir) {
		this.rootDir = dir;
	}
	
	public EnvContainer getEnvParm () {
		return envs;
	}
	
	public MigrationRequest getMgrtReq () {
		return mgrtReq;
	}
	
	public String getRooDir () {
		return rootDir;
	}
	
	public String getStartUTCStr () {
		return startUTCStr;
	}

	public String getImptCtrlStr () {
		return imptCtrlStr;
	}
}