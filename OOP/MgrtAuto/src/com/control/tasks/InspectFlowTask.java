package com.control.tasks;

import com.control.*;
import com.db.tasks.SrcDefCompareTask;
import com.db.tasks.TgtDefCompareTask;
import com.db.tasks.VerLabelCheckTask;
import com.param.containers.ParamContainer;

public class InspectFlowTask extends ParallelFlowTask {

	ParamContainer params;

	public InspectFlowTask(ParamContainer params) {

		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	protected void buildThreadsList() {
		MigrationTask taskVerLabelCheck = new VerLabelCheckTask(params);
		threads.add(new Thread(taskVerLabelCheck));
		
		MigrationTask taskSrcDefCompare = new SrcDefCompareTask(params);
		threads.add(new Thread(taskSrcDefCompare));
		
		MigrationTask taskTgtDefCompare = new TgtDefCompareTask(params);
		threads.add(new Thread(taskTgtDefCompare));
		
	}

}