package com.file.tasks;

import java.util.List;
import java.io.IOException;
import java.nio.file.Path;

import com.control.*;
import com.file.FileWriterFactory;
import com.file.writers.GenericFileWriter;
import com.file.writers.LogWriter;
import com.param.containers.ParamContainer;

public class InputPersisFileTask implements MigrationTask {

	private ParamContainer params;
	private int exitValue = MgrtOpts.EXIT_SUCCESS;
	private FileWriterFactory displayLogWriter;

	public InputPersisFileTask(ParamContainer params) {
		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	public int execute() {

		printStart();
		if (!params.getRunParm().getIsSharedListEmpty()) {
			writePersisFile(
					params.getCustFileParm().getSharedInputPersisFile(), params
							.getRunParm().getDependSrcTgtList().getSharedList()
							.getPersisEntryList());
		}

		if (!params.getRunParm().getIsDMListEmpty()) {
			writePersisFile(params.getCustFileParm().getDMInputPersisFile(),
					params.getRunParm().getInfaObjList().getPersisEntryList());
		}

		printEnd();
		return 0;
	}

	private void writePersisFile(Path filepath, List<String> persisEntries) {
		FileWriterFactory writer;
		try {
			writer = new GenericFileWriter(filepath);
			for (String entry : persisEntries) {
				writer.append(entry);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		execute();
	}

	@Override
	public void printStart() {
		try {
			displayLogWriter.append("Generating Import Persistent files ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void printEnd() {
		try {

			if (exitValue == MgrtOpts.EXIT_SUCCESS) {
				displayLogWriter
						.append("Import Persistent files are generated");
			} else {
				displayLogWriter
						.append("Failed to generate Import Persistent files ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}