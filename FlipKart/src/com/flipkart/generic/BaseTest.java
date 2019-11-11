package com.flipkart.generic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;



public abstract class BaseTest implements AutoConstants {
	
	public WebDriver driver;
	/*
	static {
		
		System.setProperty(CHROME_KEY, CHROME_VALUE);
		System.setProperty(GECKO_KEY, GECKO_VALUE);
	}

	@BeforeMethod
	public void openApp() {
		//driver=new FirefoxDriver();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(ITO, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		driver.get(APP_URL);
		
	}
	
	*/
	
	@Parameters({"nodeUrl","browser","appUrl"})
	@BeforeMethod
	public void openApp(String nodeUrl, String browser, String appUrl) throws MalformedURLException {
		
		URL ra=new URL(nodeUrl);
		
		DesiredCapabilities c=new DesiredCapabilities();
		c.setBrowserName(browser);
		driver=new RemoteWebDriver(ra, c);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(ITO, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(appUrl);
		
	}
	
	//This Below code is to take the screen shot for failed TC
	@AfterMethod
	public void closeApp(ITestResult iTestResult) throws InterruptedException {
		
		int status = iTestResult.getStatus();
		String name = iTestResult.getName();
		
		if(status==1) {
			
			Reporter.log("Test" + name + "is PASS", true);
		}
		
		else {
			Reporter.log("Test" + name + "is FAIL / SKIP", true);
		   GenericUtils.getScreenShot(driver, IMG_PATH + name+ ".png");
	}
		
		Thread.sleep(3000);
		driver.quit(); //Close all the flipkart Windows/Browsers
		
	}
	
}
