package seleniumTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class BaseTestUsingTestNG {

	protected static WebDriver driver;			

	@BeforeSuite
	public void beforeSuite() {
		String operSys = System.getProperty("os.name").toLowerCase();
		System.out.println("The test is running on " + operSys);		
	}

	@AfterSuite
	public void afterSuite() {

	}

	@BeforeMethod
	@Parameters("browser")
	public void beforeClass(String browser) {
				//System.setProperty("webdriver.gecko.driver","geckodriver/geckodriver");
				//System.setProperty("webdriver.gecko.driver","J:\\Automation 2018\\geckodriver.exe");		

		
		System.out.println("Starting web driver! Running test on " + browser);		
		System.setProperty("webdriver.gecko.driver","C:\\Cucumber\\geckodriver.exe");	
		if(browser.equals("firefox"))
		{
			System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");						
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addPreference("browser.link.open_newwindow", 1); 
			//firefoxOptions.setHeadless(true);
			driver = new FirefoxDriver(firefoxOptions);				
		}
		else if(browser.equals("chrome"))
		{	
							//System.setProperty("webdriver.chrome.driver","J:\\Automation 2018\\chromedriver.exe");			
							//System.setProperty("webdriver.chrome.driver","chromedriver");
			
			System.setProperty("webdriver.chrome.driver","C:\\Cucumber\\chromedriver_2.41.exe");
			ChromeOptions chromeOptions = new ChromeOptions();  
			//chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");			
			driver = new ChromeDriver(chromeOptions);
		}		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.flighthub.com/");	
	}

	@AfterMethod
	public void afterClass() 
	{
		System.out.println("closing driver ");
	driver.close();
		//driver.quit();
	}

}