package com.file.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class GenericSerializer<T> {
	
	public void serialize ( T obj, String filename) {
		try {
	
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
