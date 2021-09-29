package com.iNetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties prop;

	// Create Constructor of ReadConfig class
	public ReadConfig() {
		File file = new File("./Configurations/config.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	public String getApplicationURL() {
		String url = prop.getProperty("baseURL");
		return url;
	}
	
	public String getUsername() {
		String uName = prop.getProperty("username");
		return uName;
	}
	
	public String getPassword() {
		String pwd = prop.getProperty("password");
		return pwd;
	}
}