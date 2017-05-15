package com.param.containers;

import com.control.requests.*;

public class ParamContainer{

	private MgrtReq mgrtReq;
	private EnvContainer envs;
	
	private InitParmContainer initParm;
	private CustFileParmContainer custFileParm;
	private SysFileParmContainer sysFileParm;
	
	private DestDBParmContainer destDBParm;
	private OrignDBParmContainer orignDBParm;
	
	private OrignInfaParmContainer orignInfaParm;
	private DestInfaParmContainer destInfaParm;
	
	private SerialParmContainer serParm;
	private RuntimeParmContainer runParm;
	

	public ParamContainer(InitParmContainer initParm) {
		
		this.initParm = initParm;
		this.mgrtReq = initParm.getMgrtReq();
		this.envs = initParm.getEnvParm();	
		
		this.sysFileParm= new SysFileParmContainer(initParm);
		this.custFileParm = new CustFileParmContainer(initParm);
		
		this.orignDBParm = new OrignDBParmContainer(mgrtReq, envs);
		this.destDBParm = new DestDBParmContainer(mgrtReq, envs);
		
		this.orignInfaParm = new OrignInfaParmContainer(mgrtReq, envs);
		this.destInfaParm = new DestInfaParmContainer(mgrtReq, envs);
		
		this.runParm = new RuntimeParmContainer(mgrtReq);
		this.serParm = new SerialParmContainer();
	}
	
	public void setSerParm (SerialParmContainer serParm) {
		this.serParm = serParm;
	}
	
	public CustFileParmContainer getCustFileParm () {
		return custFileParm;
	}
	
	public DestDBParmContainer getDestDBParm () {
		return destDBParm;
	}
	
	public DestInfaParmContainer getDestInfaParm () {
		return destInfaParm;
	}
	
	public InitParmContainer getInitParm () {
		return initParm;
	}
	
	public OrignDBParmContainer getOrignDBParm () {
		return orignDBParm;
	}
	
	public OrignInfaParmContainer getOrignInfaParm () {
		return orignInfaParm;
	}
	
	public RuntimeParmContainer getRunParm () {
		return runParm;
	}
	
	public SerialParmContainer getSerParm () {
		return serParm;
	}

	public SysFileParmContainer getSysFileParm () {
		return sysFileParm;
	}
}