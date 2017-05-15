package com.db.tasks;


import com.db.*;
import com.param.containers.ParamContainer;


public class DBOriginConnectTask extends DBConnectFactory {
	ParamContainer params;
	
	public DBOriginConnectTask (ParamContainer params){
		this.params = params;
		this.host = params.getOrignDBParm().getDBHost();
		this.port = params.getOrignDBParm().getDBPort();
		this.service = params.getOrignDBParm().getDBService();
		this.username = params.getOrignDBParm().getDBUsername();
		this.password = params.getOrignDBParm().getDBPassword();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	protected void setConnection() {
		params.getOrignDBParm().setCnx(cnx);
	}
	
}