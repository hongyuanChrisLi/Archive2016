package com.file.writers;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.file.*;

public class GenericFileWriter extends FileWriterFactory {

	public GenericFileWriter(Path filepath) throws IOException {
		setFilePath(filepath);
		initWriter();
	}
	
	public GenericFileWriter(String filedir)  {
		setFilePath(Paths.get(filedir));
		initWriter();
	}

	@Override
	public void append(String line) throws IOException {
		bw.write(line, 0, line.length());
		bw.newLine();
	}

	@Override
	public void appendList(List<String> list) {
		// TODO Auto-generated method stub
		
	}

}