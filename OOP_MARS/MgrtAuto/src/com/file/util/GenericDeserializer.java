package com.file.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class GenericDeserializer<T> {
	
	@SuppressWarnings("unchecked")
	public T deserialize( String filename) {
		T obj = null; 
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			obj = (T) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
}