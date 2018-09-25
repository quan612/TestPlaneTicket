package cucumber.TestRunner;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

//import com.github.mkolisnyk.cucumber.runner.*;


/* for mkolisnyk report
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json"
						,overviewReport = true
						,detailedReport = true
						,detailedAggregatedReport = true
						,outputFolder = "target")

		//,plugin = { "html:target/cucumber-html-report" 
		//		,"json:target/cucumber.json", "pretty:target/cucumber-pretty.txt"
		//        ,"usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml"}
*/		
@CucumberOptions(
		features="src/test/resources/features"		
		,glue={"cucumber.StepDefinitions"}
		,tags= {"@scenario"}		
		,plugin = {"json:target/cucumber-report/cucumber.json"}
		,monochrome = true) 

public class CucumberRunner //extends ExtendedTestNGRunner //extends ExtendedTestNGRunner //extends AbstractTestNGCucumberTests  sss
{
	public static WebDriver driver;
	private TestNGCucumberRunner testRunner;
		
	@BeforeTest
	//@BeforeMethod
	public void setUP()
	{
		System.out.println("Test set up is here");
		//System.setProperty("webdriver.chrome.driver","C:\\Cucumber\\chromedriver.exe");
		
		//System.setProperty("webdriver.gecko.driver","C:\\Cucumber\\geckodriver.exe");
		System.setProperty("webdriver.gecko.driver","geckodriver/geckodriver");
		//System.setProperty("webdriver.gecko.driver","J:\\Automation 2018\\geckodriver.exe");	
		
		//driver = new ChromeDriver();
		//System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");		
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("browser.link.open_newwindow", 1); 
		options.setHeadless(true);
		
		driver = new FirefoxDriver(options);
		testRunner = new TestNGCucumberRunner(CucumberRunner.class);			
	}
	
//  /*		
	@Test(dataProvider="features")
	public void UserCanSearchForFlightTicket(CucumberFeatureWrapper cFeature)
	{
		testRunner.runCucumber(cFeature.getCucumberFeature());
	}
	
	@DataProvider(name="features")
	public Object[][] getFeatures()
	{
		return testRunner.provideFeatures();
	}	
//	*/
	
	@AfterTest
	//@AfterMethod
	public void tearDown()
	{
		System.out.println("Test tear down is here");
		testRunner.finish();
		driver.quit();
	}	
}