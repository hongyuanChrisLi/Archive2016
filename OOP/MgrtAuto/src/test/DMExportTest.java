package test;

import java.io.IOException;
import java.util.*;

import com.file.tasks.*;
import com.infa.*;
import com.infa.cmd.*;
import com.infa.tasks.*;
import com.db.tasks.*;
import com.control.tasks.*;
import com.control.*;
import com.param.containers.ParamContainer;

public class DMExportTest extends Thread {

	private ParamContainer params;
	private Queue<FlowTask> taskQueue = new LinkedList<FlowTask>();

	public DMExportTest(ParamContainer params) throws IOException {
		this.params = params;
		params.getRunParm().setMigrationFlowThread(this);
		setFlow();
	}

	private void setFlow() throws IOException {

	
		FlowTask taskInitConnect = new  InitFlowTask (params);
		taskQueue.add(taskInitConnect);
		

		PMRepCmdFactory cmdExportDM = new ExportDMCmd(params);
		FlowTask taskExportDM= new PMRepGenericTask(cmdExportDM,params);
		taskQueue.add(taskExportDM);
		
		/*PMRepCmdFactory cmdImportShared = new PMRepImportSharedCmd(params);
		FlowTask taskImportShared = new PMRepGenericTask (cmdImportShared, params);
		taskQueue.add(taskImportShared);
		
		PMRepCmdFactory cmdImportDM = new PMRepImportDMCmd(params);
		FlowTask taskImportDM = new PMRepGenericTask (cmdImportDM, params);
		taskQueue.add(taskImportDM);*/
		
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