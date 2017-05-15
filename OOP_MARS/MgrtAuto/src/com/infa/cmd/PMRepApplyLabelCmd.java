package com.infa.cmd;

import com.infa.*;
import com.param.containers.ParamContainer;

public class PMRepApplyLabelCmd extends PMRepCmdFactory {

	ParamContainer params;
	String crq;
	
	public PMRepApplyLabelCmd (ParamContainer params) {
		
		this.params = params;
		this.crq = params.getInitParm().getMgrtReq().getCRQ();
		buildMap();
		setAction("applylabel");
	}
	
	@Override
	protected void buildMap() {
		
		addOption("-a", crq);
		addOption("-i", params.getCustFileParm().getLabelPersisFile().toString());
	}

	@Override
	public String getStartNotice() {
		return "Applying Label " + crq + " ...";
	}

	@Override
	public String getSuccessNotice() {
		return "Label applied";
	}

	@Override
	public String getFailNotice() {
		return "Failed to apply label" ;
	}
	
}