package com.infa.objects;

import com.control.MgrtOpts.OBJELEM;
import com.infa.*;


@SuppressWarnings("serial")
public class DBVerLabelInfaObj extends InfaObjFactory {

	private final static int FOLDER_IDX = 0;
	private final static int OBJ_IDX = 1;
	private final static int TYPE_IDX = 2;
	private final static int VERSION_IDX = 3;
	private final static int LABEL_IDX = 4;
	
	
	/*@Override
	public int compareTo(InfaObjFactory o) {
		
		String mystr = folder + name + type;
		String ostr = o.getFolder() + o.getName() + o.getType();
		return mystr.compareTo(ostr);
	}*/

	@Override
	public void setValue(String line) {
		
		String[] tokens = line.split(",");
		//System.out.println(Arrays.toString(tokens));
		
		this.put(OBJELEM.FOLDER, tokens[FOLDER_IDX]);
		this.put(OBJELEM.OBJNAME, tokens[OBJ_IDX]);
		this.put(OBJELEM.TYPE, tokens[TYPE_IDX]);
		this.put(OBJELEM.VERS_STAT, tokens[VERSION_IDX]);
		this.put(OBJELEM.LABL_STAT, tokens[LABEL_IDX]);
	}
	
}