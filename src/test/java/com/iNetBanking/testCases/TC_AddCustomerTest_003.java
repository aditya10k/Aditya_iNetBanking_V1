package com.iNetBanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.iNetBanking.pageObject.AddCustomerPage;
import com.iNetBanking.pageObject.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass{
	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
	LoginPage lp = new LoginPage(driver);
	AddCustomerPage addCust = new AddCustomerPage(driver);
	lp.setUserName(username);
	logger.info("UserName entered");
	lp.setPasswor(password);
	logger.info("Password entered");
	lp.clickSubmit();
	
	addCust.clikNewCustomer();
	logger.info("Clicked on New Customer Link");
	logger.info("Entering the customer details");
	addCust.custName("Aditya");
	addCust.custFemaleGender();
	Thread.sleep(7000);
	addCust.custDOB("15", "10", "1975");
	Thread.sleep(3000);
	addCust.custAddress("Balagere");
	addCust.custCity("Bangalore");
	addCust.custState("Karnataka");
	addCust.custPIN("235645");
	addCust.custMobNo("3562455423");
	String email = (generateRandomString(7) +"@gmail.com");
	System.out.println(email);
	addCust.custEmail(email);
	addCust.custPassword("P@Ssowrd123");
	Thread.sleep(5000);
	addCust.clickSubmit();
	logger.info("Entered all the details and clicked on submit Button");
	Thread.sleep(3000);
	boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
	if(res==true) {
		Assert.assertTrue(true);
		logger.info("Pass - Customer Added successfully");
	}
	else {
		logger.info("Fail - Customer didn't get added");
		captureScreen(driver,"addNewCustomer");
		Assert.assertTrue(false);
	}
	
	}
	

}
