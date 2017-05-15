package com.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public abstract class FileWriterFactory {

	private Path filepath;
	private File file;
	protected BufferedWriter bw;
	private final static Charset charset = Charset.forName("ISO-8859-1");

	protected void setFilePath(String dir) {
		this.filepath = Paths.get(dir);
		System.out.println(filepath.toString());
	}

	protected void setFilePath(Path filepath) {
		this.filepath = filepath;
	}
	
	protected void initWriter() {

		file = new File(filepath.toString());

		try {

			if (file.exists()) {
				Files.delete(filepath);
			}

			
			file.createNewFile();
			
			bw = Files.newBufferedWriter(filepath, charset,
					StandardOpenOption.APPEND);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() throws IOException {
		bw.close();
	}
	
	public void flush () {
		try {
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public abstract void append(String line) throws IOException;
	public abstract void appendList(List<String> list) throws IOException;

}