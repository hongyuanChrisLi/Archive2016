package com.file.readers;

import java.io.FileNotFoundException;
import java.io.IOException;


import com.file.*;
import com.infa.util.*;
import com.param.containers.*;


public class IntegSvcFileReader extends FileReaderFactory {

	IntegSvcMap integSvcMap; 
	//ParamContainer params;
	
	public IntegSvcFileReader( ParamContainer params ) throws FileNotFoundException {
		this.integSvcMap = new IntegSvcMap();
		params.getRunParm().setIntegSvcMap(integSvcMap);
		setFilePath(params.getSysFileParm().getIntegSvcFile());
		initReader();
	}

	@Override
	public String readFile() throws IOException {

		String readbuff = br.readLine();
		while (readbuff != null) {
			integSvcMap.put(readbuff);
			readbuff = br.readLine();
			//System.out.println(infaObj.toString());
		}
		
		closeReader();
		return null;
	}
	
}