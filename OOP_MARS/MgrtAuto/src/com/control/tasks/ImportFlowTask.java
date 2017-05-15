package com.control.tasks;


import com.control.*;
import com.db.tasks.ImpObjListTask;
import com.db.tasks.NewLabelCheckTask;
import com.infa.PMRepCmdFactory;
import com.infa.cmd.PMRepApplyLabelCmd;
import com.infa.cmd.PMRepCreateLabelCmd;
import com.infa.cmd.PMRepImportDMCmd;
import com.infa.cmd.PMRepImportSharedCmd;
import com.infa.tasks.PMRepGenericTask;
import com.param.containers.ParamContainer;


public class ImportFlowTask extends SerialFlowTask {
	
	private ParamContainer params;
	
	public ImportFlowTask (ParamContainer params) {
		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	protected void execSerial() {
		
		if ( ! params.getRunParm().getIsSharedListEmpty()) {
			PMRepCmdFactory cmdImportShared = new PMRepImportSharedCmd(params);
			FlowTask taskImportShared = new PMRepGenericTask (cmdImportShared, params);
			taskImportShared.execute();
		}
		
		if ( ! params.getRunParm().getIsDMListEmpty()) {
			PMRepCmdFactory cmdImportDM = new PMRepImportDMCmd(params);
			FlowTask taskImportDM = new PMRepGenericTask (cmdImportDM, params);
			taskImportDM.execute();
		}
		
	}
	
}