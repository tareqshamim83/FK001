package com.flipkart.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.flipkart.generic.BaseTest;
import com.flipkart.generic.ExcelData;
import com.flipkart.pages.CameraNikonCOOLPIXB500Page;
import com.flipkart.pages.MyCartPage;
import com.flipkart.pages.OnlineShoppingSitePage;
import com.flipkart.pages.SearchForProductsPage;

public class VerifyInfoProducSearchVsCartScreens extends BaseTest {

	public Workbook wb;
	Actions act;

	@Test(priority = 1)
	public void testVerifyInfoProducSearchVsCartScreens()
			throws InterruptedException, EncryptedDocumentException, FileNotFoundException, IOException {

		// Getting Data from Excel File-Here put your mobile number and password in excel file
		String mobilenumber = ExcelData.getData(FILE_PATH, "TC_FlipkartLogin", 1, 0);
		String password = ExcelData.getData(FILE_PATH, "TC_FlipkartLogin", 1, 1);

		/*---------Login to FlipKart page--------------*/
		OnlineShoppingSitePage lgn = new OnlineShoppingSitePage(driver);
		//Clicked on cancel button of Login pop up(Hidden Division Pop-Up); comes when getting to APP_URL="https://www.flipkart.com/
		lgn.cancelLgn(); 
		lgn.signIn(); // Now Click on signIn
		lgn.setUserName(mobilenumber); // Provide user name, got it from excel file
		lgn.setPWD(password);// Provide password, got it from excel file
		lgn.submit(); // Click submit button to login

		/* Put Dynamic Wait */
		driver.manage().timeouts().pageLoadTimeout(ITO, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);

		String products = ExcelData.getData(FILE_PATH, "TC_SearchForProducts", 1, 0); // Get product-Camera from excel
																						// file

		/*---------Search Camera in FlipKart--------------*/
		SearchForProductsPage sfp = new SearchForProductsPage(driver);

		// Putting Static/Dynamic Wait to search camera
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		sfp.searchProducts(products); // Search camera and Press Submit
		Thread.sleep(3000);
		sfp.submit();
		/*
		 * Put static and dynamic wait and select Nikon COOLPIX B500 and get the
		 * informations(name/price/description...) and store into Excel file at sheet
		 * 'TC_ProductInformation'
		 */
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// java script to scroll to camera-'Nikon COOLPIX B500'
		js.executeScript("arguments[0].scrollIntoView();",
		driver.findElement(By.xpath("//div[text()='Nikon COOLPIX B500']")));
		Thread.sleep(3000);
		String Pname = driver.findElement(By.xpath("//div[text()='Nikon COOLPIX B500']")).getText();
		String Pprice = driver.findElement(By.xpath("//div[@class='_1vC4OE _2rQ-NK' and text()='₹18,450']")).getText();
		String Pdescription = driver.findElement(By.xpath("//div[text()='Nikon COOLPIX B500']/..//ul[@class='vFw0gD']")).getText();

		Thread.sleep(3000);

		wb = WorkbookFactory.create(new FileInputStream(FILE_PATH));
		wb.getSheet("TC_ProductInformation").createRow(1).createCell(0, CellType.STRING).setCellValue(Pname);
		wb.getSheet("TC_ProductInformation").getRow(1).createCell(1, CellType.STRING).setCellValue(Pprice);
		wb.getSheet("TC_ProductInformation").getRow(1).createCell(2, CellType.STRING).setCellValue(Pdescription);
		wb.write(new FileOutputStream(FILE_PATH));
		wb.close();

		/*
		 * Move to My Cart Page and Store Camera- Name and Price into Excel file at
		 * sheet 'TC_MyCartScreen' and Verify the Name and Price from Product Search and
		 * Cart Screens
		 */
		WebDriverWait wait = new WebDriverWait(driver, ETO);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'https://rukminim1.flixcart.com/image/312/312/camera/q/k/h/nikon-coolpix-b500')]"))).click();

		// Now Switch to child tab/window where cart button is present
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		Set<String> allWHS = driver.getWindowHandles();
		for (String wh : allWHS) {
			driver.switchTo().window(wh);
		}

		// Now Adding Camera - Nikon COOLPIX B500 to cart
		// Adding static and dynamic wait
		Thread.sleep(3000);
		CameraNikonCOOLPIXB500Page c = new CameraNikonCOOLPIXB500Page(driver);
		c.addCameraToCart();
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		String Cname = driver.findElement(By.xpath("//a[text()='Nikon COOLPIX B500']")).getText();
		String Cprice = driver.findElement(By.xpath("//span[@class='pMSy0p XU9vZa']")).getText();
		Thread.sleep(3000);
		wb = WorkbookFactory.create(new FileInputStream(FILE_PATH));
		wb.getSheet("TC_MyCartScreen").createRow(1).createCell(0, CellType.STRING).setCellValue(Cname);
		wb.getSheet("TC_MyCartScreen").getRow(1).createCell(1, CellType.STRING).setCellValue(Cprice);
		wb.write(new FileOutputStream(FILE_PATH));
		wb.close();

		// Assertion for Camera Price and Name verification
		Assert.assertEquals(ExcelData.getData(FILE_PATH, "TC_MyCartScreen", 1, 0),ExcelData.getData(FILE_PATH, "TC_ProductInformation", 1, 0));
		Assert.assertEquals(ExcelData.getData(FILE_PATH, "TC_MyCartScreen", 1, 1),ExcelData.getData(FILE_PATH, "TC_ProductInformation", 1, 1));

		/*
		 * Remove Camera - Nikon COOLPIX B500 from cart
		 */

		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		MyCartPage mcp = new MyCartPage(driver);
		mcp.removeCameraFromCart();
		Thread.sleep(2000);
		mcp.clkRemove();

		// Put Static wait and Logout from MyAccount
		Thread.sleep(4000);
		act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[text()='My Account']"))).perform();
		Thread.sleep(3000);
		lgn.logoutAccount();

	}

}
