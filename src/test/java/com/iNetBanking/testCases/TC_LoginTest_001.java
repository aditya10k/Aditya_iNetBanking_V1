package com.iNetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.iNetBanking.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void LoginTest() throws IOException {	
		ExtentTest test = extent.createTest("LoginTest", "Test to verify login functionality");
		//driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);	
		lp.setUserName(username);
		logger.info("UserName entered");
		test.info("UserName entered");
		lp.setPasswor(password);
		logger.info("Password entered");
		test.info("Passwerd entered");
		lp.clickSubmit();
		logger.info("Clicked on submit button");
		test.info("Clicked on submit button");
		//System.out.println(driver.getTitle());
		if(driver.getTitle().equals("GTPL Bank Manager HomePage")) {
			Assert.assertTrue(true);
			test.pass("Title Verified successfully");
		}
		else {
			captureScreen(driver, "LoginTest");
			Assert.assertFalse(false);
			test.fail("Title didn't match");
			
		}
			
	}

}
