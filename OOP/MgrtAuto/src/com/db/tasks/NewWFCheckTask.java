package com.db.tasks;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.control.MgrtOpts;
import com.control.MgrtOpts.*;
import com.db.SQLTaskFactory;
import com.file.FileWriterFactory;
import com.infa.InfaObjFactory;
import com.infa.objects.DBBasicInfaObj;
import com.infa.util.InfaObjList;
import com.infa.util.InfaObjListUtils;
import com.param.containers.ParamContainer;

public class NewWFCheckTask extends SQLTaskFactory {

	private InfaObjList destWFList = new InfaObjList();
	private Set<String> newWFSet = new HashSet<String>();
	private InfaObjList originWFList;
	private ParamContainer params;
	private FileWriterFactory displayLogWriter;

	public NewWFCheckTask(ParamContainer params) {
		this.params = params;
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
		params.getRunParm().setNewWorkflowSet(newWFSet);
	}

	@Override
	public void printStart() {
		try {
			displayLogWriter.append("Identifying new workflows ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void printEnd() {
		try {

			if (exitValue == MgrtOpts.EXIT_SUCCESS) {
				displayLogWriter.append("New workflows are found");
			} else if (exitValue == MgrtOpts.EXIT_SKIP) {
				displayLogWriter.append("No new workflows");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void preRun() {

		cnx = params.getDestDBParm().getCnx();
		originWFList = params.getRunParm().getInfaObjList().getWorkflowList();

		if (!originWFList.isEmpty()) {
			String wfListStr = originWFList.getObjListStr(INFATYPE.WORKFLOW
					.getDesc());
			sqlStr = "SELECT SUBJECT_AREA ||','|| WORKFLOW_NAME RES FROM "
					+ params.getDestDBParm().getDBMetaOwner()
					+ ".REP_WORKFLOWS WHERE SUBJECT_AREA ||','|| WORKFLOW_NAME IN ("
					+ wfListStr + ")";
			//System.out.println(sqlStr);
		} else {
			exitValue = MgrtOpts.EXIT_SKIP;
		}

	}

	@Override
	protected void postRun() {
		// TODO Auto-generated method stub
		InfaObjList newWFList = InfaObjListUtils.subtractInfaObjLists(
				originWFList, destWFList);

		if (newWFList.isEmpty()) {

			exitValue = MgrtOpts.EXIT_SKIP;
		} else {

			for (InfaObjFactory infaObj : newWFList) {
				newWFSet.add(infaObj.getShortID());
				//System.out.println("new Workflow ? : " + infaObj.getShortID());
			}

			exitValue = MgrtOpts.EXIT_SUCCESS;
		}
	}

	@Override
	protected void processRecord(String res) {
		InfaObjFactory infaObj = new DBBasicInfaObj();
		infaObj.setValue(res);

		System.out.println("Workflow Res :" + res);
		destWFList.add(infaObj);
	}

}