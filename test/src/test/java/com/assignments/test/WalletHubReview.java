package com.assignments.test;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.assignments.utils.ConfigurationUtils;
import com.assignments.utils.PropertyLoader;

import junit.framework.Assert;

// class to review wallethub urls
public class WalletHubReview {
	
	String walletHubUserName = null;
	String walletHubPasswd = null;
	
	static String walletHubRegURL = "https://wallethub.com/join/light";
	static String review_sub_url = "https://wallethub.com/profile/test_insurance_company/";
	static String review_verification_url = "https://wallethub.com/profile/username/reviews/";
	
	@Test
	public void postUserReviewOnWalletHub() {
		
		//initiate webdriver instance from ConfigurationUtils class
		WebDriver driver = ConfigurationUtils.setDriverDetails();
		
		// read user details from properties file
		Properties prop = PropertyLoader.loadProperties();
		walletHubUserName = prop.getProperty("walletHubUN");
		walletHubPasswd = prop.getProperty("walletHubPassword");
		
		
		// click on login animation to enter username and password
		WebElement loginClick = driver.findElement(By.xpath(prop.getProperty("walletHubLoginClick")));
		loginClick.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 1000);
		
		WebElement userNameField = driver.findElement(By.xpath(prop.getProperty("walletHubUserNameArea")));
		userNameField.sendKeys(walletHubUserName);
		
		WebElement passwordField = driver.findElement(By.xpath(prop.getProperty("walletHubPasswordArea")));
		passwordField.sendKeys(walletHubPasswd);
		
		WebElement loginButton = driver.findElement(By.xpath(prop.getProperty("walletHubLoginButton")));
		loginButton.click();
		
		//post login navigate to review url
		driver.navigate().to(review_sub_url);
		
		//handling free credit score pop up
		WebElement csrPopup = driver.findElement(By.xpath(prop.getProperty("walletHubCSRPopUp")));
		wait.until(ExpectedConditions.elementToBeClickable(csrPopup));
		csrPopup.click();
		
		//hovering on review stars and selecting 5th one
		WebElement reviewStars = driver.findElement(By.xpath(prop.getProperty("walletHubCSRReviewStarHover")));
		Actions actionBuilder = new Actions(driver);
		actionBuilder.moveToElement(reviewStars);
		WebElement review5Star = driver.findElement(By.xpath(prop.getProperty("walletHubCSRReview5Star")));
		actionBuilder.moveToElement(review5Star).click().perform();
		
		//selecting health insurance from policy dropdown
		WebElement policySelector = driver.findElement(By.xpath(prop.getProperty("walletHubPolicySelector")));
		wait.until(ExpectedConditions.elementToBeClickable(policySelector));
		policySelector.click();
		WebElement healthPolicySelector = driver.findElement(By.xpath(prop.getProperty("walletHubHealthPolicy")));
		healthPolicySelector.click();
		
		//selecting 5th star
		WebElement healthPolicyStar = driver.findElement(By.xpath(prop.getProperty("walletHubHealthPolicyStar")));
		healthPolicyStar.click();
		
		//posting a review
		WebElement policyReview = driver.findElement(By.xpath(prop.getProperty("walletHubPolicyReviewComment")));
		policyReview.clear();
		
		String reviewMessage = "";
		//for loop to enter review less than 200 chars
		for(int i=0;i<30;i++)
		{
			reviewMessage+= " Good ";
			
		}
		policyReview.sendKeys(reviewMessage);
		policyReview.submit();
		
		//wait for confirmation
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(prop.getProperty("walletHubPolicyReviewConfirmation")))));
		
		//navigating to profile to see if review is posted exists
		driver.navigate().to(review_verification_url);
		String postedReview = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("review updated", postedReview.contains(reviewMessage));
		
	}

}
