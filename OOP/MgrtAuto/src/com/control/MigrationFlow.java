package com.control;

import java.io.IOException;
import java.util.*;

import com.file.tasks.*;
import com.infa.*;
import com.infa.cmd.*;
import com.infa.tasks.*;
import com.db.tasks.*;
import com.control.tasks.*;
import com.param.containers.ParamContainer;

public class MigrationFlow extends Thread {

	private ParamContainer params;
	private Queue<FlowTask> taskQueue = new LinkedList<FlowTask>();

	public MigrationFlow(ParamContainer params) throws IOException {
		this.params = params;
		params.getRunParm().setMigrationFlowThread(this);
		setFlow();
	}

	private void setFlow()  {

	
		FlowTask taskInitConnect = new  InitFlowTask (params);
		taskQueue.add(taskInitConnect);

		FlowTask taskdeploydepend = new DeployDependFlowTask (params);
		taskQueue.add(taskdeploydepend);
		
		FlowTask taskInspect = new InspectFlowTask(params);
		taskQueue.add(taskInspect);
		
		FlowTask taskImportPrep= new ExpImpPrepFlowTask(params);
		taskQueue.add(taskImportPrep);
		
		FlowTask taskExpInteg = new ExpIntegFlowTask (params);
		taskQueue.add(taskExpInteg);
		
		FlowTask taskTransition = new TransitionFlowTask (params) ;
		taskQueue.add(taskTransition);
		
		/*FlowTask taskImport = new ImportFlowTask(params);
		taskQueue.add(taskImport);
		
		FlowTask taskLabel = new LabelFlowTask(params);
		taskQueue.add(taskLabel);*/
		
		FlowTask taskCleanup = new CleanupTask (params);
		taskQueue.add(taskCleanup);
	
	}

	@Override
	public void run() {

		while (! taskQueue.isEmpty() && ! isInterrupted()) {
			FlowTask task = taskQueue.remove();
			task.execute();
		}
	}
	

}