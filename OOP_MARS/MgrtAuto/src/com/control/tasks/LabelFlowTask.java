package com.control.tasks;


import com.control.*;
import com.db.tasks.ImpObjListTask;
import com.db.tasks.NewLabelCheckTask;
import com.infa.PMRepCmdFactory;
import com.infa.cmd.PMRepApplyLabelCmd;
import com.infa.cmd.PMRepCreateLabelCmd;
import com.infa.tasks.PMRepGenericTask;
import com.param.containers.ParamContainer;


public class LabelFlowTask extends SerialFlowTask {
	
	private ParamContainer params;
	
	public LabelFlowTask (ParamContainer params) {
		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	protected void execSerial() {
		
		MigrationTask taskNewLabelCheck = new NewLabelCheckTask ( params);
		taskNewLabelCheck.execute();
		
		MigrationTask taskImpObjList = new ImpObjListTask ( params);
		taskImpObjList.execute();
		
		//System.out.println("Is new Label: " + params.getIsNewLabel());
		
		if (params.getRunParm().getIsNewLabel() == MgrtOpts.NEW_LABEL) {
			
			PMRepCmdFactory cmdCreateLabel = new PMRepCreateLabelCmd(params);
			MigrationTask taskCreateLabel= new PMRepGenericTask(cmdCreateLabel,params);
			taskCreateLabel.execute();
		}
		
		PMRepCmdFactory cmdApplyLabel = new PMRepApplyLabelCmd(params);
		MigrationTask taskApplyLabel= new PMRepGenericTask(cmdApplyLabel,params);
		taskApplyLabel.execute();
		
	}
	
}