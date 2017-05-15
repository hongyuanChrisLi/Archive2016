package com.infa.objects;

import com.control.MgrtOpts.OBJELEM;
import com.infa.*;

/*
 * Line Example: 
 * 
 * 3498280415:f33ff269-dc52-48fc-955a-a551d11098e1:11665408321985:139329536013794:4456448185375:
 * 262144198635:65536193945:0112455:0165435:65536249705,IDA_DM,s_MV90_USG_EXCLUDE_PRE_MERGE_Load,
 * session,none,4,reusable
 * 
 */

@SuppressWarnings("serial")
public class PersisFileInfaObj extends InfaObjFactory {

	private final static int FOLDER_IDX = 10;
	private final static int OBJ_IDX = 11;
	private final static int TYPE_IDX = 12;
	private final static int SUBTYPE_IDX = 13;
	private final static int DBD_IDX = 0;
	private final static int SOURCE_IDX = 1;

	@Override
	public void setValue(String line) {

		this.put(OBJELEM.PERSIS_ENTRY, line);
		
		line = line.replace(":", ",");
		String[] tokens = line.split(",");
		
		this.put(OBJELEM.FOLDER, tokens[FOLDER_IDX]);
		this.put(OBJELEM.TYPE, tokens[TYPE_IDX]);
		this.put(OBJELEM.SUBTYPE, tokens[SUBTYPE_IDX]);

		
		if (tokens[OBJ_IDX].contains(".")){
			//System.out.println(name);
			String[] names = tokens[OBJ_IDX].split("\\.");
			//System.out.println(Arrays.toString(names));
			
			this.put(OBJELEM.DBD, names[DBD_IDX]);
			this.put(OBJELEM.OBJNAME, names[SOURCE_IDX]);
		} else {
			this.put(OBJELEM.OBJNAME, tokens[OBJ_IDX]);
		}
		
	}
	
}