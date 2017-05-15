package com.control.tasks;


import java.io.IOException;

import com.control.*;
import com.db.tasks.ImpObjListTask;
import com.db.tasks.NewLabelCheckTask;
import com.file.tasks.DependAnalysisTask;
import com.file.tasks.PersisInfaObjFilterTask;
import com.infa.PMRepCmdFactory;
import com.infa.cmd.PMRepApplyLabelCmd;
import com.infa.cmd.PMRepCRQQueryCmd;
import com.infa.cmd.PMRepCreateLabelCmd;
import com.infa.cmd.PMRepDependCmd;
import com.infa.cmd.PMRepImportDMCmd;
import com.infa.cmd.PMRepImportSharedCmd;
import com.infa.tasks.PMRepGenericTask;
import com.param.containers.ParamContainer;


public class DeployDependFlowTask extends SerialFlowTask {
	
	private ParamContainer params;
	
	public DeployDependFlowTask (ParamContainer params) {
		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	protected void execSerial() {
		
		PMRepCmdFactory cmdCRQQuery = new PMRepCRQQueryCmd(params);
		FlowTask taskCRQQuery = new PMRepGenericTask(cmdCRQQuery,params);
		taskCRQQuery.execute();
		FlowTask taskFilter;
		
		try {
			taskFilter = new PersisInfaObjFilterTask(params);
			taskFilter.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PMRepCmdFactory cmdDepend = new PMRepDependCmd(params);
		FlowTask taskDepend = new PMRepGenericTask(cmdDepend,params);
		taskDepend.execute();
		
		FlowTask taskDepenAnalysis = new DependAnalysisTask(params);
		taskDepenAnalysis.execute();
		
		
	}
	
}