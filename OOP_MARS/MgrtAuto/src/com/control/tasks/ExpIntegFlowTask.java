package com.control.tasks;

import com.control.*;
import com.file.subtasks.InfaTypeSerFileTask;
import com.file.subtasks.IntegSvcSerFileTask;
import com.infa.PMRepCmdFactory;
import com.infa.cmd.PMRepExportDMCmd;
import com.infa.cmd.PMRepExportSharedCmd;
import com.infa.tasks.*;
import com.param.containers.ParamContainer;

public class ExpIntegFlowTask extends ParallelFlowTask {
	
	ParamContainer params; 
	
	public ExpIntegFlowTask ( ParamContainer params ) {
		
		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}
	
	@Override
	protected void buildThreadsList() {
		
		/*MigrationTask taskInfaTypeSerFile = new InfaTypeSerFileTask(params);
		threads.add(new Thread(taskInfaTypeSerFile));*/
		
		if ( ! params.getRunParm().getIsSharedListEmpty()) {
			PMRepCmdFactory cmdExportShared = new PMRepExportSharedCmd(params);
			MigrationTask taskExportShared = new PMRepGenericTask(cmdExportShared,params);
			threads.add(new Thread(taskExportShared));
		}
		
		if ( ! params.getRunParm().getIsDMListEmpty()) {
			PMRepCmdFactory cmdExportDM = new PMRepExportDMCmd(params);
			MigrationTask taskExportDM= new PMRepGenericTask(cmdExportDM,params);
			threads.add(new Thread(taskExportDM));
			
			/*if ( ! params.getRunParm().getNewWorkflowSet().isEmpty()) {
				MigrationTask taskIntegSvcSerFile = new IntegSvcSerFileTask(params);
				threads.add(new Thread(taskIntegSvcSerFile));	
			}*/
		}
		
	}
	
}