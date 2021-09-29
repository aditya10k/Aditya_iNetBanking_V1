package com.iNetBanking.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.iNetBanking.testCases.BaseClass;

public class LoginPage extends BaseClass{
	//WebDriver ldriver;
	
	public LoginPage(WebDriver driver){
		//ldriver = rdriver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(linkText = "Log out")
	@CacheLookup
	WebElement lnkLogout;
	
	//Below are the three action method for Login Page				
	public void setUserName(String uName) {
		txtUserName.sendKeys(uName);
	}
	
	public void setPasswor(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSubmit() {
		btnLogin.submit();
	}
	
	public void clickLogOut() {
		lnkLogout.click();
	}
}

