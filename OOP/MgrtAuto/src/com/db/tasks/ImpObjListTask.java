package com.db.tasks;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.db.*;
import com.file.FileWriterFactory;
import com.file.writers.GenericFileWriter;
import com.infa.InfaObjFactory;
import com.infa.objects.ImportedInfaObj;
import com.infa.util.InfaObjList;
import com.infa.util.InfaTypeMap;
import com.param.containers.ParamContainer;
import com.control.MgrtOpts.OBJELEM;

public class ImpObjListTask extends SQLFromFileTaskFactory {

	private FileWriterFactory displayLogWriter;
	private InfaObjList impObjList = new InfaObjList();
	private ParamContainer params;
	private InfaTypeMap infaTypeMap;
	private Path labelPersisFile;
	private Thread mgrtFlowThread;
	
	// private final static String ALWAYS_REUSE = "2";
	private final static String REUSABLE = "1";

	public ImpObjListTask(ParamContainer params) {
		this.filepath = params.getSysFileParm().getImpObjSQLFile();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
		this.labelPersisFile = params.getCustFileParm().getLabelPersisFile();
		this.params = params;
		params.getRunParm().setImpObjList(this.impObjList);
	}

	@Override
	public void printStart() {
		try {
			displayLogWriter
					.append("Querying imported objects and generate simplified persistent file ... ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void printEnd() {
		try {
			displayLogWriter
					.append("Simplified persistent file created ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	protected void postRun() {
		List<String> persisEntries = impObjList.getPersisEntryList();
		try {
			GenericFileWriter writer = new GenericFileWriter(labelPersisFile);
			for (String entry : persisEntries) {
				writer.append(entry);
			}
			writer.close();

		} catch (IOException e) {
			// exitValue = FAIL_W;
			e.printStackTrace();
		}

	}

	@Override
	protected void prepConf() {

		//System.out.println("Admin User " + params.getAdminUser());
		//System.out.println("UTC " + params.getStartUTCStr());

		sqlStr = sqlTmpltStr.replaceAll("\\$adminUser", params.getInitParm().getMgrtReq().getAdminUser());
		sqlStr = sqlStr.replaceAll("\\$startUTCStr", params.getInitParm().getStartUTCStr());
		sqlStr = sqlStr.replaceAll("\\$metaOwner",
				params.getOrignDBParm().getDBMetaOwner());
		cnx = params.getDestDBParm().getCnx();
		infaTypeMap = params.getRunParm().getInfaTypeMap();
		
		//System.out.println("SqlStr \n" + sqlStr);
		// this.infaTypeMap =
	}

	@Override
	protected void processRecord(String record) {
		InfaObjFactory infaObj = new ImportedInfaObj();
		infaObj.setValue(record);

		String comboType = infaTypeMap.get(Integer.parseInt(infaObj.get(OBJELEM.TYPE_KEY)));
		String pesisEntry = "none," + infaObj.get(OBJELEM.FOLDER) + ","
				+ infaObj.get(OBJELEM.OBJNAME) + "," + comboType + ","
				+ infaObj.get(OBJELEM.VERS_NUM);

		if (infaObj.get(OBJELEM.REUSE_STAT).equals(REUSABLE)) {
			pesisEntry = pesisEntry + ",reusable";
		}

		//System.out.println(pesisEntry);

		infaObj.put(OBJELEM.PERSIS_ENTRY, pesisEntry);
		impObjList.add(infaObj);
	}
}