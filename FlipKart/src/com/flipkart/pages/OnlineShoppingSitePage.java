package com.flipkart.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class OnlineShoppingSitePage{
	
	//Declaration
	
	@FindBy(xpath="//button[@class='_2AkmmA _29YdH8']")
	private WebElement cncl;
	
	@FindBy(xpath="//a[text()='Login & Signup']")
	private WebElement lgnBTN;
	
	@FindBy(xpath="(//input[@type='text'])[2]")
	private WebElement unTB;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement pwTB;
	
	@FindBy(xpath="(//button[@type='submit'])[2]")
	private WebElement submitBTN;
	
	@FindBy(xpath="//div[text()='Logout']")
	private WebElement LogoutBTN;
	
	//Initialization
	public OnlineShoppingSitePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
}
	
	//Utilization
	public void cancelLgn() {
		cncl.click();
			}
	
	public void signIn() {
		lgnBTN.click();
			}
	
	public void setUserName(String un) {
		unTB.sendKeys(un);
}
	
	public void setPWD(String pw) {
		pwTB.sendKeys(pw);
}
	
	public void submit() {
		submitBTN.click();
	}
	
	public void logoutAccount() {
		LogoutBTN.click();  
		
	}
}
