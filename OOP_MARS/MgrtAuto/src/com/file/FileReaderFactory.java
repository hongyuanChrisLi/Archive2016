package com.file;

import java.io.*;
import java.nio.file.*;

public abstract class FileReaderFactory {

	private Path filepath;
	protected FileReader fr;
	protected BufferedReader br;

	protected void setFilePath(String dir) {
		this.filepath = Paths.get("./" + dir);
		System.out.println(filepath.toString());
	}

	protected void setFilePath(Path filepath) {
		this.filepath = filepath;
	}
	
	protected void initReader() throws FileNotFoundException {
		fr = new FileReader(filepath.toString());
		br = new BufferedReader(fr);
	}

	public abstract String readFile() throws IOException;

	protected void closeReader () throws IOException {
		br.close();
	}
}
