package com.file.readers;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.file.*;
import com.infa.util.*;
import com.param.containers.*;


public class InfaTypeFileReader extends FileReaderFactory {

	InfaTypeMap infaTypeMap; 
	//ParamContainer params;
	
	public InfaTypeFileReader( ParamContainer params ) throws FileNotFoundException {
		this.infaTypeMap = new InfaTypeMap();
		params.getRunParm().setInfaTypeMap(infaTypeMap);;
		setFilePath(params.getSysFileParm().getInfaTypeFile());
		initReader();
	}

	@Override
	public String readFile() throws IOException {

		String readbuff = br.readLine();
		while (readbuff != null) {
			infaTypeMap.put(readbuff);
			readbuff = br.readLine();
			//System.out.println(infaObj.toString());
		}
		
		closeReader();
		return null;
	}
	
}