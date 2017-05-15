package com.file.writers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.file.*;

public class LogWriter extends FileWriterFactory {

	public LogWriter(String logdir) {
		setFilePath(logdir);
		initWriter();
	}

	@Override
	public void append(String line)  {
		synchronized (this) {
			
			String timeStr =  new SimpleDateFormat("HH:mm:ss").format(new Date());
			line = timeStr + " - " + line;
			try {
				bw.write(line, 0, line.length());
				bw.flush();
				bw.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void appendList(List<String> list)  {
		
		String str = "";
		for ( String item : list ) {
			str = str + item + "\n";
		}
		try {
			bw.write(str, 0, str.length());
			bw.flush();
			bw.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}