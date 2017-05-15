package com.infa.cmd;

import com.infa.*;
import com.param.containers.ParamContainer;

public class PMRepExportSharedCmd extends PMRepCmdFactory {

	ParamContainer params;
	
	public PMRepExportSharedCmd (ParamContainer params) {
		
		this.params = params;
		buildMap();
		setAction("objectexport");
	}
	
	@Override
	protected void buildMap() {
		
		addOption("-i", params.getCustFileParm().getSharedInputPersisFile().toString());
		addOption("-u", params.getCustFileParm().getSharedXMLFile().toString());
		addOption("-l", params.getCustFileParm().getSharedExpLog().toString());
		addOption("-m", "");
		addOption("-s", "");
		addOption("-b", "");
		addOption("-r", "");
	}

	@Override
	public String getStartNotice() {
		return "Exporting shared objects ...";
	}

	@Override
	public String getSuccessNotice() {
		return "Shared objects exported";
	}

	@Override
	public String getFailNotice() {
		return "Failed to export shared objects" ;
	}
	
}