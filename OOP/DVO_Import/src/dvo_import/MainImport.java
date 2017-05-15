package dvo_import;

import java.io.*;

public class MainImport {
	
	
	public static void main (String[] argv)
	{
		FileLocator pl = new FileLocator();
		pl.setFolderDir("C:\\Parmfile\\DVO_Profiles");
		File[] profiles = pl.getFileList();
		
		
		pl.setFolderDir("C:\\Parmfile\\DVML");
		File[] dvmls = pl.getFileList();
		
		
		
	}
	
	
}

/*
for ( int i = 0; i < filelst.length; i++ )
{
	System.out.println( "Folder " + filelst[i]);

	String[] tokens = filelst[i].toString().split("_|\\\\");
	System.out.println(tokens[4]);
}*/