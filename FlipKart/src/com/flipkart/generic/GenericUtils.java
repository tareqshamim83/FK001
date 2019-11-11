package com.flipkart.generic;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

	 
	public class GenericUtils {
		
		public static WebDriver driver;
	
	  public static void getScreenShot(WebDriver driver, String path) {
		
		TakesScreenshot t = (TakesScreenshot) driver;
		
		File srcFile = t.getScreenshotAs(OutputType.FILE);
		
		File destFile = new File(path);
		
		try {
			
			FileUtils.copyFile(srcFile, destFile);
		}
		catch(IOException e) {
			
			e.printStackTrace();
		}
		
	}
	  
	 		
}
