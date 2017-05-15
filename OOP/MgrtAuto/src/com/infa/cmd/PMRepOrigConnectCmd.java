package com.infa.cmd;

import com.param.containers.*;
import com.infa.*;


public class PMRepOrigConnectCmd extends PMRepCmdFactory {
	
	ParamContainer param;
	
	public PMRepOrigConnectCmd ( ParamContainer param) {
	
		this.param = param;
		buildMap();
		setAction("connect");
	}

	@Override
	protected void buildMap() {
		
		addOption("-r", param.getOrignInfaParm().getInfaRepo());
		addOption("-h", param.getOrignInfaParm().getInfaHost());
		addOption("-o", param.getOrignInfaParm().getInfaPort());
		addOption("-n", param.getOrignInfaParm().getInfaUsername());
		addOption("-x", param.getOrignInfaParm().getInfaPassword());
	}

	@Override
	public String getStartNotice() {
		// TODO Auto-generated method stub
		return "Connecting to " + param.getOrignInfaParm().getInfaRepo() + " ...";
	}

	@Override
	public String getSuccessNotice() {
		// TODO Auto-generated method stub
		return "Connected to " + param.getOrignInfaParm().getInfaRepo();
	}

	@Override
	public String getFailNotice() {
		// TODO Auto-generated method stub
		return "Failed to connect to " +  param.getOrignInfaParm().getInfaRepo();
	}

	
	
}