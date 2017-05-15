package com.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import com.control.MigrationTask;
import com.file.readers.IntegSvcFileReader;
import com.file.util.CheckSumGen;
import com.file.util.GenericDeserializer;
import com.file.util.GenericSerializer;
import com.infa.util.IntegSvcMap;
import com.param.containers.ParamContainer;
import com.control.*;

public abstract class SerFileTaskFactory implements MigrationTask {

	// private ParamContainer params;
	protected long preModifiedTS;
	protected String objSerFile;
	protected String dataFlatFile;

	protected int exitValue;

	@Override
	public int execute() {

		printStart();
		
		getPreTS();
		//System.out.println("Before " + preModifiedTS);
		exitValue = comparedModifiedTS();

		if (exitValue == MgrtOpts.SAME_DATA_FILE) {
			deserializeObj();
		} else {
			genObj();
			serializeObj();
			updPreTS();
		}

		//System.out.println("After " + preModifiedTS);
		printEnd();
		
		return exitValue;
	}

	@Override
	public void run() {
		execute();

	}

	
	private synchronized int comparedModifiedTS() {

		File dataFile = new File(dataFlatFile);
		long curModifiedTS = dataFile.lastModified();

		if (fileExists(objSerFile) && curModifiedTS == preModifiedTS) {
				return MgrtOpts.SAME_DATA_FILE;
		}
		
		preModifiedTS = curModifiedTS;
		return MgrtOpts.DIFF_DATA_FILE;
	}
	

	private boolean fileExists(String filename) {
		File file = new File(filename);
		if (file.exists() && !file.isDirectory()) {
			return MgrtOpts.FILE_EXIST;
		}
		return MgrtOpts.FILE_NOT_FOUND;
	}


	protected abstract void genObj();

	protected abstract void serializeObj();

	protected abstract void deserializeObj();

	protected abstract void getPreTS();
	
	protected abstract void updPreTS();
}