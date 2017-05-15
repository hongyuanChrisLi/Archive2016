package com.file.tasks;

import java.io.File;

import com.control.MigrationTask;
import com.file.subtasks.*;
import com.file.util.GenericDeserializer;
import com.file.util.GenericSerializer;
import com.infa.util.IntegSvcMap;
import com.param.containers.ParamContainer;
import com.param.containers.SerialParmContainer;

public class SerFileTask implements MigrationTask {

	ParamContainer params;
	private String serFileStr;
	
	public SerFileTask(ParamContainer params) {
		this.params = params;
		this.serFileStr = params.getSysFileParm().getParmSerFile().toString();
	}

	@Override
	public int execute() {

		File serFile = new File (serFileStr);
		
		if ( serFile.exists() && serFile.isFile() ) {
			deserializeParm();
		}
		
		MigrationTask taskIntegSvcSerFile = new IntegSvcSerFileTask(params);
		taskIntegSvcSerFile.execute();
		
		MigrationTask taskInfaTypeSerFile = new InfaTypeSerFileTask(params);
		taskInfaTypeSerFile.execute();
		
		MigrationTask taskEnvSerFile = new EnvSerFileTask(params);
		taskEnvSerFile.execute();
		
		serializeParm ();
		
		return 0;
	}

	@Override
	public void run() {
		execute();

	}

	@Override
	public void printStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printEnd() {
		// TODO Auto-generated method stub

	}
	
	private void serializeParm () {
		GenericSerializer<SerialParmContainer> gsParm = new GenericSerializer<SerialParmContainer>();
		gsParm.serialize(params.getSerParm(), serFileStr);
		//System.out.println("after: " + params.getSerParm().getEnvTS());
	}
	
	private void deserializeParm () {
		GenericDeserializer<SerialParmContainer> gdsParm = new GenericDeserializer<SerialParmContainer>();
		params.setSerParm(gdsParm.deserialize(serFileStr));
		//System.out.println("before: " + params.getSerParm().getEnvTS());
	}

}