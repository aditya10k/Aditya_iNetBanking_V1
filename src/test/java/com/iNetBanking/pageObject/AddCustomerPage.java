package com.iNetBanking.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.iNetBanking.testCases.BaseClass;

public class AddCustomerPage extends BaseClass{
	//Step1 Create Constructor of AddCustomerPage class	
	public AddCustomerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Step2 Make Repository of all the required objects on the page
	/*@FindBy(linkText="New Customer")
	@CacheLookup
	WebElement lnkNewCustomer;*/
	
	@FindBy(how = How.LINK_TEXT, using ="New Customer")
	@CacheLookup
	WebElement lnkNewCustomer;
	
	@FindBy(how = How.NAME,using = "name")
	@CacheLookup
	WebElement txtCustomerName;
	
	@FindBy(how = How.XPATH,using = "//input[@value='m']")
	@CacheLookup
	WebElement rbtnMale;
	
	@FindBy(how = How.XPATH,using = "//input[@value='f']")
	@CacheLookup
	WebElement rbtnFemale;
	
	@FindBy(how = How.NAME,using = "dob")
	@CacheLookup
	WebElement txtDOB;
	
	@FindBy(how = How.NAME,using = "addr")
	@CacheLookup
	WebElement txtAddress;
	
	@FindBy(how = How.NAME,using = "city")
	@CacheLookup
	WebElement txtCity;
	
	@FindBy(how = How.NAME,using = "state")
	@CacheLookup
	WebElement txtState;
	
	@FindBy(how = How.NAME,using = "pinno")
	@CacheLookup
	WebElement txtPIN;
	
	@FindBy(how = How.NAME,using = "telephoneno")
	@CacheLookup
	WebElement txtMobileNbr;
	
	@FindBy(how = How.NAME,using = "emailid")
	@CacheLookup
	WebElement txtEmailId;
	
	@FindBy(how = How.NAME,using = "password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(how = How.NAME,using = "sub")
	@CacheLookup
	WebElement btnSubmit;
	
	@FindBy(how = How.NAME,using = "res")
	@CacheLookup
	WebElement btnReset;
	
	//Step3 Create Action method	
	public void clikNewCustomer() {
		lnkNewCustomer.click();
	}
	
	public void custName(String cName) {
		txtCustomerName.sendKeys(cName);
	}
	
	public void custMaleGender() {
		rbtnMale.click();
	}
	
	public void custFemaleGender() {
		rbtnFemale.click();
	}
	public void custDOB(String dd,String mm,String yyyy) {
		txtDOB.sendKeys(dd);
		txtDOB.sendKeys(mm);
		txtDOB.sendKeys(yyyy);
	}
	
	public void custAddress(String addr) {
		txtAddress.sendKeys(addr);
	}
	
	public void custCity(String city) {
		txtCity.sendKeys(city);
	}
	public void custState(String state) {
		txtState.sendKeys(state);
	}
	public void custPIN(String pin) {
		txtPIN.sendKeys(String.valueOf(pin));
	}
	
	public void custMobNo(String mob) {
		txtMobileNbr.sendKeys(String.valueOf(mob));
	}
	
	public void custEmail(String email) {
		txtEmailId.sendKeys(email);
	}
	
	public void custPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickSubmit() {
		btnSubmit.click();
	}
	
	public void clickReset() {
		btnReset.click();
	}
	
}

