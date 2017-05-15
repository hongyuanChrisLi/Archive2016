package test;

import java.io.IOException;
import java.nio.file.*;

import com.file.*;
import com.file.readers.*;

public class CtrlTest {
	
	public static void main (String[] args ) throws IOException{
		
		Path  ctrlPath = Paths.get("./files/ctrl/ctrl_file_template.txt");
		FileReaderFactory fr = new GenericFileReader(ctrlPath);
		String tmpltStr = fr.readFile();
		
		String startLabel = "{#IMPORTPARAMS}";
		String endLabel = "{#/IMPORTPARAMS}";
	
		
		int startIdx =  tmpltStr.indexOf(startLabel);
		int endIdx = tmpltStr.indexOf(endLabel);
		String subTmpltStr = tmpltStr.substring(startIdx + startLabel.length(), endIdx);
		String subStr = subTmpltStr.replaceAll("\\$isCheckin", "YES");
		
		System.out.println(subStr);
	}
	
}