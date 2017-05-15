package com.param.containers;

import java.util.Arrays;
import java.util.Set;

import com.control.requests.MgrtReq;
import com.infa.InfaObjFactory;
import com.infa.objects.RequestInfaObj;
import com.infa.util.InfaObjList;
import com.infa.util.InfaTypeMap;
import com.infa.util.IntegSvcMap;

public class RuntimeParmContainer {
	
	MgrtReq mgrtReq;
	
	private int isNewLabel;
	private boolean isSharedListEmpty;
	private boolean isDMListEmpty;
	
	private InfaObjList infaObjList = null;
	private InfaObjList dependSrcTgtList;
	private InfaObjList complementList;
	private InfaObjList impObjList;
	private IntegSvcMap integSvcMap;
	private InfaTypeMap infaTypeMap;
	private Set<String> newWFSet;


	private String srcListStr;
	private String tgtListStr;

	private Thread mgrtFlowThread;
	
	
	public RuntimeParmContainer (MgrtReq mgrtReq) {
		this.mgrtReq = mgrtReq;
	}
	
	public void setMigrationFlowThread(Thread mgrtFlowThread) {
		this.mgrtFlowThread = mgrtFlowThread;
	}


	public void setIsNewLabel(int isNewLabel) {
		this.isNewLabel = isNewLabel;
	}

	public void setIsSharedListEmpty(boolean isEmpty) {
		this.isSharedListEmpty = isEmpty;
	}

	public void setIsDMListEmpty(boolean isEmpty) {
		this.isDMListEmpty = isEmpty;
	}
	
	public void setInfaObjList(InfaObjList infaObjList) {
		this.infaObjList = infaObjList;
	}

	public void setDependSrcTgtList(InfaObjList dependSrcTgtList) {
		this.dependSrcTgtList = dependSrcTgtList;
	}

	public void setImpObjList(InfaObjList impObjList) {
		this.impObjList = impObjList;
	}

	public void setNewWorkflowSet(Set<String> newWFSet) {
		this.newWFSet = newWFSet;
	}

	public void setIntegSvcMap(IntegSvcMap integSvcMap) {
		this.integSvcMap = integSvcMap;
	}

	public void setInfaTypeMap(InfaTypeMap infaTypeMap) {
		this.infaTypeMap = infaTypeMap;
	}


	public void setSrcListStr(String str) {
		srcListStr = str;
	}

	public void setTgtListStr(String str) {
		tgtListStr = str;
	}
	
	public int getIsNewLabel() {
		return isNewLabel;
	}

	public boolean getIsSharedListEmpty() {
		return isSharedListEmpty;
	}

	public boolean getIsDMListEmpty() {
		return isDMListEmpty;
	}

	
	public Thread getMigrationFlowThread() {
		return mgrtFlowThread;
	}
	
	public InfaObjList getDependSrcTgtList() {
		return dependSrcTgtList;
	}

	public InfaObjList getComplementList() {
		return complementList;
	}

	public InfaObjList getImpObjList() {
		return impObjList;
	}

	public Set<String> getNewWorkflowSet() {
		return newWFSet;
	}

	public IntegSvcMap getIntegSvcMap() {
		return integSvcMap;
	}

	public InfaTypeMap getInfaTypeMap() {
		return infaTypeMap;
	}

	public String getSrcListStr() {
		return srcListStr;
	}

	public String getTgtListStr() {
		return tgtListStr;
	}

	
	public InfaObjList getInfaObjList() {

		if (infaObjList == null) {
			infaObjList = new InfaObjList();

			String folder = new String();
			String object = new String();

			for (String line : mgrtReq.getMigrateList()) {

				// Object_name folder_name fashion
				line = line.replace("\t", " ");
				line = line.replaceAll(" +", " ");
				line = line.trim();
				String[] tokens = line.split(" ");
				System.out.println(Arrays.toString(tokens));

				if (tokens.length == 2 && (!tokens[1].isEmpty())) {
					folder = tokens[1];
				}
				object = tokens[0];

				InfaObjFactory infaObj = new RequestInfaObj();
				infaObj.setValue(object + " " + folder);
				// System.out.println(infaObj.getFolder() + " " +
				// infaObj.getName());
				infaObjList.add(infaObj);

			}

		}

		return infaObjList;

	}
	
}
