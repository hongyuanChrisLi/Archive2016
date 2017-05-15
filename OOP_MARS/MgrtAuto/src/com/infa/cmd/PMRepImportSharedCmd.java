package com.infa.cmd;

import com.infa.*;
import com.param.containers.ParamContainer;

public class PMRepImportSharedCmd extends PMRepCmdFactory {

	ParamContainer params;
	
	public PMRepImportSharedCmd (ParamContainer params) {
		
		this.params = params;
		buildMap();
		setAction("objectimport");
	}
	
	@Override
	protected void buildMap() {
		
		//addOption("-c", params.getSharedCtrlFile());
		addOption("-i", params.getCustFileParm().getSharedXMLFile().toString());
		addOption("-c", params.getCustFileParm().getSharedCtrlFile().toString());
		addOption("-l", params.getCustFileParm().getSharedImportLog().toString());
		addOption("-p", "");
	}

	@Override
	public String getStartNotice() {
		return "Importing Shared objects ...";
	}

	@Override
	public String getSuccessNotice() {
		return "Shared objects imported";
	}

	@Override
	public String getFailNotice() {
		return "Failed to import shared objects" ;
	}
	
}