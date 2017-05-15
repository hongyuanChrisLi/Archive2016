package com.db.tasks;


import com.db.*;
import com.param.containers.ParamContainer;

public class DBDestConnectTask extends DBConnectFactory{

	ParamContainer params;
	
	public DBDestConnectTask (ParamContainer params){
		this.params = params;
		this.host = params.getDestDBParm().getDBHost();
		this.port = params.getDestDBParm().getDBPort();
		this.service = params.getDestDBParm().getDBService();
		this.username = params.getDestDBParm().getDBUsername();
		this.password = params.getDestDBParm().getDBPassword();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
		this.mgrtFlowThread = params.getRunParm().getMigrationFlowThread();
	}

	@Override
	protected void setConnection() {
		params.getDestDBParm().setCnx(cnx);
	}

	
}