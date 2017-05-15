package com.file.readers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import com.file.*;
import com.infa.*;
import com.infa.objects.*;
import com.infa.util.InfaObjList;


public class PersisFileReader extends FileReaderFactory {

	InfaObjList infaObjList; 
	InfaObjFactory infaObj;
	
	public PersisFileReader( InfaObjList infaObjList, Path filepath) throws FileNotFoundException {
		this.infaObjList= infaObjList;
		setFilePath(filepath);
		initReader();
	}

	@Override
	public String readFile() throws IOException {

		String readbuff = br.readLine();
		while (readbuff != null) {
			this.infaObj = new PersisFileInfaObj();
			infaObj.setValue(readbuff);
			infaObjList.add(infaObj);
			readbuff = br.readLine();
			//System.out.println(infaObj.toString());
		}
		
		closeReader();
		return null;
	}

}