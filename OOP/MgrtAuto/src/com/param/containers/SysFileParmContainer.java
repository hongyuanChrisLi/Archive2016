package com.param.containers;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.control.MgrtOpts.SYSFILE;

public class SysFileParmContainer {

	private String rootDir;
	

	public SysFileParmContainer(InitParmContainer initParm) {
		this.rootDir = initParm.getRooDir();
	}

	public Path getCtrlTmpltFile() {
		return Paths.get(rootDir + SYSFILE.CTRL_FILE_TMPLT.getDesc());
	}

	/*public Path getEnvDtlCheckSumSerFile() {
		return Paths.get(rootDir + SYSFILE.ENV_DTL_CHECKSUM_SER.getDesc());
	}
*/
	public Path getEnvDtlFile() {
		return Paths.get(rootDir + SYSFILE.ENV_DTL_TAB.getDesc());
	}

	public Path getEnvDtlSerFile() {
		return Paths.get(rootDir + SYSFILE.ENV_DTL_SER.getDesc());
	}

	public Path getImpObjSQLFile() {
		return Paths.get(rootDir + SYSFILE.IMPORTED_OBJS_SQL.getDesc());
	}

	public Path getParmSerFile () {
		return Paths.get(rootDir + SYSFILE.PARM_SER.getDesc());
	}
	
	/*public Path getInfaTypeCheckSumSerFile() {
		return Paths.get(rootDir + SYSFILE.INFA_TYPE_CHECKSUM_SER.getDesc());
	}*/

	public Path getInfaTypeFile() {
		return Paths.get(rootDir + SYSFILE.INFA_TYPE_TAB.getDesc());
	}

	public Path getInfaTypeSerFile() {
		return Paths.get(rootDir + SYSFILE.INFA_TYPE_SER.getDesc());
	}

	/*public Path getIntegSvcCheckSumSerFile() {
		return Paths.get(rootDir + SYSFILE.INTEG_SVC_CHECKSUM_SER.getDesc());
	}*/

	public Path getIntegSvcFile() {
		return Paths.get(rootDir + SYSFILE.INTEG_SVC_TAB.getDesc());
	}

	public Path getIntegSvcSerFile() {
		return Paths.get(rootDir + SYSFILE.INTEG_SVC_SER.getDesc());
	}

	public Path getSrcDefSQLFile() {
		return Paths.get(rootDir + SYSFILE.SRC_FIELDS_SQL.getDesc());
	}

	public Path getTgtDefSQLFile() {
		return Paths.get(rootDir + SYSFILE.TGT_FIELDS_SQL.getDesc());
	}

	public Path getVerLabelSQLFile() {
		return Paths.get(rootDir + SYSFILE.VER_LABEL_SQL.getDesc());
	}

}