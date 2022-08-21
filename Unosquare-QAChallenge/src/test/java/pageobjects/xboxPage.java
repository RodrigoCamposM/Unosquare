package pageobjects;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.unosquare.qachallenge.Base;

public class xboxPage extends Base{

	public xboxPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//Locators windows page
	By windowsLinkLocator = By.id("shellmenu_2");
	By windowsPageLocator = By.cssSelector("button[id='uhfCatLogoButton'][aria-label='Windows']");	
	By searchBtnLocator = By.cssSelector("button[id='search']");	
	By searchTxtBoxLocator = By.id("cli_shellHeaderSearchInput");
	By alertBtnLocator = By.xpath("//button[@title='Permanecer en M�xico - Espa�ol']");
	//Locators search xbox page
	By xboxPageLocator = By.xpath("//h2[contains(text(),'Consolas Xbox, juegos y mucho m�s')]");
	By buynowBtnLocator = By.linkText("Comprar");
	By showAppsBtnLocator = By.cssSelector("a[href*='https://www.microsoft.com/es-mx/search/shop/apps?q=Xbox']");
	By nextBtnLocator = By.xpath("//ul[@class='m-pagination']//li/a/span[.='Siguiente']");
	//Locators search shop xbox page		
	By appsLocator = By.xpath("//div[@class='m-channel-placement-item']");
	By appsNameLocator = By.xpath("//h3[@class='c-subheading-6']");
	By appsWithPriceLocator = By.cssSelector("span[itemprop='price'][content^='MXN']");	
	//Locators First xbox app with price
	By priceFirstApp = By.xpath("//div[@class='c0122']/div/span");
			
	
	
	public void goToXboxShop() throws InterruptedException {
		click(windowsLinkLocator); // click in the link to go to Windows page
		wait(2); 
		List<WebElement> resultList = findElements(windowsPageLocator);	// find out if a 'Windows' button is present	
		if(resultList.size()>0){ //Are we in windows page?
			click(searchBtnLocator);
			sendKeys("Xbox\n",searchTxtBoxLocator);
			Thread.sleep(6000);
			click(alertBtnLocator);
			xboxShop();
		} else { //'Windows' button is not present so we are not in windows page
			System.out.println("Windows page was not found"); 
		}
	}
	
	public void xboxShop() throws InterruptedException {
		int numPages=3; //number of pages we will analyze 
		wait(2); 
		List<WebElement> resultList = findElements(xboxPageLocator);	// find out if a sub-title text is present
		if(resultList.size()>0){ //Are we in Xbox page?
			click(buynowBtnLocator); 
			click(showAppsBtnLocator); 
			System.out.println("Total apps in the first " + numPages + " pages is: " + getAppsNumberAndNames(numPages));
			goBack(); //Returning to first page
			Thread.sleep(3000);
			goBack();
			Thread.sleep(3000);
			goBack();
			System.out.println("First app price in grid" + getNonFree());			
		}else { //Subtitle text is not present so we are not in xbox page
			System.out.println("Xbox shop page was not found"); 
		}
	}
	
		
	public int getAppsNumberAndNames (int numPages) throws InterruptedException {
		Thread.sleep(1000);
		int sumElements=0;
		for (int i = 0; i < numPages; i++) {
	        WebElement element = findElement(appsLocator); // Get all app's content
	        List<WebElement> elements = element.findElements(appsNameLocator); 	// Get all the app's name
	        for (WebElement e : elements) {
	            System.out.println("App Title: "+ e.getText());
	        }
	        sumElements += elements.size();	
			click(nextBtnLocator); //going to next page
			Thread.sleep(3000);
		}  
		return sumElements;
	}
	
	public String getNonFree () throws InterruptedException { //Get Price of All Non-Free apps
		Thread.sleep(1000);
		WebElement appsPrice = findElement(appsWithPriceLocator); 
		return appsPrice.getText(); 
		
	}
	
	public String getPrice () throws InterruptedException { //Get Price inside App
		click(appsWithPriceLocator);
		System.out.println("Price displayed inside app" + getText(priceFirstApp));
		return getText(priceFirstApp);
	}
	
	
			
		/*	
			
			for (int i = 0; i < 3; i++) {
				wait(15);
				List<WebElement> appsList = findElements(appsLocator);
				Iterator<WebElement> itr = appsList.iterator();
				while(itr.hasNext()) {
				    System.out.println(itr.next().getText());
				}
				sumElements += appsList.size();					
				click(nextBtnLocator);				
			}
			System.out.println("Total Apps in the first 3 pages is: " + sumElements);
			
		*/	
	
}
