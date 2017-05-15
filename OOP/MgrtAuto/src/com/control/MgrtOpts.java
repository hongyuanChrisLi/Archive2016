package com.control;

import java.util.*;
import java.util.ResourceBundle.Control;

public final class MgrtOpts {

	public static final int EXIT_SUCCESS = 0;
	public static final int EXIT_FAIL = 1;
	public static final int EXIT_INFO = 2;
	public static final int EXIT_SKIP = 3;

	public static final int VERSION_DELETED = 0;
	public static final int VERSION_CHECKED_IN = 1;
	public static final int VERSION_CHECKED_OUT = 2;
	public static final int LABEL_LATEST = 0;
	public static final int NOT_LABEL_LATEST = 1;

	public static final boolean CTRL_SHARED = true;
	public static final boolean CTRL_DM = false;

	public static final boolean FILE_EXIST = true;
	public static final boolean FILE_NOT_FOUND = false;

	public static final int DIFF_DATA_FILE = 0;
	public static final int SAME_DATA_FILE = 1;
	
	public static final int NEW_LABEL = 0;

	// public static final boolean CTRL_

	public static final Map<Integer, String> verLabelCase;
	static {
		Map<Integer, String> caseMap = new HashMap<Integer, String>();

		caseMap.put(VERSION_DELETED, "Deleted");
		caseMap.put(VERSION_CHECKED_OUT, "Checked out");
		caseMap.put(NOT_LABEL_LATEST, "Latest version not labeled");

		verLabelCase = Collections.unmodifiableMap(caseMap);
	}

	public static final Map<String, String> specialCtrlType;
	static {
		Map<String, String> typeMap = new HashMap<String, String>();

		typeMap.put("source", "Source Definition");
		typeMap.put("target", "Target Definition");
		typeMap.put("event_raise", "Event-raise");
		typeMap.put("event_wait", "Event-wait");
		typeMap.put("lookup_procedure", "Lookup Procedure");

		specialCtrlType = Collections.unmodifiableMap(typeMap);
	}

	public static enum OBJELEM {
		FOLDER,
		DBD,
		OBJNAME,
		TYPE_KEY,
		TYPE,
		SUBTYPE,
		VERS_STAT,
		VERS_NUM,
		LABL_STAT,
		REUSE_STAT,
		PERSIS_ENTRY,
		NOTE
	}
	
/*	public static enum PRMCNTNR {
		REQUEST,
		ORIGN_ENV_INFA_DTL,
		ORIGN_ENV_DB_DTL,
		DEST_ENV_INFA_DTL,
		DEST_ENV_DB_DTL,
		SYSFILE,
		WORKFILE,
		LOG_WRITER,
		OBJECT_LIST,
		REUSE_STAT,
		PERSIS_ENTRY,
		NOTE
	}
	*/
	
	
	public static enum DIR {
		FILES("/files"),
		CONTROL(FILES.getDesc() + "/ctrl/"),
		PARAM(FILES.getDesc() + "/parm/"),
		SQL(FILES.getDesc() + "/sql/"),
		SERIAL(FILES.getDesc() + "/serial/"),
		LOG("/log/"),
		WORK(FILES.getDesc() + "/work/");

		private String desc;

