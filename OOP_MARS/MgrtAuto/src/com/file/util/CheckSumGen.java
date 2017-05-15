package com.file.util;

import java.io.*;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckSumGen {
	
	byte[] checkSum;
	
	public CheckSumGen(Path filepath) {
		createCheckSum(filepath.toString());
	}
	
	public CheckSumGen(String file) {
		createCheckSum(file);
	}

	private void createCheckSum(String filename) {
		try {
			InputStream fis = new FileInputStream(filename);
						
			byte[] buffer = new byte[1024];
			MessageDigest msgDig = MessageDigest.getInstance("MD5");
			int numRead;
			
			do {
				numRead = fis.read(buffer);
				if (numRead > 0) {
					msgDig.update(buffer, 0, numRead);
				}
			} while (numRead != -1);

			fis.close();
			checkSum = msgDig.digest();
			
		} catch (NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getCheckSum () {
		
		//System.out.println(Arrays.toString(checkSum));
		
		String res = "";
		
		for (int i = 0; i < checkSum.length; i++ ) {
			//System.out.println("Binary: " + Integer.toBinaryString(checkSum[i]));
			//System.out.println("Hex String: " + Integer.toHexString(checkSum[i]));
			//System.out.println("Hex & 0xff: " + Integer.toString((checkSum[i]),16));
			//System.out.println("Binary & 0xff: " + Integer.toBinaryString(checkSum[i] & 0xff ));
			//System.out.println("Hex + 0x100: " + Integer.toString((checkSum[i] & 0xff ) + 0x100,16));
			//System.out.println("Hex substr: " + Integer.toHexString(checkSum[i]).substring(6));
			//System.out.println("Binary + 0x100: " + Integer.toBinaryString((checkSum[i] & 0xff ) + 0x100));
			//System.out.println("");
			
			
			res += Integer.toString( (checkSum[i] & 0xff ) + 0x100, 16).substring(1);
		}
		
		return res;
		
		//return Arrays.toString(checkSum);
	}

}
