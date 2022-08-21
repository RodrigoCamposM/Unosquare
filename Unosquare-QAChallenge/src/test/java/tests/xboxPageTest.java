package tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import pageobjects.xboxPage;

public class xboxPageTest {
	private WebDriver driver; 
	xboxPage xboxPage; 

	@Before
	public void setUp() throws Exception {
		xboxPage = new xboxPage(driver);
		driver = xboxPage.chromeDriverConnection(); // Creating connection 
		xboxPage.getPage("https://www.microsoft.com/es-mx/"); //Open Browser and go to URL
		xboxPage.maximize();
		Thread.sleep(5000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit(); //Close the browser
	}

	@Test
	public void verifyFirstAppPrice() throws InterruptedException {
		xboxPage.goToXboxShop();	
		assertEquals(xboxPage.getNonFree() ,xboxPage.getPrice());
	}

}
