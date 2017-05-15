package com.file.tasks;

import java.io.IOException;

import com.control.MgrtUtils;
import com.file.FileWriterFactory;
import com.file.PersisFileTaskFactory;
import com.param.containers.*;
import com.infa.objects.*;
import com.infa.util.*;
import com.infa.*;

public class DependAnalysisTask extends PersisFileTaskFactory {

	// private InfaObjMap myMap;
	private FileWriterFactory displayLogWriter;
	private ParamContainer params;
	// private InfaObjMapFactory stgInfaObjMap = new InfaObjMapPersis ();
	private InfaObjList complementMap;

	public DependAnalysisTask(ParamContainer params) {

		this.filepath = params.getCustFileParm().getDependPersisFile();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
		this.params = params;
	}

	@Override
	public void printStart() {
		try {
			displayLogWriter.append("Analyzing Dependencies ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void printEnd() {
		try {
			displayLogWriter
					.append("Dependent Source and Target Lists extracted ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void writePersisFile() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void runPlan() {
		// TODO Auto-generated method stub
		readPersisFile();
		setDependInfaObjList();
		getComplementList();
		setSrcTgtStrLists();
		//setTgtListStr();

	}

	private void getComplementList() {

		complementMap = InfaObjListUtils.subtractInfaObjLists(
				params.getRunParm().getDependSrcTgtList(), params.getRunParm().getInfaObjList());
		//System.out.println("Complement map: ");
		//MgrtUtils.printInfaObjList(complementMap.getKeys(), "\t");

	}

	private void setSrcTgtStrLists() {
		params.getRunParm().setSrcListStr(complementMap.getObjListStr("source"));
		System.out.println("source: " + params.getRunParm().getSrcListStr());
		
		params.getRunParm().setTgtListStr(complementMap.getObjListStr("target"));
		System.out.println("target: " + params.getRunParm().getTgtListStr());
		
		
		
	}

	/*private void setTgtListStr() {
		params.setTgtListStr(complementMap.getObjListStr("target"));
		System.out.println("target: " + params.getTgtListStr());
		// System.out.println( "Target: " +
		// persisObjMap.getObjListStr("target"));
	}*/

	private void setDependInfaObjList() {
		params.getRunParm().setDependSrcTgtList(persisObjList);
		//params.setFolderSet(persisObjList.getFolderSet());
	}

}