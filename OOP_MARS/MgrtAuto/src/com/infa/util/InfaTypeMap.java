package com.infa.util;

import java.util.HashMap;

public class InfaTypeMap extends HashMap<Integer, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8042575152241398825L;
	
	public void put(String line) {
		String[] tokens = line.split(":");
		Integer typeKey = Integer.parseInt(tokens[0]);
		this.put(typeKey, tokens[1]);
	}
}