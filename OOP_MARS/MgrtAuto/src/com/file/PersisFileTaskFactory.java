package com.file;

import java.io.IOException;
import java.nio.file.Path;

import com.control.*;
import com.file.readers.PersisFileReader;
import com.infa.*;
import com.infa.objects.*;
import com.infa.util.InfaObjList;

public abstract class PersisFileTaskFactory implements MigrationTask {

	protected int exitValue;
	protected InfaObjList persisObjList = new InfaObjList();
	protected FileReaderFactory reader;
	protected FileWriterFactory writer;
	protected Path filepath;

	protected abstract void writePersisFile();

	protected abstract void runPlan();

	protected void readPersisFile() {
		try {
			reader = new PersisFileReader(persisObjList, filepath);
			reader.readFile();
			reader.closeReader();
			//System.out.println("readPersisFile:");
			//MgrtUtils.printInfaObjList(persisObjList);
			//MgrtUtils.printStringList(persisObjList.getShortIDList(), "");
		} catch (IOException e) {
			// exitValue = FAIL_R;
			e.printStackTrace();
		}
	}

	@Override
	public int execute() {

		printStart();
		runPlan();
		printEnd();
		return exitValue;
	}
	
	
	@Override
	public void run() {
		execute();
	}

}