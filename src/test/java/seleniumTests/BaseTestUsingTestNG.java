package seleniumTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.FlightResultPage;
import pageObjects.FlightHomePage;
import pageTests.FlightHomePageTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public class BaseTestUsingTestNG {

	protected static WebDriver driver;	

	//@Test
	//public void Test1() throws InterruptedException {		 	  


	//  FlightHomePageTest flightHomeTest = new FlightHomePageTest(driver);
	//  Assert.assertTrue(flightHomeTest.VerifyUserIsPresentFlightTab(), "User is not present flight tab, please recheck!!!");

	// FlightHomePage flh = new FlightHomePage(driver);
	// Assert.assertTrue(flh.VerifyUserIsPresentFlightTab(), "User is not present flight tab, please recheck!!!");
	//  FlightHomePage flh = new FlightHomePage(driver);


	//	  flh.EnterToDestination(strToDestination);
	//	  flh.PickingDepartDate(strDepartDate);
	//	  flh.PickingReturnDate(strReturnDate);	 
	//	  flh.SearchFlightAndGoToFlightFilterPage();	  
	//	
	//	  //now it is at flight filter page	  
	//	  FlightFilterPage flf = new FlightFilterPage(driver);
	//	//  Assert.assertEquals(flf.VerifyIfSearchFareModalDialogExist(),(Boolean) true);
	//	  //Assert.assertEquals(flf.VerifySearchCheapestFaresText(), (Boolean) true);
	//	  flf.WaitForFlightResultLoad();
	//	  flf.ClickOnSelectAllAirlinesCheckBox();
	//	  flf.ClickOnShowMoreToSeeAirlineOptions();
	//	  flf.SelectPreferAirlineBrand(strAirlinePrefer);
	//	  flf.WaitForFlightResult();	
	//	  flf.GetFirstPriceInFlightResultDetail();

	//}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Starting web driver!");

		//System.setProperty("webdriver.gecko.driver","C:\\Cucumber\\geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver","geckodriver/geckodriver");
		System.setProperty("webdriver.gecko.driver","J:\\Automation 2018\\geckodriver.exe");
		
		/*
		//System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		//System.setProperty("webdriver.firefox.bin","C:\\Users\\Quan.Huynh\\AppData\\Local\\Nightly\\firefox.exe");		
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("browser.link.open_newwindow", 1); 
		options.setHeadless(true);
		driver = new FirefoxDriver(options);
		*/

//	/*	
	  	System.setProperty("webdriver.chrome.driver","J:\\Automation 2018\\chromedriver.exe");
	 	//System.setProperty("webdriver.chrome.driver","chromedriver");
		ChromeOptions options = new ChromeOptions();  
		//options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		driver = new ChromeDriver(options);
	//	*/
		
		driver.get("https://www.flighthub.com/");	
	}

	@AfterMethod
	public void afterMethod() 
	{
		// Close the driver
		System.out.println("closing driver ");
		//driver.close();
		driver.quit();
	}

}