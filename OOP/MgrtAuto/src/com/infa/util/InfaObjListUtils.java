package com.infa.util;

import java.util.*;

import com.control.MgrtUtils;
import com.control.MgrtOpts.OBJELEM;
import com.infa.*;

public class InfaObjListUtils {

	private static InfaObjList listA;
	private static InfaObjList listB;
	private static StringInfaObjMap idMapA;
	private static StringInfaObjMap idMapB;
	private static Set<String> setA;
	private static Set<String> setB;
	private static InfaObjList resInfaList;

	
	public static InfaObjList subtractInfaObjLists(InfaObjList infaListA,
			InfaObjList infaListB) {
		// InfaObjMapCompareUtil.mapA = mapA;
		init(infaListA, infaListB);
		setA.removeAll(setB);
		createResInfaList();
		return resInfaList;
	}

	public static InfaObjList intersectInfaObjLists(InfaObjList infaListA,
			InfaObjList infaListB) {
		init(infaListA, infaListB);
		setA.retainAll(setB);
		createResInfaList();
		return resInfaList;
	}

	private static void init(InfaObjList infaListA, InfaObjList infaListB) {
		idMapA = new StringInfaObjMap();
		idMapB = new StringInfaObjMap();
		resInfaList = new InfaObjList();
		listA = infaListA;
		listB = infaListB;
		setA = new HashSet<String>();
		setB = new HashSet<String>();
		buildIDMap(listA, idMapA, setA);
		buildIDMap(listB, idMapB, setB);
		//System.out.println("setA :" );
		//MgrtUtils.printStringSet(setA, "");
		//System.out.println("setB:" );
		//MgrtUtils.printStringSet(setB, "");
	}

	private static void buildIDMap(InfaObjList infaObjList,
			StringInfaObjMap idMap,
			Set<String> idSet) {
		List<InfaObjFactory> infaSubList;
		
		for ( InfaObjFactory infaObj :  infaObjList ){
			String id =  infaObj.getShortID();
			if (idMap.containsKey(id)) {
				idMap.get(id).add(infaObj);
			}else{
				infaSubList = new ArrayList<InfaObjFactory> ();
				infaSubList.add(infaObj);
				idMap.put(id, infaSubList);
				idSet.add(id);
			}
		}
		
	}

	private static void createResInfaList () {
		resInfaList = new InfaObjList();
		
		for (String id : setA) {
			List<InfaObjFactory> infaSubList = idMapA.get(id);
			for ( InfaObjFactory infaObj : infaSubList) {
				resInfaList.add(infaObj);
			}
		}
	}
	
	public static StringInfaObjMap getNoteInfaMap (InfaObjList infaObjList) {
	
		List<InfaObjFactory> infaObjSubList;
		StringInfaObjMap noteInfaMap = new StringInfaObjMap ();
		
		for (InfaObjFactory infaObj : infaObjList ) {
			//Map.Entry<InfaObjFactory, String> pair = it.next();
			String key = infaObj.get(OBJELEM.NOTE);
			
			if ( noteInfaMap.containsKey(key)) {
				noteInfaMap.get(key).add(infaObj);
			}else{
				infaObjSubList = new ArrayList<InfaObjFactory> ();
				infaObjSubList.add(infaObj);
				noteInfaMap.put(key, infaObjSubList);
			}
		}
		return noteInfaMap;
	}
	
	
	/*
	 * private static String createID ( InfaObjFactory infaObj) { return null;
	 * 
	 * }
	 */

	/*private static List<String> getIDMapKeys(Map<String, InfaObjFactory> idMap) {
		return null;
	}*/

	// private static

}