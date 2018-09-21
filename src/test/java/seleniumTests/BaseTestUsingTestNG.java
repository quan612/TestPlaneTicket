package seleniumTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestUsingTestNG {

	protected static WebDriver driver;			
	
	@BeforeSuite
	public void beforeSuite() {
		
	}
	
	@AfterSuite
	public void afterSuite() {
	
	}
	
	@BeforeMethod
	@Parameters("browser")
	public void beforeClass(String browser) {
		System.out.println("Starting web driver! Running test on " + browser);		
		System.setProperty("webdriver.gecko.driver","C:\\Cucumber\\geckodriver.exe");		
		//System.setProperty("webdriver.gecko.driver","geckodriver/geckodriver");
		//System.setProperty("webdriver.gecko.driver","J:\\Automation 2018\\geckodriver.exe");		
		
		if(browser.equals("firefox"))
		{
				System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
				//System.setProperty("webdriver.firefox.bin","C:\\Users\\Quan.Huynh\\AppData\\Local\\Nightly\\firefox.exe");		
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				firefoxOptions.addPreference("browser.link.open_newwindow", 1); 
				firefoxOptions.setHeadless(true);
				driver = new FirefoxDriver(firefoxOptions);				
		}
		else if(browser.equals("chrome"))
				{	//System.setProperty("webdriver.chrome.driver","J:\\Automation 2018\\chromedriver.exe");
			  	System.setProperty("webdriver.chrome.driver","C:\\Cucumber\\chromedriver.exe");
			 	//System.setProperty("webdriver.chrome.driver","chromedriver");
				ChromeOptions chromeOptions = new ChromeOptions();  
				//chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("--disable-dev-shm-usage");
				driver = new ChromeDriver(chromeOptions);
				}		
		
		//driver.manage().deleteAllCookies();
		driver.get("https://www.flighthub.com/");	
	}

	@AfterMethod
	public void afterClass() 
	{
		// Close the driver
		System.out.println("closing driver ");
		driver.close();
		//driver.quit();
	}

}