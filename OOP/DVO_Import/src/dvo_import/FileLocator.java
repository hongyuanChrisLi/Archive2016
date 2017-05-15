package dvo_import;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLocator
{
	private File folder;
	
	public void setFolderDir(String dir)
	{
		folder = new File(dir);
	}
	
	public File[] getFileList () 
	{
		File[] filelst = folder.listFiles();
		return filelst;
	}
	
}