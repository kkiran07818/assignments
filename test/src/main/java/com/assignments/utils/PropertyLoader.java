package com.assignments.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
	
	public static Properties prop = new Properties();
	
	public static Properties loadProperties() {
		
		try { prop.load(new FileInputStream(new File(FilePath.USER_DETAILS)));
		  prop.load(new FileInputStream(new File(FilePath.PAGE_DETAILS)));
		  
		  } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
		  e) { e.printStackTrace(); }
		return prop;
		
	}

}
