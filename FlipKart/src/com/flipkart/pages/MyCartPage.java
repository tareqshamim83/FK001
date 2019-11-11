package com.flipkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyCartPage {
	
	@FindBy(xpath="//div[@class='gdUKd9' and text()='Remove']")
	private WebElement removeCameraNikon;
	
	@FindBy(xpath="//div[@class='gdUKd9 _3Z4XMp _2nQDKB']")
	private WebElement clkRemove;
	
	public MyCartPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	public void removeCameraFromCart() {
		
		removeCameraNikon.click();
	}
	
	public void clkRemove() {
		
	clkRemove.click();
	
	}

}
