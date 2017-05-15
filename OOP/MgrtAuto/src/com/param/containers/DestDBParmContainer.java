package com.param.containers;

import java.sql.Connection;

import com.control.requests.MigrationRequest;
import com.param.DBParmCmpt;

public class DestDBParmContainer implements DBParmCmpt {

	private Connection cnx;
	private EnvContainer envs;
	private MigrationRequest mgrtReq;

	public DestDBParmContainer(MigrationRequest mgrtReq, EnvContainer envs) {

		this.mgrtReq = mgrtReq;
		this.envs = envs;
	}

	@Override
	public void setCnx(Connection cnx) {
		this.cnx = cnx;
	}

	@Override
	public String getDBHost() {
		return envs.get(mgrtReq.getDestEnv() + "_DBHOST");
	}

	@Override
	public String getDBPort() {
		return envs.get(mgrtReq.getDestEnv() + "_DBPORT");
	}

	@Override
	public String getDBService() {
		return envs.get(mgrtReq.getDestEnv() + "_DBSERVICE");
	}

	@Override
	public String getDBUsername() {
		return envs.get(mgrtReq.getDestEnv() + "_DBUSER");
	}

	@Override
	public String getDBPassword() {
		return envs.get(mgrtReq.getDestEnv() + "_DBPASS");
	}

	@Override
	public String getDBMetaOwner() {
		return envs.get(mgrtReq.getDestEnv() + "_DBMETA");
	}

	@Override
	public Connection getCnx() {
		return cnx;
	}

}