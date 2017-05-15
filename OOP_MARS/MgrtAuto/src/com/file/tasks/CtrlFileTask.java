package com.file.tasks;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import com.control.*;
import com.param.containers.ParamContainer;
import com.file.util.*;
import com.file.writers.*;
import com.file.*;

public class CtrlFileTask implements MigrationTask {

	private Map<MgrtOpts.SECTION, String> sectMap;
	private String sharedCtrlStr;
	private String dmCtrlStr;
	
	private Path tmpltpath;
	private Path sharedCtrlPath;
	private Path dmCtrlPath;
	private ParamContainer params;
	private FileWriterFactory displayLogWriter;
	private int exitValue = MgrtOpts.EXIT_SUCCESS;
	
	public CtrlFileTask (ParamContainer params) {
		this.params = params;
		this.tmpltpath = params.getSysFileParm().getCtrlTmpltFile();
		this.sharedCtrlPath = params.getCustFileParm().getSharedCtrlFile();
		this.dmCtrlPath = params.getCustFileParm().getDMCtrlFile();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}
	
	@Override
	public void printStart() {
		try {
			displayLogWriter.append("Generating Import Control file ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void printEnd() {
		try {

			if (exitValue ==  MgrtOpts.EXIT_SUCCESS) {
				displayLogWriter.append("Import Control file is generated");
			} else {
				displayLogWriter
						.append("Failed to generate Import Control file ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int execute() {
		// TODO Auto-generated method stub
		printStart();
		parseTmplt();
		genCtrlStr();
		writeCtrlFile();
		printEnd();
		return 0;
	}
	
	private void parseTmplt () {
		CtrlTmpltParser ctParser = new CtrlTmpltParser(tmpltpath);
		sectMap = ctParser.getSectMap();
	}
	
	private void genCtrlStr () {
		CtrlFileGen cfGen = new CtrlFileGen(params, sectMap);
		
		if (params.getRunParm().getInfaObjList().getSharedList().isEmpty()) {
			params.getRunParm().setIsSharedListEmpty(true);
		} else {
			params.getRunParm().setIsSharedListEmpty(false);
			cfGen.setIsShared(MgrtOpts.CTRL_SHARED);
			sharedCtrlStr = cfGen.getCtrlStr();
		}
		
		if (params.getRunParm().getInfaObjList().getDMList().isEmpty()) {
			params.getRunParm().setIsDMListEmpty(true);
		} else {
			params.getRunParm().setIsDMListEmpty(false);
			cfGen.setIsShared(MgrtOpts.CTRL_DM);
			dmCtrlStr = cfGen.getCtrlStr();
		}
	}
	
	private void writeCtrlFile() {
		try {
			FileWriterFactory writer = null; 
			
			if ( ! params.getRunParm().getIsSharedListEmpty()) {
				writer = new GenericFileWriter (sharedCtrlPath) ;
				writer.append(sharedCtrlStr);
				writer.close();
			}
			
			if ( ! params.getRunParm().getIsDMListEmpty()) {
				writer = new GenericFileWriter (dmCtrlPath);
				writer.append(dmCtrlStr);
				writer.close();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		execute();
	}
	
}