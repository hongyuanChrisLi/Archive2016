package com.file.subtasks;

import java.io.IOException;

import com.control.MgrtOpts;
import com.file.FileWriterFactory;
import com.file.SerFileTaskFactory;
import com.file.readers.IntegSvcFileReader;
import com.file.util.GenericDeserializer;
import com.file.util.GenericSerializer;
import com.infa.util.IntegSvcMap;
import com.param.containers.ParamContainer;

public class EnvSerFileTask extends SerFileTaskFactory {

	private ParamContainer params;
	private FileWriterFactory displayLogWriter;
	//int exitValue;

	public EnvSerFileTask(ParamContainer params) {
		this.params = params;
		this.objSerFile = params.getSysFileParm().getEnvDtlSerFile().toString();
		this.dataFlatFile = params.getSysFileParm().getEnvDtlFile().toString();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	public void printStart() {
		try {
			displayLogWriter
					.append("Checking envrionment file ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void printEnd() {
		try {
			
			if (exitValue == MgrtOpts.SAME_DATA_FILE ) {
				displayLogWriter
				.append("Envrionment details deserialized.");
			} else {
				displayLogWriter
				.append("Envrionment details updated and serialized.");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void genObj() {
		try {
			IntegSvcFileReader isReader = new IntegSvcFileReader(params);
			isReader.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void serializeObj() {
		GenericSerializer<IntegSvcMap> gsMap = new GenericSerializer<IntegSvcMap>();
		gsMap.serialize(params.getRunParm().getIntegSvcMap(), objSerFile);
	}

	@Override
	protected void deserializeObj() {
		GenericDeserializer<IntegSvcMap> gdsMap = new GenericDeserializer<IntegSvcMap>();
		IntegSvcMap map = gdsMap.deserialize(objSerFile);
		params.getRunParm().setIntegSvcMap(map);
	}

	@Override
	protected void getPreTS() {
		preModifiedTS = params.getSerParm().getEnvTS();
	}
	
	@Override
	protected void updPreTS() {
		params.getSerParm().setEnvTS(preModifiedTS);
		System.out.println(preModifiedTS);
		
	}

	
}