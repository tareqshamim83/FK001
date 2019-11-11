package com.flipkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CameraNikonCOOLPIXB500Page {
	
	@FindBy(xpath="//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")
	private WebElement CameraNikonCOOLPIXB500;
	
	public CameraNikonCOOLPIXB500Page(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	public void addCameraToCart() {
		
		CameraNikonCOOLPIXB500.click();
	}
}
