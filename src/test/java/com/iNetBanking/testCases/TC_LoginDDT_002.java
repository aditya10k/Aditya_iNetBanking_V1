package com.iNetBanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.iNetBanking.pageObject.LoginPage;
import com.iNetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{
	
	@Test(dataProvider = "LoginDataProvider")
	public void loginDDT(String user, String pwd) {
		LoginPage lp = new LoginPage(driver);
		ExtentTest test = extent.createTest("LoginTest Data Driven", "Test to verify login functionality");
		SoftAssert sftAsrt = new SoftAssert();
		lp.setUserName(user);
		logger.info("UserName entered");
		test.info("UserName entered");
		lp.setPasswor(pwd);
		logger.info("Password entered");
		test.info("Password entered");
		lp.clickSubmit();
		logger.info("Clicked on Submit button");
		test.info("Clicked on Submit button");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			sftAsrt.assertTrue(false);
			//Assert.assertTrue(false);
			test.fail("Login Failed");
			logger.warn("Login Failed");
			
		}
		else {
			sftAsrt.assertTrue(true);
			lp.clickLogOut();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			logger.info("Login Passed");
			test.pass("Login Passed");
		}
		sftAsrt.assertAll();
	}
	
	public boolean isAlertPresent() {	
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e) {		
			return false;
		}
	}
	
	public static String loginData[][];
	public static String path;
	@DataProvider(name = "LoginDataProvider")
	public String[][] getData() throws IOException {
		path = System.getProperty("user.dir")+"\\src\\test\\java\\com\\iNetBanking\\testData\\iNetBankingTestData.xls";
		loginData = XLUtils.xlRead(path,"LoginData");
		logger.info("Read xl data successful");

		return loginData;
	}
}
