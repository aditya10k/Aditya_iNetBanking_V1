package com.iNetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.iNetBanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass{
	//Creating object of ReadConfig class to get access to all the methods
	ReadConfig readconfig = new ReadConfig();		
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	
	@BeforeSuite
	public void setUp() {
        // start reporters
       htmlReporter = new ExtentHtmlReporter("extentReportiNetBanking.html");
        // create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
	}
	
	@AfterSuite
	public void tearDownReport() {
		 // calling flush writes everything to the log file
        extent.flush();
	}
	@Parameters("browser")
	@BeforeClass
	public void setup(String brName) {
		logger = LogManager.getLogger(BaseClass.class);
		logger.info("Came Inside Setup method");
		//logger = Logger.getLogger("BaseClass");
		//PropertyConfigurator.configure("log4j2.properties");
		if(brName.equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		logger.info("Opened the Chrome browser");
		}
		else if(brName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info("Opened the firefox browser");
		}
		
		else if(brName.equals("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			logger.info("Opened the IE browser");
		}
		
		else {
			System.out.println("Selenium doesn't support " + brName + " browser");
			logger.warn("Test Can't run on the requested browser as Selenium doesn't suport it");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseURL);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		logger.info("-------------Successfully quited the browser------------");
	}
	
	public void captureScreen(WebDriver driver,String tName) throws IOException  {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/screenshots/" + tName +".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		
	}
	
	public String generateRandomString(int length) {		
		String rndString = RandomStringUtils.randomAlphabetic(length);
		return rndString;
	}
	
	public String generateRandomNumber(int length) {
			String rndnbrString = RandomStringUtils.randomNumeric(length);
			return rndnbrString;
	}
}