		private DIR(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

	public static enum SYSFILE {

		SRC_FIELDS_SQL(DIR.SQL.getDesc() + "src_fields.sql"),
		TGT_FIELDS_SQL(DIR.SQL.getDesc() + "tgt_fields.sql"),
		VER_LABEL_SQL(DIR.SQL.getDesc() + "version_label.sql"),
		IMPORTED_OBJS_SQL(DIR.SQL.getDesc() + "imported_objs.sql"),
		CTRL_FILE_TMPLT(DIR.CONTROL.getDesc() + "ctrl_file.tmplt"),
		PARM_SER(DIR.SERIAL.getDesc() + "parm.ser"),
		ENV_DTL_TAB(DIR.PARAM.getDesc() + "env_details.tab"),
		ENV_DTL_SER(DIR.SERIAL.getDesc() + "env_dtl.ser"),
		//ENV_DTL_CHECKSUM_SER(DIR.SERIAL.getDesc() + "env_dtl_checksum.ser"),
		INTEG_SVC_TAB(DIR.PARAM.getDesc() + "integration_services.tab"),
		INTEG_SVC_SER(DIR.SERIAL.getDesc() + "integ_svc.ser"),
		//INTEG_SVC_CHECKSUM_SER(DIR.SERIAL.getDesc() + "integ_svc_checksum.ser"),
		INFA_TYPE_TAB(DIR.PARAM.getDesc() + "infa_type.tab"),
		INFA_TYPE_SER(DIR.SERIAL.getDesc() + "infa_type.ser");
		//INFA_TYPE_CHECKSUM_SER(DIR.SERIAL.getDesc() + "infa_type_checksum.ser");

		private String desc;

		private SYSFILE(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

	}
	
	public static enum CUSTFILE {

		CRQ_PERSIS("crq.persis"),
		SHARED_CRQ_PERSIS("shared_crq.persis"),
		DM_CRQ_PERSIS("dm_crq.persis"),
		DEPEND_PERSIS("depend.persis"),
		LABEL_PERSIS("label.persis"),
		SHARED_CTRL("shared.ctrl"),
		DM_CTRL("dm.ctrl"),
		SHARED_XML("shared.xml"),
		DM_XML("dm.xml");
		
		private String desc;

		private CUSTFILE(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

	}
	
	public static enum LOGFILE {
		
		SHARED_EXPORT_LOG("shared_export.log"),
		DM_EXPORT_LOG("dm_export.log"),
		SHARED_IMPORT_LOG("shared_import.log"),
		DM_IMPORT_LOG("dm_import.log"),
		PMREP_LOG("pmrep.log");
		
		private String desc;

		private LOGFILE(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}
	
	
	public static enum SECTION {
		IMPORTPARAMS("IMPORTPARAMS"),
		FOLDERMAP("FOLDERMAP"),
		RESOLVECONFLICT("RESOLVECONFLICT"),
		SPECIFICOBJECT("SPECIFICOBJECT"),
		SOURCE("SOURCE");

		private String desc;

		private SECTION(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

	}

	public static enum STRVAL {
		CHECKIN("YES"),
		RETAIN("YES"),
		COMMENT("Per ");

		private String desc;

		private STRVAL(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

	}

	public static enum INFATYPE {
		SOURCE("source"),
		TARGET("target"),
		TASK("task"),
		TRANS("transformation"),
		WORKFLOW("workflow");

		private String desc;

		private INFATYPE(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}
	}

	public static enum FILEVAR {
		OBJNAME("\\$objName"),
		OBJTYPE("\\$objType"),
		OBJDBD("\\$objDBD"),
		OBJFOLDER("\\$objFolder"),
		OBJREPO("\\$objRepo"),
		FOLDER("\\$folder"),
		ORIGINREPO("\\$originRepo"),
		DESTREPO("\\$destRepo"),
		RETAIN("\\$isRetained"),
		COMMENT("\\$checkinComments"),
		CHECKIN("\\$isCheckin"),
		IMPORTCTRL("\\$importCtrl"),
		FOLDERMAP("\\$\\[FOLDERMAP\\]"),
		CONFLICT("\\$\\[RESOLVECONFLICT\\]"),
		SPECIFICOBJ("\\$\\[SPECIFICOBJECTS\\]");

		private String desc;

		private FILEVAR(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

	}



	
	/*
	 * public static enum LIST_STR { SOURCE("source"), TARGET("target");
	 * 
	 * @SuppressWarnings("unused") private String value; private LIST_STR(String
	 * value) { this.value = value; } }
	 */
	/*
	 * public static enum SET_OPERATION{ REMOVE, RETAIN }
	 */

	// Control File template

}