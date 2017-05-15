package com.db.tasks;

import java.io.IOException;

import com.control.MgrtOpts;
import com.control.MgrtUtils;
import com.db.*;
import com.file.FileWriterFactory;
import com.param.containers.ParamContainer;

public class TgtDefCompareTask extends SQLCompareTaskFactory {

	private ParamContainer params;
	private FileWriterFactory displayLogWriter;
	private Thread mgrtFlowThread;
	private String tgtListStr;

	public TgtDefCompareTask(ParamContainer params) {
		this.params = params;
		this.filepath = params.getSysFileParm().getTgtDefSQLFile();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
		this.mgrtFlowThread = params.getRunParm().getMigrationFlowThread();
		// this.extraInfaObjMap = params.getExtraInfaObjMap();
	}

	@Override
	public void printStart() {
		try {
			displayLogWriter
					.append("Comparing Dependent Target definitions ... ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void printEnd() {
		try {

			if (this.exitValue == MgrtOpts.EXIT_FAIL) {
				displayLogWriter.append("Target Compare Task Failed.");
				mgrtFlowThread.interrupt();
			} else {

				if (this.exitValue == MgrtOpts.EXIT_INFO) {
					// this.printStgStrKeys();
					displayLogWriter.append("Added Target Definitions: ");
					displayLogWriter.appendList(resList);
					//MgrtUtils.printStringList(resList, "\t");
					// System.out.println("Updated InfaObj List after Target: ");
					// MgrtUtils.printInfaObjList(params.getInfaObjList(),
					// "\t");
				} else {
					displayLogWriter.append("No additional Target added.");
				}

				displayLogWriter.append("Target Comparison Completed.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * @Override protected void runSQL() { //String tgtListStr =
	 * params.getTgtListStr();
	 * 
	 * infaObjList = params.getInfaObjList();
	 * 
	 * sqlStrOrigin = sqlTmpltStr.replaceAll("\\$tgtListStr", tgtListStr);
	 * sqlStrOrigin = sqlStrOrigin.replaceAll("\\$metaOwner",
	 * params.getOriginDBMetaOwner()); runSQLInEnv(params.getOriginCnx(),
	 * OriginFields, sqlStrOrigin); //System.out.print(sqlStrOrigin); sqlStrDest
	 * = sqlTmpltStr.replaceAll("\\$tgtListStr", tgtListStr); sqlStrDest =
	 * sqlStrDest.replaceAll("\\$metaOwner", params.getDestDBMetaOwner());
	 * runSQLInEnv(params.getDestCnx(), DestFields, sqlStrDest);
	 * //System.out.print(sqlStrDest); }
	 */

	@Override
	protected void prepConf() {
		tgtListStr = params.getRunParm().getTgtListStr();

		if (tgtListStr.isEmpty()) {
			exitValue = MgrtOpts.EXIT_SKIP;
		} else {
			String sqlStrTmp = sqlTmpltStr.replaceAll("\\$tgtListStr",
					tgtListStr);

			sqlStrOrigin = sqlStrTmp.replaceAll("\\$metaOwner",
					params.getOrignDBParm().getDBMetaOwner());
			cnxOrigin = params.getOrignDBParm().getCnx();

			sqlStrDest = sqlStrTmp.replaceAll("\\$metaOwner",
					params.getDestDBParm().getDBMetaOwner());
			cnxDest = params.getDestDBParm().getCnx();

			infaObjList = params.getRunParm().getInfaObjList();
			dependList = params.getRunParm().getDependSrcTgtList();

		}

	}

	/*
	 * @Override public int execute() { infaObjList = params.getInfaObjList();
	 * dependList = params.getDependSrcTgtList(); tgtListStr =
	 * params.getTgtListStr(); if ( tgtListStr.isEmpty()) { exitValue =
	 * MgrtOpts.EXIT_SKIP; }else{ super.execute(); }
	 * 
	 * return exitValue; }
	 */

}