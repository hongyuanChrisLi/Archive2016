package com.infa.tasks;

import java.io.IOException;

import com.file.FileWriterFactory;
import com.infa.*;
import com.param.containers.ParamContainer;


public class PMRepGenericTask extends PMRepTaskFactory {
	
	protected FileWriterFactory displayLogWriter;
	
	public PMRepGenericTask (PMRepCmdFactory cmd, ParamContainer params ) {
		this.cmd = cmd;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
		this.mgrtFlowThread = params.getRunParm().getMigrationFlowThread();
		this.pmrepLogWriter = params.getCustFileParm().getPmrepLogWriter();
		//System.out.println( "PMRepGenericTask: " + cmd.getCmd());
	}
	
	
	@Override
	public void printStart() {

		try {
			displayLogWriter.append(cmd.getStartNotice());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void printEnd() {
		try {
			
			if (exitValue == 0)
				displayLogWriter.append(cmd.getSuccessNotice());
			else {
				displayLogWriter.append(cmd.getFailNotice());
				mgrtFlowThread.interrupt();
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}