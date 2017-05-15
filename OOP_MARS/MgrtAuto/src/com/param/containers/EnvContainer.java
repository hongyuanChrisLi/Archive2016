package com.param.containers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class EnvContainer extends HashMap<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3531164617586310159L;
	private final Set<String> keySufSet = new HashSet<String>();

	// private Map<String, String> envparam = new HashMap<String, String>();

	public EnvContainer() {

		initSuffixPool();
	}

	private void initSuffixPool() {

		this.keySufSet.add("_INFAREPO");
		this.keySufSet.add("_INFAHOST");
		this.keySufSet.add("_INFAPORT");
		this.keySufSet.add("_INFAUSER");
		this.keySufSet.add("_INFAPASS");
		this.keySufSet.add("_DBHOST");
		this.keySufSet.add("_DBPORT");
		this.keySufSet.add("_DBSERVICE");
		this.keySufSet.add("_DBUSER");
		this.keySufSet.add("_DBPASS");
		this.keySufSet.add("_DBMETA");

	}

	@Override
	public String put(String key, String value) {

		String sfx = key.substring(key.lastIndexOf('_'));

		if (keySufSet.contains(sfx)) {
			super.put(key, value);
		} else {
			System.out.println(sfx + " is an invalid key suffix!");
		}

		return null;
	}

}