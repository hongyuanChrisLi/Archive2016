package com.param.containers;

import com.control.requests.MgrtReq;
import com.param.InfaParmCmpt;

public class DestInfaParmContainer implements InfaParmCmpt {

	private EnvContainer envs;
	private MgrtReq mgrtReq;
	
	public DestInfaParmContainer(MgrtReq mgrtReq, EnvContainer envs) {

		this.mgrtReq = mgrtReq;
		this.envs = envs;
	}

	@Override
	public String getInfaRepo() {
		return envs.get(mgrtReq.getDestEnv() + "_INFAREPO");
	}

	@Override
	public String getInfaHost() {
		return envs.get(mgrtReq.getDestEnv() + "_INFAHOST");
	}

	@Override
	public String getInfaPort() {
		return envs.get(mgrtReq.getDestEnv() + "_INFAPORT");
	}

	@Override
	public String getInfaUsername() {
		return mgrtReq.getDestInfaUser();
	}

	@Override
	public String getInfaPassword() {
		return mgrtReq.getDestInfaPasswd();
	}

}