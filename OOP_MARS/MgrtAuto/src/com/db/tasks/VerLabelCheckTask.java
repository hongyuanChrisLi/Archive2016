package com.db.tasks;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.db.*;
import com.infa.*;
import com.infa.util.*;
import com.infa.objects.*;
import com.infa.util.StringInfaObjMap;
import com.file.FileWriterFactory;
import com.param.containers.*;
import com.control.*;
import com.control.MgrtOpts.OBJELEM;

public class VerLabelCheckTask extends SQLFromFileTaskFactory {

	private FileWriterFactory displayLogWriter;
	private InfaObjList verLabelList;
	private List<String> verLablStrList;
	private String crq;
	private Thread mgrtFlowThread;
	private ParamContainer params;

	// private int extiValue = MgrtOpts.EXIT_SUCCESS;

	/*
	 * private static final int VERSION_DELETED = 0; private static final int
	 * VERSION_CHECKED_IN = 1; private static final int VERSION_CHECKED_OUT = 2;
	 * private static final int LABEL_LATEST = 0;
	 */

	public VerLabelCheckTask(ParamContainer params) {
		this.params = params;
		this.filepath = params.getSysFileParm().getVerLabelSQLFile();
		this.crq = params.getInitParm().getMgrtReq().getCRQ();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
		this.verLabelList = new InfaObjList();
		this.mgrtFlowThread = params.getRunParm().getMigrationFlowThread();

		/*
		 * try { this.stmt = cnx.createStatement(); } catch (SQLException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
	}

	@Override
	public void printStart() {
		try {
			displayLogWriter.append("Checking version and label ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void printEnd() {
		try {

			if (exitValue == MgrtOpts.EXIT_SUCCESS) {
				displayLogWriter.append("version and label check passed");
			} else {
				displayLogWriter.append("Objects with issues: ");
				displayLogWriter.appendList(verLablStrList);
				/*StringInfaObjMap noteInfaMap = InfaObjListUtils
						.getNoteInfaMap(verLabelList);
				noteInfaMap.print();*/
				mgrtFlowThread.interrupt();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void prepConf() {
		sqlStr = sqlTmpltStr.replaceAll("\\$crq", crq);
		sqlStr = sqlStr.replaceAll("\\$metaOwner",
				params.getOrignDBParm().getDBMetaOwner());
		cnx = params.getOrignDBParm().getCnx();
	}

	@Override
	protected void processRecord(String record) {

		DBVerLabelInfaObj dbInfaObj = new DBVerLabelInfaObj();
		dbInfaObj.setValue(record);

		switch (Integer.parseInt(dbInfaObj.get(OBJELEM.VERS_STAT))) {
		case MgrtOpts.VERSION_CHECKED_OUT:
			dbInfaObj.put(OBJELEM.NOTE,
					MgrtOpts.verLabelCase.get(MgrtOpts.VERSION_CHECKED_OUT));
			verLabelList.add(dbInfaObj);
			break;
		case MgrtOpts.VERSION_DELETED:
			dbInfaObj.put(OBJELEM.NOTE,
					MgrtOpts.verLabelCase.get(MgrtOpts.VERSION_DELETED));
			verLabelList.add(dbInfaObj);
			break;
		case MgrtOpts.VERSION_CHECKED_IN:
			if (Integer.parseInt(dbInfaObj.get(OBJELEM.LABL_STAT)) != MgrtOpts.LABEL_LATEST) {
				dbInfaObj.put(OBJELEM.NOTE,
						MgrtOpts.verLabelCase.get(MgrtOpts.NOT_LABEL_LATEST));
				verLabelList.add(dbInfaObj);
			}
			break;
		default:
			break;
		}

	}

	@Override
	protected void postRun() {

		verLabelList = InfaObjListUtils.intersectInfaObjLists(verLabelList,
				params.getRunParm().getInfaObjList());

		if (!verLabelList.isEmpty()) {
			exitValue = MgrtOpts.EXIT_FAIL;
			//System.out.println("The keyset is not empty");
			getVerLablStrList();
		}
	}
	
	private void getVerLablStrList () {
		
		verLablStrList = new ArrayList<String> ();
		
		for ( InfaObjFactory infaObj : verLabelList) {
			verLablStrList.add(infaObj.getShortID() + " => " + infaObj.get(MgrtOpts.OBJELEM.NOTE) + " !");
		}
	}

}