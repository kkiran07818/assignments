package com.assignments.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class ScreenShotUtil {

	@AfterMethod
	public void takeScreenShot(ITestResult result) {
		
		if(ITestResult.FAILURE==result.getStatus()) {
			try {
				
				TakesScreenshot screenShot = (TakesScreenshot)ConfigurationUtils.driver;
				File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);
				
				FileUtils.copyFile(sourceFile, new File("screenshots\\"+result.getName()+".png"));
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
