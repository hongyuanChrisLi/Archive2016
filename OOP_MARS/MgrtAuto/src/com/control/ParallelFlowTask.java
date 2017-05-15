package com.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.file.FileWriterFactory;

public abstract class ParallelFlowTask implements FlowTask {
	
	protected List<Thread> threads = new ArrayList<Thread> ();
	protected FileWriterFactory displayLogWriter;
	
	protected abstract void buildThreadsList () ; 
	
	private void printSeperator () {
		try {
			displayLogWriter.append("\n =================== \n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int execute() {
		
		printSeperator ();
		buildThreadsList();
		
		for (Thread t : threads ) {
			t.start();
		}
		
		for ( Thread t : threads ) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
}