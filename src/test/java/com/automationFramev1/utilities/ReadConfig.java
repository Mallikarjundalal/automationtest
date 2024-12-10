package com.automationFramev1.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ReadConfig {
	Properties p;
	public ReadConfig() {
      try {
      File src=new File("./Configuration//config.properties");
      FileInputStream fis= new  FileInputStream(src);
      p=new Properties();
      p.load(fis);
      } catch (Exception e) {
    	  System.out.println("Excption is"+ e.getMessage());
      }
}
	public String applicationurl() {
		String url=p.getProperty("url");
		return url;
	}
	public String UserName() {
		String url=p.getProperty("userName");
		return url;
	}
	public String Password() {
		String url=p.getProperty("passWord");
		return url;
	}
	public String chromePath() {
		String url=p.getProperty("chromepath");
		return url;
	}
	public String fireFoxpath() {
		String url=p.getProperty("firefoxpath");
		return url;
	}
	
}
