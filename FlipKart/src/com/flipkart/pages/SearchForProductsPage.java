package com.flipkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchForProductsPage{
	
	//Declaration
	@FindBy(xpath="//button[@class='_2AkmmA _29YdH8']")
	private WebElement cncl;
	
	@FindBy(xpath="(//input[@type='text'])")
	private WebElement textBOX;
		
	@FindBy(xpath="(//button[@type='submit'])")
	private WebElement submitBTN;
	
	
	//Initialization
	public SearchForProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public void cancelLgn() {
		cncl.click();
			}
	
	public void searchProducts(String products) {
		textBOX.sendKeys(products);
	}
		
	
	public void submit(){
		submitBTN.click();
	}
	
	
}


