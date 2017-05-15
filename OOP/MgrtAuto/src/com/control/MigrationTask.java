package com.control;


public interface MigrationTask extends FlowTask, Runnable {
	
	public void printStart ();
	public void printEnd();
}