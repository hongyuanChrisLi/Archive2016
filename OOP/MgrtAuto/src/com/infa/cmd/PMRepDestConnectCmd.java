package com.infa.cmd;

import com.param.containers.*;
import com.infa.*;


public class PMRepDestConnectCmd extends PMRepCmdFactory {
	
	ParamContainer param;
	
	public PMRepDestConnectCmd ( ParamContainer param) {
	
		this.param = param;
		buildMap();
		setAction("connect");
	}
	
	@Override
	protected void buildMap() {
		
		addOption("-r", param.getDestInfaParm().getInfaRepo());
		addOption("-h", param.getDestInfaParm().getInfaHost());
		addOption("-o", param.getDestInfaParm().getInfaPort());
		addOption("-n", param.getDestInfaParm().getInfaUsername());
		addOption("-x", param.getDestInfaParm().getInfaPassword());
	}

	@Override
	public String getStartNotice() {
		return "Connecting to " + param.getDestInfaParm().getInfaRepo() + " ..." ;
	}

	@Override
	public String getSuccessNotice() {
		return "Connected to " + param.getDestInfaParm().getInfaRepo();
	}

	@Override
	public String getFailNotice() {
		// TODO Auto-generated method stub
		return "Failed to connect to " + param.getDestInfaParm().getInfaRepo();
	}


	
	
}