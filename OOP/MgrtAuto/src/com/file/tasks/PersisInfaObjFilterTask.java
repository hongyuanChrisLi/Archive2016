package com.file.tasks;

import java.io.IOException;
import java.util.List;

import com.control.MgrtUtils;
import com.file.*;
import com.file.writers.GenericFileWriter;
import com.infa.*;
import com.param.containers.*;
import com.infa.util.*;

public class PersisInfaObjFilterTask extends PersisFileTaskFactory {

	private InfaObjList myList;
	private InfaObjList outlierList;
	private FileWriterFactory displayLogWriter;
	private ParamContainer params;
	private Thread mgrtFlowThread;
	private boolean isGroup;
	//private Set<InfaObjFactory> outliers;

	private static int SUCCESS = 0;
	private static int FAIL_OL = 1;
	// private static int FAIL_R = 2;
	// private static int FAIL_W = 3;
	private int exitValue = SUCCESS;

	public PersisInfaObjFilterTask(ParamContainer params) throws IOException {

		this.params = params;
		this.myList = params.getRunParm().getInfaObjList();
		this.filepath = params.getCustFileParm().getCRQPersisFile();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
		this.mgrtFlowThread = params.getRunParm().getMigrationFlowThread();
		this.isGroup = params.getInitParm().getMgrtReq().getIsGroup();
	}

	@Override
	public void printStart() {

		try {
			displayLogWriter.append("Filtering Query Persistent file ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void printEnd() {

		try {

			if (exitValue == SUCCESS) {
				displayLogWriter.append("New Persistent file is generated");
			} else if (exitValue == FAIL_OL) {
				displayLogWriter
						.append("The following objects do not exist in the deployment group: ");
				printOutliers();
				mgrtFlowThread.interrupt();
			} else {
				displayLogWriter.append("Failed to filter objects");
				mgrtFlowThread.interrupt();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void writePersisFile() {

		//myMap.updateMap();
		List<String> persisEntries = myList.getPersisEntryList();

		try {
			writer = new GenericFileWriter(filepath);
			for (String entry : persisEntries) {
				writer.append(entry);
				//System.out.println("Persis Entries: ");
				//System.out.println(entry);
			}
			writer.close();

		} catch (IOException e) {
			// exitValue = FAIL_W;
			e.printStackTrace();
		}

	}

	@Override
	protected void runPlan() {
		readPersisFile();

		if (isGroup) {
			//System.out.println("Group migration is turned on");
			params.getRunParm().setInfaObjList(persisObjList);
			//MgrtUtils.printInfaObjList(params.getInfaObjMap().getKeys(), "");
			// System.out.println("runPlan:");
			// MgrtUtils.printInfaObjList(persisObjMap.getKeys());
			// System.out.println(Arrays.toString(params.getInfaObjMap().getKeyStrSet().toArray()));
			// MgrtUtils.printInfaObjList(params.getInfaObjMap().getKeys());
		} else {
			checkOutliers();

			if (exitValue != FAIL_OL) {
				System.out.println("Start to write persis");
				updInfaObjMap ();
				writePersisFile();

			}
		}
	}

	private void updInfaObjMap () {
		
		//System.out.println("The Persis Entries: (Before) ");
		//MgrtUtils.printStringList(persisObjList.getPersisEntryList(), "");
		
		myList= InfaObjListUtils.intersectInfaObjLists(persisObjList, myList);
		params.getRunParm().setInfaObjList(myList);
		//System.out.println("The Persis Entries: (After) ");
		//MgrtUtils.printInfaObjList(params.getInfaObjMap().getKeys(), "");
		//MgrtUtils.printStringList(myList.getPersisEntryList(), "");
	}
	
	private void checkOutliers() {

		outlierList = InfaObjListUtils.subtractInfaObjLists(myList, persisObjList);

		System.out.println("outlierList :");
		MgrtUtils.printInfaObjList(outlierList, "");
		
		if (!outlierList.isEmpty()) {
			exitValue = FAIL_OL;
		}
	}

	private void printOutliers() throws IOException {

		for (InfaObjFactory infaObj : outlierList) {
			displayLogWriter.append("--> " + infaObj.toString());
		}
	}
}