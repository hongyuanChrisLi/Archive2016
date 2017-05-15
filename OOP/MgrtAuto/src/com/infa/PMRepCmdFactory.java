package com.infa;

import java.util.*;
import java.util.Map.Entry;

public abstract class PMRepCmdFactory {

	private String pmaction;
	private Map<String, String> pmopt = new HashMap<String, String>();

	protected void setAction(String pmaction) {
		this.pmaction = pmaction;
	}

	protected abstract void buildMap();

	protected void addOption(String opt, String value) {
		pmopt.put(opt, value);
	}

	public String getCmd() {

		String options = new String();

		Iterator<Entry<String, String>> it = pmopt.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<String, String> pair = it.next();
			options = options.concat(" " + pair.getKey() + " "
					+ pair.getValue());

		}

		return "pmrep " + pmaction + options;
	}
	
	public abstract String getStartNotice();
	
	public abstract String getSuccessNotice();
	
	public abstract String getFailNotice();

}