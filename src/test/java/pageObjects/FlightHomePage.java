package pageObjects;

import java.util.ArrayList;
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
	By businessContainer = By.id("home-class-dropdown");
	By businessContainerLiChilid = By.xpath(".//li");
	By passengerComboBox = By.id("passenger-select-container");
	By passengerContainer = By.id("home-passengers-container");	
	By validationErrors = By.id("validation_errors");
	
	//tab
	By ulTab = By.cssSelector("ul[class='hp-form-tabs clearfix']");

	// constructor
	public FlightHomePage(WebDriver driver)
	{
		this.driver = driver;	
		common = new CommonRepository(driver);
	}	

	/* ** private methods  ** */	

	/* ** Public methods  ** */
	public FlightResultPage UserClickSearchFlightAndGoToFlightResultPage()
	{			
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(btnSearchFlight));
		driver.findElement(btnSearchFlight).click();	
		System.out.println("Click on Search flight button!!!");		 
		return new FlightResultPage(driver);
	}
	public void UserEnterFlightDestination(String destination)
	{	
		try
		{
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(toDestinationTxtBox));
			driver.findElement(toDestinationTxtBox).click();		
			driver.findElement(toDestinationTxtBox).sendKeys(destination);		
			driver.findElement(toDestinationTxtBox).sendKeys(Keys.TAB);		
		}
		catch(Exception e)
		{
			
		}
	}
	public void UserPickDepartDate(String departDate)
	{		
		try 
		{
			driver.findElement(departTextBox).click();	
			common.SelectADayInDatePicker(departDate, driver);		
		}
		catch(Exception e)
		{
			
		}
	}
	public void UserPickReturnDate(String returnDate)
	{				
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(returnTextBox));	
		driver.findElement(returnTextBox).click();
		common.SelectADayInDatePicker(returnDate, driver);	 	
	}
		
	public void UserClickOnFlightClassComboBox()
	{
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(businessComboBox));	
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(businessComboBox));
		driver.findElement(businessComboBox).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(businessContainer));	
		new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfNestedElementLocatedBy(businessContainer, businessContainerLiChilid));
	}

	public void UserClickOnPassengerComboBox()
	{
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(passengerComboBox));	
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(passengerComboBox));
		driver.findElement(passengerComboBox).click();	
		
	}
	
	public List<String> GetValidationErrors()
	{
		List<String> allErrors = new ArrayList<>();
		
		List<WebElement> li = driver.findElement(validationErrors)
									 .findElement(By.tagName("div"))
									 .findElement(By.tagName("ul"))
									 .findElements(By.tagName("li"));
		li.forEach(s -> allErrors.add(s.getText()));		
		allErrors.forEach(s -> System.out.println(s));
		return allErrors;		
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
			
			System.out.println("Business class combobox items: ");
			List<WebElement> items = driver.findElement(businessComboBox)
					.findElement(By.id("home-class-dropdown"))
					.findElement(By.tagName("ul"))
					.findElements(By.tagName("li"));		// get all li tags containing combobox item	
			
			for (WebElement webElement : items) {
				System.out.println(webElement.getText());
			}
			
			if(items.get(0).getText().equals("Economy/Coach")
				&& items.get(1).getText().equals("Premium Economy")
				&& items.get(2).getText().equals("Business")
				&& items.get(3).getText().equals("First")
					)
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
			System.out.println("Catch exception " + e);
			return false;
		}
	}
	public boolean VerifyPassengerComboBox()
	{
		try
		{			
			System.out.println("Passenger combobox items: ");
			List<WebElement> span = driver.findElement(passengerContainer)
					.findElements(By.xpath(".//*['@class=hp-select-pax-wrap clearfix']/div[2]/span")); // to get all the span inside passenger container			
			
			for (WebElement webElement : span) {
				try 
				{
					System.out.print(webElement.getText() +  " ");
					System.out.println(webElement.findElement(By.tagName("span")).getText());
					
				}
				catch(NoSuchElementException e) // in case span father not containing span children
				{
					System.out.println(" ");
				}					
			}			
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Exception " + e);
			return false;
		}
	}
}
