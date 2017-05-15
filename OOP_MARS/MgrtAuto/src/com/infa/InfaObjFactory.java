package com.infa;

import java.util.HashMap;
import java.util.Map;

import com.control.MgrtOpts.OBJELEM;

@SuppressWarnings("serial")
public abstract class InfaObjFactory extends HashMap<OBJELEM, String> {

	public abstract void setValue (String line);
	
	public String getShortID () {
		return  this.get(OBJELEM.FOLDER) + "," + this.get(OBJELEM.OBJNAME);
	}
	
}