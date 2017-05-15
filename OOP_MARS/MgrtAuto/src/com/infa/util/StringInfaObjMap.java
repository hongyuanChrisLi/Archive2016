package com.infa.util;

import java.util.*;

import com.control.MgrtUtils;
import com.infa.InfaObjFactory;

@SuppressWarnings("serial")
public class StringInfaObjMap extends HashMap<String, List<InfaObjFactory>> {

	public void print() {

		Iterator<Map.Entry<String, List<InfaObjFactory>>> it = this.entrySet()
				.iterator();
		
		while (it.hasNext()) {
			Map.Entry<String, List<InfaObjFactory>> pair = it.next();
			System.out.println(pair.getKey());
			MgrtUtils.printInfaObjList(pair.getValue(), "\t");
			
		}

	}

}