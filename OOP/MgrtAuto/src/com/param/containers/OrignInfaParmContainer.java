package com.param.containers;

import com.control.requests.MigrationRequest;
import com.param.InfaParmCmpt;

public class OrignInfaParmContainer implements InfaParmCmpt {

	private EnvContainer envs;
	private MigrationRequest mgrtReq;
	
	public OrignInfaParmContainer(MigrationRequest mgrtReq, EnvContainer envs) {

		this.mgrtReq = mgrtReq;
		this.envs = envs;
	}
	
	@Override
	public String getInfaRepo() {
		return envs.get(mgrtReq.getOrignEnv() + "_INFAREPO");
	}

	@Override
	public String getInfaHost() {
		return envs.get(mgrtReq.getOrignEnv() + "_INFAHOST");
	}

	@Override
	public String getInfaPort() {
		return envs.get(mgrtReq.getOrignEnv() + "_INFAPORT");
	}

	@Override
	public String getInfaUsername() {
		return mgrtReq.getOrignInfaUser();
	}

	@Override
	public String getInfaPassword() {
		return mgrtReq.getOrignInfaPasswd();
	}
	
}