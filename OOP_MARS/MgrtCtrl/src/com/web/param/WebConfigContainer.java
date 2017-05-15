package com.web.param;

import java.util.ArrayList;
import java.util.List;


public class WebConfigContainer {
	
	
	
	private List<DBInfoContainer> dbList = new ArrayList<DBInfoContainer> (); 
	private List<String> orignEnvList = new ArrayList<String> ();
	private List<String> destEnvList = new ArrayList<String> ();
	private String homeDir;
	private String imptCtrlDir;
	
	public void addDBInfo (DBInfoContainer dbInfo){
		dbList.add(dbInfo);
	}
	
	public void addOrignEnv (String orignEnv) {
		orignEnvList.add(orignEnv);
	}
	
	public void addDestEnv (String destEnv) {
		destEnvList.add(destEnv);
	}
	
	public List<DBInfoContainer> getDBList () {
		return dbList;
	}
	
	public List<String>  getOrignEnvList () {
		return orignEnvList;
	}
	
	public List<String> getDestEnvList () {
		return destEnvList;
	}

  public String getHomeDir() {
    return homeDir;
  }

  public void setHomeDir(String homeDir) {
    this.homeDir = homeDir;
  }

  public String getImptCtrlDir() {
    return imptCtrlDir;
  }

  public void setImptCtrlDir(String imptCtrlDir) {
    this.imptCtrlDir = imptCtrlDir;
  }
	
	
}