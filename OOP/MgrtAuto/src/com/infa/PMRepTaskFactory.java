package com.infa;

import java.io.*;

import com.control.MigrationTask;
import com.file.*;

public abstract class PMRepTaskFactory implements MigrationTask {

	private boolean islog = true;
	protected PMRepCmdFactory cmd;
	protected Thread mgrtFlowThread;
	protected FileWriterFactory pmrepLogWriter;
	protected int exitValue;

	protected void runPMRepCmd() throws IOException {

		String[] cmd_splits = cmd.getCmd().split(" ");

		ProcessBuilder probuilder = new ProcessBuilder(cmd_splits);
		Process process = probuilder.start();

		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;

		if (islog) {

			while ((line = br.readLine()) != null) {
				pmrepLogWriter.append(line);
				//System.out.println(line);
			}

		} else {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}

		pmrepLogWriter.flush();
		br.close();

		exitValue = process.exitValue();
		process.destroy();
		// int exit_val = process.exitValue();

	}

	@Override
	public int execute() {
		// TODO Auto-generated method stub
		printStart();

		try {
			runPMRepCmd();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		printEnd();

		return exitValue;
	}

	/*
	 * @Override public void printStart() {
	 * 
	 * try { displayLogWriter.append(cmd.getStartNotice()); } catch (IOException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } }
	 * 
	 * @Override public void printEnd() { try {
	 * 
	 * if (exitValue == 0) displayLogWriter.append(cmd.getSuccessNotice()); else
	 * { displayLogWriter.append(cmd.getFailNotice());
	 * mgrtFlowThread.interrupt(); } }catch (IOException e) {
	 * e.printStackTrace(); } }
	 */

	@Override
	public void run() {
		execute();
	}

}