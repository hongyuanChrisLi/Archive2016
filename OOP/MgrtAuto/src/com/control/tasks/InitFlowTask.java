package com.control.tasks;

import com.control.*;
import com.db.tasks.DBDestConnectTask;
import com.db.tasks.DBOriginConnectTask;
import com.file.tasks.SerFileTask;
import com.infa.PMRepCmdFactory;
import com.infa.cmd.PMRepOrigConnectCmd;
import com.infa.tasks.PMRepGenericTask;
import com.param.containers.ParamContainer;

public class InitFlowTask extends ParallelFlowTask {
	
	ParamContainer params; 
	
	public InitFlowTask ( ParamContainer params ) {
		
		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}
	

	@Override
	protected void buildThreadsList() {
		PMRepCmdFactory cmdInfaOrigConnect = new PMRepOrigConnectCmd(params);
		MigrationTask taskInfaOrigConnect = new PMRepGenericTask(cmdInfaOrigConnect,params);
		threads.add(new Thread(taskInfaOrigConnect));
		
		MigrationTask taskDBOrigConnect = new DBOriginConnectTask(params);
		threads.add(new Thread(taskDBOrigConnect));
		
		MigrationTask taskDBDestConnect = new DBDestConnectTask(params);
		threads.add(new Thread(taskDBDestConnect));
		
		MigrationTask taskSerFile = new SerFileTask(params);
		threads.add(new Thread(taskSerFile));
		
	}
	
}