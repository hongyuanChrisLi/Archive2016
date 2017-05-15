package com.infa.objects;

import com.control.MgrtOpts.OBJELEM;
import com.infa.*;


@SuppressWarnings("serial")
public class RequestInfaObj extends InfaObjFactory {

	private final static int FOLDER_IDX = 1;
	private final static int OBJ_IDX = 0;
	
	/*@Override
	public int compareTo(InfaObjFactory o) {
		
		String mystr = folder + name;
		String ostr = o.getFolder() + o.getName();
		return mystr.compareTo(ostr);
	}*/

	@Override
	public void setValue(String line) {
		
		String[] tokens = line.split(" ");
		
		this.put(OBJELEM.FOLDER, tokens[FOLDER_IDX]);
		this.put(OBJELEM.OBJNAME, tokens[OBJ_IDX]);
	}
	
}