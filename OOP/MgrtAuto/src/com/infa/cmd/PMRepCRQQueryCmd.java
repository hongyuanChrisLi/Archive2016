package com.infa.cmd;

import com.infa.*;
import com.param.containers.ParamContainer;

public class PMRepCRQQueryCmd extends PMRepCmdFactory {

	private ParamContainer param;
	private String crq;
	
	public PMRepCRQQueryCmd (ParamContainer param) {
		this.param = param;
		this.crq = param.getInitParm().getMgrtReq().getCRQ();
		
		buildMap();
		setAction("executequery");
	}
	
	@Override
	protected void buildMap() {
		
		addOption("-q", crq );
		addOption("-u", param.getCustFileParm().getCRQPersisFile().toString());
	}

	@Override
	public String getStartNotice() {
		return "Querying " + crq + " ...";
	}

	@Override
	public String getSuccessNotice() {
		return "Query " + crq + " executed successfully";
	}

	@Override
	public String getFailNotice() {
		return "Failed to execute "  + crq ;
	}
	
}