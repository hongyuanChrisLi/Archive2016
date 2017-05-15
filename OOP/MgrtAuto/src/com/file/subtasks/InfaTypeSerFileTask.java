package com.file.subtasks;

import java.io.IOException;

import com.control.MgrtOpts;
import com.file.FileWriterFactory;
import com.file.SerFileTaskFactory;
import com.file.readers.InfaTypeFileReader;
import com.file.readers.IntegSvcFileReader;
import com.file.util.GenericDeserializer;
import com.file.util.GenericSerializer;
import com.infa.util.InfaTypeMap;
import com.infa.util.IntegSvcMap;
import com.param.containers.ParamContainer;

public class InfaTypeSerFileTask extends SerFileTaskFactory {

	private ParamContainer params;
	private FileWriterFactory displayLogWriter;

	// int exitValue;

	public InfaTypeSerFileTask(ParamContainer params) {
		this.params = params;
		this.objSerFile = params.getSysFileParm().getInfaTypeSerFile()
				.toString();
		this.dataFlatFile = params.getSysFileParm().getInfaTypeFile()
				.toString();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
	}

	@Override
	public void printStart() {
		try {
			displayLogWriter.append("Checking Infa type file ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void printEnd() {
		try {

			if (exitValue == MgrtOpts.SAME_DATA_FILE) {

				displayLogWriter.append("Infa type details deserialized.");
			} else {
				displayLogWriter
						.append("Infa type details updated and serialized.");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void genObj() {
		try {
			InfaTypeFileReader isReader = new InfaTypeFileReader(params);
			isReader.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void serializeObj() {
		GenericSerializer<InfaTypeMap> gsMap = new GenericSerializer<InfaTypeMap>();
		gsMap.serialize(params.getRunParm().getInfaTypeMap(), objSerFile);
	}

	@Override
	protected void deserializeObj() {
		GenericDeserializer<InfaTypeMap> gdsMap = new GenericDeserializer<InfaTypeMap>();
		InfaTypeMap map = gdsMap.deserialize(objSerFile);
		params.getRunParm().setInfaTypeMap(map);
	}

	@Override
	protected void getPreTS() {
		preModifiedTS = params.getSerParm().getInfaTypeTS();
	}

	@Override
	protected void updPreTS() {
		params.getSerParm().setInfaTypeTS(preModifiedTS);
	}
}