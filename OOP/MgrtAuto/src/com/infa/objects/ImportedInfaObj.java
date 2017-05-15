package com.infa.objects;

import com.control.MgrtOpts.OBJELEM;
import com.infa.*;

@SuppressWarnings("serial")
public class ImportedInfaObj extends InfaObjFactory {

	private final static int FOLDER_IDX = 0;
	private final static int OBJ_IDX = 1;
	private final static int TYPE_KEY_IDX = 2;
	private final static int VERS_IDX = 3;
	private final static int REUSE_IDX = 4;
	
	/*@Override
	public int compareTo(InfaObjFactory o) {
		
		String mystr = folder + name;
		String ostr = o.getFolder() + o.getName();
		return mystr.compareTo(ostr);
	}
*/
	@Override
	public void setValue(String line) {
		
		String[] tokens = line.split(",");
		
		this.put(OBJELEM.FOLDER, tokens[FOLDER_IDX]);
		this.put(OBJELEM.OBJNAME, tokens[OBJ_IDX]);
		this.put(OBJELEM.TYPE_KEY, tokens[TYPE_KEY_IDX]);
		this.put(OBJELEM.VERS_NUM, tokens[VERS_IDX]);
		this.put(OBJELEM.REUSE_STAT, tokens[REUSE_IDX]);
		//this.put(OBJELEM.TYPE, "");
	}
	
}