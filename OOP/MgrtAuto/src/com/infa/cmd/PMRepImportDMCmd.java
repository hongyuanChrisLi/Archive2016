package com.infa.cmd;

import com.infa.*;
import com.param.containers.ParamContainer;

public class PMRepImportDMCmd extends PMRepCmdFactory {

	ParamContainer params;
	
	public PMRepImportDMCmd (ParamContainer params) {
		
		this.params = params;
		buildMap();
		setAction("objectimport");
	}
	
	@Override
	protected void buildMap() {
		
		//addOption("-c", params.getSharedCtrlFile());
		addOption("-i", params.getCustFileParm().getDMXMLFile().toString());
		addOption("-c", params.getCustFileParm().getDMCtrlFile().toString());
		addOption("-l", params.getCustFileParm().getDMImportLog().toString());
		addOption("-p", "");
	}

	@Override
	public String getStartNotice() {
		return "Importing DM objects ...";
	}

	@Override
	public String getSuccessNotice() {
		return "DM objects imported";
	}

	@Override
	public String getFailNotice() {
		return "Failed to import DM objects" ;
	}
	
}