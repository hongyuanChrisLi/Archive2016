package com.infa.util;

import com.infa.*;
import com.control.MgrtOpts.*;
import com.control.MgrtOpts.OBJELEM;

import java.util.*;

@SuppressWarnings("serial")
public class InfaObjList extends LinkedList<InfaObjFactory> {

	private List<String> shortIDList = new ArrayList<String>();
	private List<String> persisEntryList = new ArrayList<String>();
	private Set<String> folderSet = new HashSet<String>();
	private Set<String> sharedFolderSet = new HashSet<String>();

	/*
	 * |------Shared------|-------DM-------| |--WF--|
	 */

	private static final int startIdx = 0;
	private int sharedEndIdx = 0;
	private int wfEndIdx = 0;

	/*
	 * InfaObjSubList sharedList = new InfaObjSubList(); InfaObjSubList dmList =
	 * new InfaObjSubList(); InfaObjSubList wfList = new InfaObjSubList();
	 */

	@Override
	public boolean add(InfaObjFactory e) {
		synchronized (this) {
			// this.add(e);
			shortIDList.add(e.getShortID());
			persisEntryList.add(e.get(OBJELEM.PERSIS_ENTRY));
			folderSet.add(e.get(OBJELEM.FOLDER));

			if (e.get(OBJELEM.FOLDER).contains("_Shared_")) {
				sharedFolderSet.add(e.get(OBJELEM.FOLDER));
				this.add(startIdx, e);
				sharedEndIdx++;
				wfEndIdx++;

			} else {

				if (e.containsKey(OBJELEM.TYPE) && e.get(OBJELEM.TYPE).equals(INFATYPE.WORKFLOW.getDesc())) {
					this.add(sharedEndIdx, e);
					wfEndIdx++;
				} else {
					this.addLast(e);
				}

			}
		}
		return true;

	}

	private boolean addSub(InfaObjFactory e) {
		super.add(e);
		persisEntryList.add(e.get(OBJELEM.PERSIS_ENTRY));
		return true;
	}

	public List<String> getShortIDList() {
		return shortIDList;
	}

	// @Override
	public List<String> getPersisEntryList() {
		return persisEntryList;
	}

	// @Override
	public Set<String> getFolderSet() {
		return folderSet;
	}

	public Set<String> getSharedFolderSet() {
		return sharedFolderSet;
	}

	public InfaObjList getSharedList() {

		// System.out.println("sharedEndIdx : " + sharedEndIdx);
		if (sharedEndIdx > 0) {
			return getSubInfaObjList(this.subList(startIdx, sharedEndIdx));

		}
		return new InfaObjList();
	}

	public InfaObjList getDMList() {

		// System.out.println("wfEndIdx : " + wfEndIdx);
		if (sharedEndIdx < this.size()) {
			return getSubInfaObjList(this.subList(sharedEndIdx, this.size()));
		}
		return new InfaObjList();
	}

	public InfaObjList getWorkflowList() {

		if (wfEndIdx - sharedEndIdx > 0) {
			return getSubInfaObjList(this.subList(sharedEndIdx, wfEndIdx));
		}

		return new InfaObjList();
	}

	public String getObjListStr(String type) {

		String objListStr = new String();

		for (InfaObjFactory infaObj : this) {

			String infaType = infaObj.get(OBJELEM.TYPE);
			String infaNameLow = infaObj.get(OBJELEM.OBJNAME).toLowerCase();

			if (infaType.equals(type)
					&& !(infaNameLow.startsWith("sc_") || infaNameLow
							.startsWith("shortcut_"))) {
				if (objListStr.isEmpty()) {
					objListStr = "\'" + infaObj.get(OBJELEM.FOLDER) + ","
							+ infaObj.get(OBJELEM.OBJNAME) + "\'";
				} else {
					objListStr += ",\'" + infaObj.get(OBJELEM.FOLDER) + ","
							+ infaObj.get(OBJELEM.OBJNAME) + "\'";
				}

				// System.out.println( "objListStr  " +
				// pair.getKey().toString());
				// );
			}
		}
		return objListStr;
	}

	private InfaObjList getSubInfaObjList(List<InfaObjFactory> sublist) {

		InfaObjList subInfaObjList = new InfaObjList();

		for (InfaObjFactory infaObj : sublist) {
			subInfaObjList.addSub(infaObj);
			//System.out.println("Obj: " + infaObj.getShortID());

		}

		return subInfaObjList;
	}

}
