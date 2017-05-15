package com.infa.cmd;

import com.infa.*;

public class PMRepCleanupCmd extends PMRepCmdFactory {

	public PMRepCleanupCmd() {
		setAction("cleanup");
	}

	@Override
	protected void buildMap() {
	}

	@Override
	public String getStartNotice() {
		return "";
	}

	@Override
	public String getSuccessNotice() {
		// TODO Auto-generated method stub
		return "Disconnected from repository\n<>-----End of Section-----<>\n";
	}

	@Override
	public String getFailNotice() {
		// TODO Auto-generated method stub
		return "Failed to disconnect from repoistory";
	}

}