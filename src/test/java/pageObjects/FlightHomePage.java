package pageObjects;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumTests.CommonRepository;

public class FlightHomePage {

	WebDriver driver;
	CommonRepository common;
	FlightResultPage flightResultPage;
	By toDestinationTxtBox = By.xpath("//div['fields-row clearfix container-home-search-from']/div[2]/div[2]/input[1]");
	By departTextBox = By.xpath("//div[@class='main_search_fares_form']/div[2]/div/div[3]/div[1]/div[2]");
	By returnTextBox = By.xpath("//div[@class='main_search_fares_form']/div[2]/div/div[3]/div[2]/div[2]");
	By btnSearchFlight = By.id("btn-search-flight");
	By businessComboBox = By.id("class_select");
	
	//tab
	By ulTab = By.cssSelector("ul[class='hp-form-tabs clearfix']");

	// constructor
	public FlightHomePage(WebDriver driver)
	{
		this.driver = driver;	
		common = new CommonRepository(driver);
	}	

	/* ** private methods  ** */
	private void ClickOnDepartTextBox()
	{		
		driver.findElement(departTextBox).click();
	}	

	private void ClickOnReturnTextBox()
	{
		driver.findElement(returnTextBox).click();
	}

	private void SearchFlightAndGoToFlightResultPage()
	{
		driver.findElement(btnSearchFlight).click();	
		System.out.println("Click on Search flight button!!!");		
	}		

	private void EnterFlightDestination(String destination) 
	{
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(toDestinationTxtBox));
		driver.findElement(toDestinationTxtBox).click();		
		driver.findElement(toDestinationTxtBox).sendKeys(destination);		
		driver.findElement(toDestinationTxtBox).sendKeys(Keys.TAB);
	}

	/* ** Public methods  ** */
	public FlightResultPage UserSearchForFlight(String destination, String departDate, String returnDate)
	{		
		EnterFlightDestination(destination);	
		ClickOnDepartTextBox();		
		common.SelectADayInDatePicker(departDate, driver);		
		ClickOnReturnTextBox();		
		common.SelectADayInDatePicker(returnDate, driver);	 		
		SearchFlightAndGoToFlightResultPage();	 
		return new FlightResultPage(driver);
	}
	public void UserClickOnFlightClassComboBox()
	{
		driver.findElement(businessComboBox).click();
	}

	/* ** Test methods  ** */
	public boolean VerifyUserIsPresentFlightTab()
	{
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(ulTab));
		WebElement ul = driver.findElement(ulTab);	
		WebElement flightTab = ul.findElement(By.tagName("li")).findElement(By.tagName("a"));		
		if(flightTab.getAttribute("id").equals("search_type_flights")
				&& flightTab.getAttribute("class").equals("active"))
		{	
			System.out.println("Flight tab is in active state!!!");
			return true;
		}		
		else
			return false;
	}
	public boolean VerifyFlightHomePageTitle()
	{
		if(driver.getTitle().equals("Cheap Flights, Airfare, and Hotels - FlightHub.com"))
		{	
			System.out.println("User is at flight home page!!!");
			return true;
		}		
		else
			return false;
	}
	public boolean VerifyBusinessClassComboBox()
	{
		try
		{
			boolean result = false;
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(businessComboBox));	
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(businessComboBox));
			
			
			List<WebElement> items = driver.findElement(businessComboBox)
					.findElement(By.id("home-class-dropdown"))
					.findElement(By.tagName("ul"))
					.findElements(By.tagName("li"));
			
			
			if(items.get(0).getText().equals("Economy/Coach")
				&& items.get(1).getText().equals("Premium Economy")
				&& items.get(2).getText().equals("Business")
				&& items.get(3).getText().equals("First"))
				{
					System.out.println("Business class combo box test pass!!!");
					result = true;				
				}
			else
				result = false;
			
			return result;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean VerifyBsinessClassComboBoxItemIsClickAble()
	{
		return true;
	}
}
