package com.control.tasks;

import com.control.*;
import com.file.tasks.XMLIntegSvcUpdTask;
import com.infa.PMRepCmdFactory;
import com.infa.cmd.PMRepDestConnectCmd;
import com.infa.tasks.PMRepGenericTask;
import com.param.containers.ParamContainer;

public class TransitionFlowTask extends ParallelFlowTask {

	ParamContainer params;

	public TransitionFlowTask(ParamContainer params) {

		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	protected void buildThreadsList() {
		PMRepCmdFactory cmdInfaDestConnect = new PMRepDestConnectCmd(params);
		MigrationTask taskInfaDestConnect = new PMRepGenericTask(
				cmdInfaDestConnect, params);
		threads.add(new Thread(taskInfaDestConnect));

		
		/*System.out.println("DM Empty" + params.getIsDMListEmpty());
		System.out.println("WF Empty" + params.getNewWorkflowSet().isEmpty());*/
		
		if ( ! ( params.getRunParm().getIsDMListEmpty() || params.getRunParm().getNewWorkflowSet().isEmpty())) {
			System.out.println("Get in here");
			MigrationTask taskXMLIntegSvcUpd = new XMLIntegSvcUpdTask(params);
			threads.add(new Thread(taskXMLIntegSvcUpd));
		}

		
	}


}