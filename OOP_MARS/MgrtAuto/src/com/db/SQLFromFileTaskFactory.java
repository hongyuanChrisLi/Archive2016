package com.db;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.control.*;
import com.file.*;
import com.file.readers.*;


public abstract class SQLFromFileTaskFactory extends SQLTaskFactory{

	protected Path filepath;
	protected String sqlTmpltStr;

	//protected int exitValue = MgrtOpts.EXIT_SUCCESS; 
	private FileReaderFactory reader;

	/*@Override
	public int execute() {
		// TODO Auto-generated method stub
		printStart();
		readSQLTmplt();
		genSQL();
		runSQL();
		processResults();
		printEnd();
		return exitValue;
	}*/
	
	protected abstract void prepConf();
	
	@Override
	protected void preRun (){
		readSQLTmplt ();
		prepConf();
	}
	
	private void readSQLTmplt () {
		
		try {
			reader = new GenericFileReader(filepath);
			sqlTmpltStr = reader.readFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}