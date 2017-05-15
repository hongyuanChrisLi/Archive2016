package com.param.containers;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.control.MgrtOpts.CUSTFILE;
import com.control.MgrtOpts.DIR;
import com.control.MgrtOpts.LOGFILE;
import com.file.FileWriterFactory;
import com.file.util.DirManager;
import com.file.writers.GenericFileWriter;
import com.file.writers.LogWriter;


public class CustFileParmContainer {
	
	private String rootDir;
	private String userDir;
	private String crq;
	private String displayLogDir;
	
	private String userWorkDir;
	private String crqWorkDir;
	private String userLogDir;
	
	private FileWriterFactory logFileWriter;
	private FileWriterFactory displayLogWriter;
	
	public CustFileParmContainer (InitParmContainer initParm) {
		
		this.rootDir = initParm.getRooDir();
		this.userDir = initParm.getMgrtReq().getDBUser();
		this.crq = initParm.getMgrtReq().getCRQ();
		this.displayLogDir = initParm.getMgrtReq().getDisployLog();
		initContainer() ;

	}
	
	public Path getCRQPersisFile() {
		return getWorkFilePath(CUSTFILE.CRQ_PERSIS.getDesc());
	}
	
	public Path getDependPersisFile() {
		return getWorkFilePath(CUSTFILE.DEPEND_PERSIS.getDesc());
	}

	public FileWriterFactory getDisplayLogWriter() {
		return displayLogWriter;
	}

	public Path getDMCtrlFile() {
		return getWorkFilePath(CUSTFILE.DM_CTRL.getDesc());
	}

	public Path getDMExpLog() {
		return getLogFile(LOGFILE.DM_EXPORT_LOG.getDesc());
	}

	public Path getDMImportLog() {
		return getLogFile(LOGFILE.DM_IMPORT_LOG.getDesc());
	}

	public Path getDMInputPersisFile() {
		return getWorkFilePath(CUSTFILE.DM_CRQ_PERSIS.getDesc());
	}

	public Path getDMXMLFile() {
		return getWorkFilePath(CUSTFILE.DM_XML.getDesc());
	}

	public Path getLabelPersisFile() {
		return getWorkFilePath(CUSTFILE.LABEL_PERSIS.getDesc());
	}

	public FileWriterFactory getPmrepLogWriter() {
		return logFileWriter;
	}
	
	public Path getSharedCtrlFile() {
		return getWorkFilePath(CUSTFILE.SHARED_CTRL.getDesc());
	}

	public Path getSharedExpLog() {
		return getLogFile(LOGFILE.SHARED_EXPORT_LOG.getDesc());
	}

	public Path getSharedImportLog() {
		return getLogFile(LOGFILE.DM_IMPORT_LOG.getDesc());
	}

	public Path getSharedInputPersisFile() {
		return getWorkFilePath(CUSTFILE.SHARED_CRQ_PERSIS.getDesc());
	}

	public Path getSharedXMLFile() {
		return getWorkFilePath(CUSTFILE.SHARED_XML.getDesc());
	}

	private Path getLogFile(String name) {
		return Paths.get(userLogDir + name);
	}
	
	private FileWriterFactory getLogFileWriter(String name) {
		System.out.println(userLogDir + name);
		return new GenericFileWriter(userLogDir + name);
	}
	
	private Path getWorkFilePath(String name) {
		System.out.println(crqWorkDir + "/"  + name);
		return Paths.get(crqWorkDir + "/"  + name);
	}

	private void initContainer () {
		
		this.userWorkDir = rootDir + DIR.WORK.getDesc() + userDir ;
		DirManager.createDir(userWorkDir);
		crqWorkDir = DirManager.createDirWithCRQnTS(userWorkDir, crq);
		this.userLogDir = crqWorkDir + DIR.LOG.getDesc();
		DirManager.createDir(userLogDir);
		
		logFileWriter = getLogFileWriter(LOGFILE.PMREP_LOG.getDesc());
		displayLogWriter = new LogWriter(displayLogDir);
		
	}
	
}