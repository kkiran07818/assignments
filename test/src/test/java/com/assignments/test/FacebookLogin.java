package com.assignments.test;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.assignments.utils.ConfigurationUtils;
import com.assignments.utils.PropertyLoader;


public class FacebookLogin {

	private String userName = "";
	private String password = "";
	public WebDriver driver = null;
	
	@Test
	public void userLogin() {
		
		//initiate properties to load user details and web page elements
		Properties prop = PropertyLoader.loadProperties();
		// initiate driver instance from ConfigurationUtils class
		driver = ConfigurationUtils.setDriverDetails();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");

		userName = prop.getProperty("UserName");
		password = prop.getProperty("Password");

		// user login
		
		driver.findElement(By.id("email")).sendKeys(userName);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.name("login")).click();
				
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("StatusBox"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("StatusBox"))));
		driver.findElement(By.xpath(prop.getProperty("StatusBox"))).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("statusTextArea"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("statusTextArea"))));
		driver.findElement(By.xpath(prop.getProperty("statusTextArea"))).sendKeys("Hello World");

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.getProperty("postButton"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("postButton"))));
		driver.findElement(By.xpath(prop.getProperty("postButton"))).click();
		
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
