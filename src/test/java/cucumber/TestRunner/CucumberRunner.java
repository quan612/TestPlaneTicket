package cucumber.TestRunner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.github.mkolisnyk.cucumber.runner.ExtendedTestNGRunner;

//@ExtendedCucumberOptions(jsonReport = "target/cucumber.json"
//						,overviewReport = true
//						,detailedReport = true
//						,detailedAggregatedReport = true
//						,outputFolder = "target")

@CucumberOptions(
		features="src/test/resources/features"		
		,glue={"cucumber.StepDefinitions"}
		,tags= {"@scenario"}
		//,plugin = { "html:target/cucumber-html-report" 
		//		,"json:target/cucumber.json", "pretty:target/cucumber-pretty.txt"
		//        ,"usage:target/cucumber-usage.json", "junit:target/cucumber-results.xml"}
		,plugin = {"json:target/cucumber-report/cucumber.json"}
		,monochrome = true) 

public class CucumberRunner //extends ExtendedTestNGRunner //extends ExtendedTestNGRunner //extends AbstractTestNGCucumberTests  sss
{
	public static WebDriver driver;
	private TestNGCucumberRunner testRunner;

	@BeforeSuite
	public static void suiteSetUp() {
		// TODO: Add setup code
	}
	@AfterSuite
	public static void suiteTearDown() {
		// TODO: Add tear down code
	}
		
	@BeforeTest
	//@BeforeMethod
	public void setUP()
	{
		System.out.println("Test set up is here");
		//System.setProperty("webdriver.chrome.driver","C:\\Cucumber\\chromedriver.exe");
		
		//System.setProperty("webdriver.gecko.driver","C:\\Cucumber\\geckodriver.exe");
		System.setProperty("webdriver.gecko.driver","geckodriver/geckodriver");
		
		
		//System.setProperty("webdriver.firefox.bin","C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");		
		FirefoxOptions options = new FirefoxOptions();
		options.addPreference("browser.link.open_newwindow", 1); 
		//options.setHeadless(true);
		
		driver = new FirefoxDriver(options);
		//driver = new ChromeDriver();
		testRunner = new TestNGCucumberRunner(CucumberRunner.class);			
	}	

	//@Test(description="login",dataProvider="features")
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

	
	@AfterTest
	//@AfterMethod
	public void tearDown()
	{
		System.out.println("Test tear down is here");
		testRunner.finish();
		driver.quit();
	}	
}

//*/
