package com.file.readers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

import com.file.*;

public class GenericFileReader extends FileReaderFactory {

	public GenericFileReader( Path filepath) throws FileNotFoundException {
		setFilePath(filepath);
		initReader();
	}
	
	@Override
	public String readFile() throws IOException {
		
		String fileStr = new String();
		
		String readbuff = br.readLine();
		while (readbuff != null) {
			fileStr += readbuff + "\n";
			readbuff = br.readLine();
		}
		
		closeReader();
		return fileStr;
	}
	
}