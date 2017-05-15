package com.infa.objects;

import com.control.MgrtOpts.OBJELEM;
import com.infa.*;


@SuppressWarnings("serial")
public class DBBasicInfaObj extends InfaObjFactory {

	private final static int FOLDER_IDX = 0;
	private final static int OBJ_IDX = 1;
	
	@Override
	public void setValue(String line) {
		
		String[] tokens = line.split(",");
		
		this.put(OBJELEM.FOLDER, tokens[FOLDER_IDX]);
		this.put(OBJELEM.OBJNAME, tokens[OBJ_IDX]);
	}
	
}