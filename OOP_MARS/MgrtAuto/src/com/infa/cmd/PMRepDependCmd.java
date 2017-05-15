package com.infa.cmd;

import com.infa.*;
import com.param.containers.ParamContainer;

public class PMRepDependCmd extends PMRepCmdFactory {

	ParamContainer param;
	
	public PMRepDependCmd (ParamContainer param) {
		
		this.param = param;
		buildMap();
		setAction("listobjectdependencies");
	}
	
	@Override
	protected void buildMap() {
		
		addOption("-i", param.getCustFileParm().getCRQPersisFile().toString());
		addOption("-u", param.getCustFileParm().getDependPersisFile().toString());
		addOption("-d", "source,target");
		addOption("-p", "children");
	}

	@Override
	public String getStartNotice() {
		return "Retrieving Dependencies ...";
	}

	@Override
	public String getSuccessNotice() {
		return "Dependency persistent file generated";
	}

	@Override
	public String getFailNotice() {
		return "Failed to generate dependency persistent file" ;
	}
	
}