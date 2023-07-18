package com.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.test.constants.SourcePath;

public class Utils extends SourcePath {

	public static String getConfigProperty(String key) throws IOException {
		File file = new File(CONFIG_PROPERTIES_PATH);
	
		FileInputStream fi=new FileInputStream(file);
		Properties propFile=new Properties();
		
			propFile.load(fi);
			String value=propFile.getProperty(key);
			
			return value;
	}
	
	
	
	
	
}
