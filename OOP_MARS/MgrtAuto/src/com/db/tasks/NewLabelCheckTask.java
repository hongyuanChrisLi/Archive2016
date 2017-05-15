package com.db.tasks;

import java.io.IOException;
import com.db.SQLTaskFactory;
import com.file.FileWriterFactory;
import com.param.containers.ParamContainer;
import com.control.MgrtOpts;

public class NewLabelCheckTask extends SQLTaskFactory {

	private String crq;
	private ParamContainer params;
	private FileWriterFactory displayLogWriter;
	private int resValue;

	// private static final int LABEL_EXISTS = 1;

	public NewLabelCheckTask(ParamContainer params) {
		this.crq = params.getInitParm().getMgrtReq().getCRQ();
		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	public void printStart() {
		try {
			displayLogWriter.append("Checking if Label " + crq + " exists ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void printEnd() {
		try {

			if (resValue == MgrtOpts.NEW_LABEL) {
				displayLogWriter.append("Label " + crq + " is new.");
			} else {
				displayLogWriter.append("Label " + crq + " exists");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void preRun() {
		cnx = params.getDestDBParm().getCnx();
		sqlStr = "SELECT count(LABEL_NAME) RES FROM "
				+ params.getDestDBParm().getDBMetaOwner()
				+ ".REP_LABEL WHERE LABEL_NAME = '" + crq + "'";
		//System.out.println(sqlStr);
	}

	@Override
	protected void postRun() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void processRecord(String res) {
		resValue = Integer.parseInt(res);
		//System.out.println("resValue: " + resValue);
		params.getRunParm().setIsNewLabel(resValue);
		// exitValue = Integer
	}

}