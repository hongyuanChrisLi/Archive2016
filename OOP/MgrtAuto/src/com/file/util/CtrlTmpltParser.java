package com.file.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

import com.control.*;
import com.file.FileReaderFactory;
import com.file.readers.*;

public class CtrlTmpltParser {

	private Path filePath;
	private String tmpltStr;

	public CtrlTmpltParser(Path filepath) {
		this.filePath = filepath;
	};

	public Map<MgrtOpts.SECTION, String> getSectMap() {

		Map<MgrtOpts.SECTION, String> sectMap = new HashMap<MgrtOpts.SECTION, String>();

		try {
			FileReaderFactory tmpltReader = new GenericFileReader(filePath);
			tmpltStr = tmpltReader.readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sectMap.put(MgrtOpts.SECTION.IMPORTPARAMS,
				getSectStr(MgrtOpts.SECTION.IMPORTPARAMS));
		sectMap.put(MgrtOpts.SECTION.FOLDERMAP,
				getSectStr(MgrtOpts.SECTION.FOLDERMAP));
		sectMap.put(MgrtOpts.SECTION.RESOLVECONFLICT,
				getSectStr(MgrtOpts.SECTION.RESOLVECONFLICT));
		sectMap.put(MgrtOpts.SECTION.SPECIFICOBJECT,
				getSectStr(MgrtOpts.SECTION.SPECIFICOBJECT));
		sectMap.put(MgrtOpts.SECTION.SOURCE,
				getSectStr(MgrtOpts.SECTION.SOURCE));
		
		return sectMap;
	}

	private String getSectStr(MgrtOpts.SECTION section) {
		
		String sectLabel = section.getDesc();
		String startLabel = "{#" + sectLabel + "}";
		String endLabel = "{#/" + sectLabel + "}";
	
		
		int startIdx =  tmpltStr.indexOf(startLabel);
		int endIdx = tmpltStr.indexOf(endLabel);
		String sectStr = tmpltStr.substring(startIdx + startLabel.length() + 1, endIdx);
		
		//System.out.println(sectStr);
		
		return sectStr;
	}

}