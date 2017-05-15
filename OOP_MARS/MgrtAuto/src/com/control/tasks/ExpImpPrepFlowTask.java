package com.control.tasks;


import com.control.*;
import com.db.tasks.NewWFCheckTask;
import com.file.tasks.CtrlFileTask;
import com.file.tasks.InputPersisFileTask;
import com.param.containers.ParamContainer;

public class ExpImpPrepFlowTask extends ParallelFlowTask {

	ParamContainer params;

	public ExpImpPrepFlowTask(ParamContainer params) {

		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	protected void buildThreadsList() {
		MigrationTask taskCtrlFile = new CtrlFileTask (params);
		threads.add(new Thread(taskCtrlFile));
		
		MigrationTask taskInputPersisFile = new InputPersisFileTask(params);
		threads.add(new Thread(taskInputPersisFile));
		
		MigrationTask taskWFCheck = new NewWFCheckTask (params);
		threads.add(new Thread(taskWFCheck));
		
		
	}



}