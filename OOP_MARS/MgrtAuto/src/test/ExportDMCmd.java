package test;

import com.infa.*;
import com.param.containers.ParamContainer;

public class ExportDMCmd extends PMRepCmdFactory {

	ParamContainer params;
	
	public ExportDMCmd (ParamContainer params) {
		
		this.params = params;
		buildMap();
		setAction("objectexport");
	}
	
	@Override
	protected void buildMap() {
		
		addOption("-i", params.getCustFileParm().getDMInputPersisFile().toString());
		addOption("-u", params.getCustFileParm().getDMXMLFile().toString());
		addOption("-l", params.getCustFileParm().getDMExpLog().toString());
		addOption("-m", "");
		addOption("-s", "");
		addOption("-b", "");
		addOption("-r", "");
	}

	@Override
	public String getStartNotice() {
		return "Exporting DM objects ...";
	}

	@Override
	public String getSuccessNotice() {
		return "DM objects exported";
	}

	@Override
	public String getFailNotice() {
		return "Failed to export DM objects" ;
	}
	
}