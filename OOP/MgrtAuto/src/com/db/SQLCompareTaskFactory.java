package com.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.Map.Entry;

import com.control.MgrtOpts;
import com.infa.*;
import com.infa.util.*;
import com.infa.objects.DBBasicInfaObj;

public abstract class SQLCompareTaskFactory extends SQLFromFileTaskFactory {

	protected List<String> OriginFields = new ArrayList<String>();
	protected List<String> DestFields = new ArrayList<String>();
	protected List<String> fields;
	
	protected InfaObjList infaObjList;
	protected InfaObjList dependList;
	protected List<String> resList = new ArrayList<String>();
	
	protected String sqlStrOrigin;
	protected String sqlStrDest;
	
	protected Connection cnxOrigin;
	protected Connection cnxDest;
	
	
	@Override
	protected void runSQL()  {
		cnx = cnxOrigin;
		sqlStr = sqlStrOrigin;
		fields = OriginFields;
		//System.out.println(sqlStr);
		super.runSQL();
		
		cnx = cnxDest;
		sqlStr = sqlStrDest;
		fields = DestFields;
		super.runSQL();
	}
	

	@Override
	protected void processRecord(String record) {
		fields.add(record);
	}
	
	@Override
	protected void postRun() {
		Set<String> originSet = new HashSet<String>(OriginFields);
		Set<String> destSet = new HashSet<String>(DestFields);
		
		originSet.removeAll(destSet);
		
		Set<String> stgStrSet = new HashSet<String>();
		InfaObjList stgInfaList = new InfaObjList();

		if (!originSet.isEmpty()) {

			this.exitValue = MgrtOpts.EXIT_INFO;
			
			for (String str : originSet) {
				int endIndex = str.indexOf(',', str.indexOf(',') + 1);
				stgStrSet.add(str.substring(0, endIndex));
			}

			for (String str : stgStrSet) {

				InfaObjFactory infaObj = new DBBasicInfaObj();
				//System.out.println(pair.getKey());
				infaObj.setValue(str);
				stgInfaList.add(infaObj);
				resList.add(infaObj.getShortID());
			}

			stgInfaList = InfaObjListUtils.intersectInfaObjLists(dependList, stgInfaList);
			infaObjList.addAll(stgInfaList);
		}

	}
	
}