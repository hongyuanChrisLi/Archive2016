package com.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.file.FileWriterFactory;

public abstract class SerialFlowTask implements FlowTask {
	
	protected FileWriterFactory displayLogWriter;
	//protected Queue<FlowTask> taskQueue = new LinkedList<FlowTask>();
	
	private void printSeperator () {
		try {
			displayLogWriter.append("\n =================== \n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	abstract protected void execSerial ();
	
	@Override
	public int execute() {
		
		printSeperator ();
		execSerial ();
		
	/*	while (! taskQueue.isEmpty() ) {
			FlowTask task = taskQueue.remove();
			task.execute();
		}
		*/
		return 0;
	}
	
}