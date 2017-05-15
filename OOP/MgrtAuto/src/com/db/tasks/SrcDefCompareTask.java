package com.db.tasks;

import java.io.IOException;

import com.control.MgrtOpts;
import com.control.MgrtUtils;
import com.db.*;
import com.file.FileWriterFactory;
import com.param.containers.ParamContainer;

public class SrcDefCompareTask extends SQLCompareTaskFactory {

	private ParamContainer params;
	private FileWriterFactory displayLogWriter;
	private Thread mgrtFlowThread;
	private String srcListStr;

	public SrcDefCompareTask(ParamContainer params) {
		this.params = params;
		this.filepath = params.getSysFileParm().getSrcDefSQLFile();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
		this.mgrtFlowThread = params.getRunParm().getMigrationFlowThread();
		// this.extraInfaObjMap = params.getExtraInfaObjMap();
	}

	@Override
	public void printStart() {
		try {
			displayLogWriter
					.append("Comparing Dependent Source definitions ... ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void printEnd() {
		try {

			if (this.exitValue == MgrtOpts.EXIT_FAIL) {
				displayLogWriter.append("Source Compare Task Failed.");
				mgrtFlowThread.interrupt();
			} else {

				if (this.exitValue == MgrtOpts.EXIT_INFO) {
					// this.printStgStrKeys();
					displayLogWriter.append("Added Source Definitions: ");
					displayLogWriter.appendList(resList);
					//MgrtUtils.printStringList(resList, "\t");
					// System.out.println("Updated InfaObj List after Source: ");
					// MgrtUtils.printInfaObjList(params.getInfaObjList(),
					// "\t");
				} else {
					displayLogWriter.append("No additional Source added.");
				}

				displayLogWriter.append("Source Comparison Completed.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	protected void prepConf() {

		srcListStr = params.getRunParm().getSrcListStr();
		
		if (srcListStr.isEmpty()) {
			exitValue = MgrtOpts.EXIT_SKIP;
		} else {

			String sqlStrTmp = sqlTmpltStr.replaceAll("\\$srcListStr",
					srcListStr);

			sqlStrOrigin = sqlStrTmp.replaceAll("\\$metaOwner",
					params.getOrignDBParm().getDBMetaOwner());
			cnxOrigin = params.getOrignDBParm().getCnx();

			sqlStrDest = sqlStrTmp.replaceAll("\\$metaOwner",
					params.getDestDBParm().getDBMetaOwner());
			cnxDest = params.getDestDBParm().getCnx();

			infaObjList = params.getRunParm().getInfaObjList();
			dependList = params.getRunParm().getDependSrcTgtList();
			
			//System.out.println(sqlStrOrigin);
			//System.out.println(sqlStrDest);
			
		}

	}

}
