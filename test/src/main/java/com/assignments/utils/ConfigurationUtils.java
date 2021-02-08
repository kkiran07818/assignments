package com.assignments.utils;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// class to get driver object with browser details set
public class ConfigurationUtils {
	
	public static WebDriver driver = null;
	
	public static WebDriver setDriverDetails() {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Jars/chromedriver.exe");

		Map<String, Object> preference = new HashMap<String, Object>();
		preference.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", preference);
		
		driver = new ChromeDriver(options);
		
		return driver;
	}
	
	
}
