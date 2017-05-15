package com.infa.util;

import java.io.Serializable;
import java.util.*;

public class IntegSvcMap extends HashMap<String, String[]> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5366802606784848305L;
	//private Map<String, String[]> integSvcMap = new HashMap<String, String[]>();
	private Map<String, Integer> envsMap = new HashMap<String, Integer>();
	private boolean isHeaderLine = true;

	private final static int ENVS_IDX = 1;
	private final static int FOLDER_IDX = 0;
	private final static int SVCS_IDX = 1;

	public void put(String line) {

		if (isHeaderLine) {
			processHeaderLine(line);
		} else {
			processDataLine(line);
		}

	}
	
	public String get (String folder, String env) {
		return this.get(folder)[envsMap.get(env)];
	}
	
	public boolean containsKeys (String folder, String env) {
		
		boolean containsFolderKey = this.containsKey(folder);
		boolean containsEnvKey = envsMap.containsKey(env);
		
		return containsFolderKey && containsEnvKey;
	}
	
	private void processHeaderLine(String line) {

		String[] parts = line.split(":");
		String[] envs = parts[ENVS_IDX].split(",");

		for (int i = 0; i < envs.length; i++) {
			envsMap.put(envs[i], i);
		}

		isHeaderLine = false;
	}

	private void processDataLine(String line) {
		String[] parts = line.split(":");
		String[] svcs = parts[SVCS_IDX].split(",");
		this.put(parts[FOLDER_IDX], svcs);
	}
}
