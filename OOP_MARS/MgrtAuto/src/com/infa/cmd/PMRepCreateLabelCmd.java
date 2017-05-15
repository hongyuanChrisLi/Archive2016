package com.infa.cmd;

import com.infa.*;
import com.param.containers.ParamContainer;

public class PMRepCreateLabelCmd extends PMRepCmdFactory {

	ParamContainer params;
	String crq;
	
	public PMRepCreateLabelCmd (ParamContainer params) {
		
		this.params = params;
		this.crq = params.getInitParm().getMgrtReq().getCRQ();
		buildMap();
		setAction("createlabel");
	}
	
	@Override
	protected void buildMap() {
		
		addOption("-a", crq);
	}

	@Override
	public String getStartNotice() {
		return "Creating Label " + crq + " ...";
	}

	@Override
	public String getSuccessNotice() {
		return "Label created";
	}

	@Override
	public String getFailNotice() {
		return "Failed to create label" ;
	}
	
}