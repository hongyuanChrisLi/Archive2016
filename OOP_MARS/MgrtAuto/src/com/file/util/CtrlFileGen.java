package com.file.util;

import java.util.*;

import com.control.MgrtOpts;
import com.control.MgrtOpts.*;
import com.param.containers.ParamContainer;
import com.infa.*;
import com.infa.util.*;

public class CtrlFileGen {

	
	private ParamContainer params;
	private InfaObjList infoObjList;
	private Set<String> folderSet;
	private String originRepo;
	private String destRepo;
	private String impctnl;
	private String requester;
	private Map<MgrtOpts.SECTION, String> sectMap;
	//private Map<String, String> typeMap;

	public CtrlFileGen(ParamContainer params,
			Map<MgrtOpts.SECTION, String> sectMap) {
		
		this.originRepo = params.getOrignInfaParm().getInfaRepo();
		this.destRepo = params.getDestInfaParm().getInfaRepo();
		this.impctnl = params.getInitParm().getImptCtrlStr();
		this.requester = params.getInitParm().getMgrtReq().getRequester();
		this.params = params;
		this.sectMap = sectMap;
		
	}
	
	public void setIsShared(boolean isShared){
		
		if (isShared == MgrtOpts.CTRL_SHARED) {
			this.infoObjList = params.getRunParm().getInfaObjList().getSharedList();
			this.folderSet = params.getRunParm().getDependSrcTgtList().getSharedFolderSet();
		} else {
			this.infoObjList =  params.getRunParm().getInfaObjList().getDMList();
			this.folderSet = params.getRunParm().getInfaObjList().getFolderSet();
			folderSet.addAll(params.getRunParm().getDependSrcTgtList().getFolderSet());
		}
		
	}
	
	public String getCtrlStr() {

		String str = getimportParamsSection();
		// System.out.println(str);
		return str;
	}

	private String getimportParamsSection() {

		String sectStr = sectMap.get(SECTION.IMPORTPARAMS);

		sectStr = sectStr.replaceAll(FILEVAR.CHECKIN.getDesc(),
				STRVAL.CHECKIN.getDesc());
		sectStr = sectStr.replaceAll(FILEVAR.RETAIN.getDesc(),
				STRVAL.RETAIN.getDesc());
		sectStr = sectStr.replaceAll(FILEVAR.COMMENT.getDesc(),
				STRVAL.COMMENT.getDesc() + requester);
		sectStr = sectStr.replaceAll(FILEVAR.IMPORTCTRL.getDesc(), impctnl);
		sectStr = sectStr.replaceAll("\\$\\[FOLDERMAP\\]",
				getFolderMapSection());
		sectStr = sectStr.replaceAll(FILEVAR.CONFLICT.getDesc(),
				getResolveConflictSection());
		return sectStr;
	}

	private String getFolderMapSection() {

		String sectStr = "";
		String tmpltStr = sectMap.get(SECTION.FOLDERMAP);

		for (String folder : folderSet) {
			String folderStr = tmpltStr;
			folderStr = folderStr.replaceAll(FILEVAR.FOLDER.getDesc(), folder);
			folderStr = folderStr.replaceAll(FILEVAR.ORIGINREPO.getDesc(),
					originRepo);
			folderStr = folderStr.replaceAll(FILEVAR.DESTREPO.getDesc(),
					destRepo);

			sectStr += folderStr;
		}

		sectStr = sectStr.replaceAll("\n", "\n\t");

		// System.out.println(sectStr);

		return sectStr;
	}

	private String getResolveConflictSection() {

		String sectStr = sectMap.get(SECTION.RESOLVECONFLICT);
		// String tmpltStr = sectMap.get(MgrtOpts.SECTION.RESOLVECONFLICT);

		sectStr = sectStr.replaceAll(FILEVAR.SPECIFICOBJ.getDesc(),
				getSpecObjSection());

		sectStr = sectStr.replaceAll("\n", "\n\t");

		return sectStr;
	}

	private String getSpecObjSection() {

		String sectStr = "";

		String tmpltStr = sectMap.get(SECTION.SPECIFICOBJECT);
		String srcTmpltStr = sectMap.get(SECTION.SOURCE);

		for (InfaObjFactory infaObj : infoObjList) {

			String objStr = "";
			String type = "";

			// Sub-types of tasks and transformations

			if (infaObj.get(OBJELEM.TYPE) == INFATYPE.TASK.getDesc()
					|| infaObj.get(OBJELEM.TYPE) == INFATYPE.TRANS.getDesc()) {
				type = infaObj.get(OBJELEM.SUBTYPE);
			} else {
				type = infaObj.get(OBJELEM.TYPE);
			}

			// DBD of source definitions

			if (type.equals(INFATYPE.SOURCE.getDesc())) {

				objStr = srcTmpltStr;
				objStr = objStr.replaceAll(FILEVAR.OBJDBD.getDesc(),
						infaObj.get(OBJELEM.DBD));
			} else {
				objStr = tmpltStr;
			}

			// Special type name translation.
			if (MgrtOpts.specialCtrlType.containsKey(type)) {
				type = MgrtOpts.specialCtrlType.get(type);
			} else {
				type = type.substring(0, 1).toUpperCase() + type.substring(1);
			}

			objStr = objStr.replaceAll(FILEVAR.OBJNAME.getDesc(),
					infaObj.get(OBJELEM.OBJNAME));
			objStr = objStr.replaceAll(FILEVAR.OBJTYPE.getDesc(), type);
			objStr = objStr.replaceAll(FILEVAR.OBJFOLDER.getDesc(),
					infaObj.get(OBJELEM.FOLDER));
			objStr = objStr.replaceAll(FILEVAR.OBJREPO.getDesc(), originRepo);

			sectStr += objStr;

		}

		sectStr = sectStr.replaceAll("\n", "\n\t");

		return sectStr;
	}

}