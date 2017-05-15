package com.param.containers;

import java.sql.Connection;

import com.param.DBParmCmpt;
import com.control.requests.MigrationRequest;

public class OrignDBParmContainer implements DBParmCmpt {

	private Connection cnx;
	private EnvContainer envs;
	private MigrationRequest mgrtReq;

	public OrignDBParmContainer(MigrationRequest mgrtReq, EnvContainer envs) {

		this.mgrtReq = mgrtReq;
		this.envs = envs;
	}

	@Override
	public void setCnx(Connection cnx) {
		this.cnx = cnx;
	}

	@Override
	public String getDBHost() {
		return envs.get(mgrtReq.getOrignEnv() + "_DBHOST");
	}

	@Override
	public String getDBPort() {
		return envs.get(mgrtReq.getOrignEnv() + "_DBPORT");
	}

	@Override
	public String getDBService() {
		return envs.get(mgrtReq.getOrignEnv() + "_DBSERVICE");
	}

	@Override
	public String getDBUsername() {
		return envs.get(mgrtReq.getOrignEnv() + "_DBUSER");
	}

	@Override
	public String getDBPassword() {
		return envs.get(mgrtReq.getOrignEnv() + "_DBPASS");
	}

	@Override
	public String getDBMetaOwner() {
		return envs.get(mgrtReq.getOrignEnv() + "_DBMETA");
	}

	@Override
	public Connection getCnx() {
		return cnx;
	}

}