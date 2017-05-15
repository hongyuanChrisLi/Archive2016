package com.file.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DirManager {
	
	public static final SimpleDateFormat filterFmt = new SimpleDateFormat ("MMdd");;
	
	public static void createDir ( String dirStr ) {
		
		
		File dir = new File(dirStr);
		
		if ( ! dir.exists() ) {
			//System.out.println("Creating directory " + dir);
			
			dir.mkdir();
			dir.setWritable(true, false);
		}
	}
	
	public synchronized static String createDirWithCRQnTS (String userDir, String crq) {
		
		rmSubFiles(userDir);
		
		String tsStr =  new SimpleDateFormat("MMddHHmmss").format(new Date());
		String crqShort = crq.replaceFirst("^CRQ0+(?!$)", "RFC");
		
		String dirStr = userDir + "/" + crqShort + "_" + tsStr;
		
		createDir(dirStr);
		
		return dirStr;
	}
	
	
	private static void rmSubFiles (String dir) {
		
		//SimpleDateFormat filterFmt = new SimpleDateFormat ("MMdd");
		
		File curFolder = new File(dir);
		String[] subDirs = curFolder.list();
		
		for ( int i = 0; i< subDirs.length; i ++ ) {
			
			File file = new File(dir + "/" + subDirs[i]);
			int curDateVal = Integer.parseInt(filterFmt.format(new Date().getTime()));
			int fileDateVal = Integer.parseInt(filterFmt.format(file.lastModified()));
			
			if (curDateVal - fileDateVal > 0) {
				rmFile( file);
			}
		}
	}
	
	private static void rmFile (File file ) {
		
		
		if (file.isDirectory()) {
			//System.out.println("directory: " + file.getPath());
			rmSubFiles(file.getPath());
		}
		
		file.delete();
	}
	
}