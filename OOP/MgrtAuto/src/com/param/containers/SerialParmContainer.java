package com.param.containers;

import java.io.Serializable;


public class SerialParmContainer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7170372678796365039L;
	private long integSvcTS;
	private long infaTypeTS;
	private long envTS;
	
	public void setIntegSvcTS (long ts) {
		this.integSvcTS = ts;
	}
	
	public void setInfaTypeTS (long ts) {
		this.infaTypeTS = ts;
	}
	
	public void setEnvTS (long ts) {
		this.envTS = ts;
	}
	
	public long getIntegSvcTS () {
		return integSvcTS;
	}
	
	public long getInfaTypeTS ()  {
		return infaTypeTS;
	}
	
	public long getEnvTS() {
		return envTS;
	}
	
}