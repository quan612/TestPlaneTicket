package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumTests.CommonRepository;

public class FlightHomePage {
	WebDriver driver;
	CommonRepository common;
	FlightResultPage flightResultPage;

	By toDestinationTxtBox = By.xpath("//div['fields-row clearfix container-home-search-from']/div[2]/div[2]/input[1]");
	By datePickerControl = By.id("datepicker");
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

		try
		{
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(btnSearchFlight));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('btn-search-flight').setAttribute('target', '_self')"); // to force the transition to new page is performed on the same tab, instead to a new tab
			System.out.println(driver.findElement(btnSearchFlight).getAttribute("target"));	
			
			driver.findElement(btnSearchFlight).click();	
			System.out.println("Click on Search flight button!!!");		 
			return new FlightResultPage(driver);
		}
		catch(Exception e)
		{
			System.out.println("Catch exception " + e);
			return null;
		}

	}
	public void UserEnterFlightDestination(String destination)
	{	
		try
		{
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(toDestinationTxtBox));					
			new Actions(driver).moveToElement(driver.findElement(toDestinationTxtBox)).click().perform();
			driver.findElement(toDestinationTxtBox).sendKeys(destination);		
			driver.findElement(toDestinationTxtBox).sendKeys(Keys.TAB);		
		}
		catch(Exception e)
		{
			System.out.println("Catch exception " + e);
		}
	}
	public void UserPickDepartDate(String departDate)
	{		
		try 
		{
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(departTextBox));
			driver.findElement(departTextBox).click();
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(datePickerControl)); //wait until picker control is located
			common.SelectADayInDatePicker(departDate, driver);		
		}
		catch(Exception e)
		{
			System.out.println("Catch exception " + e);
		}
	}
	public void UserPickReturnDate(String returnDate)
	{				
		try 
		{
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(returnTextBox));	
			driver.findElement(returnTextBox).click();
			new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(datePickerControl)); //wait until picker control is located
			common.SelectADayInDatePicker(returnDate, driver);	 
		}
		catch(Exception e)
		{
			System.out.println("Catch exception " + e);
		}
	}

	public void UserClickOnFlightClassComboBox()
	{
		try 
		{
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(businessComboBox));		
			driver.findElement(businessComboBox).click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(businessContainer));	
			new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfNestedElementLocatedBy(businessContainer, businessContainerLiChilid)); // wait until all children in combox box are located
		}
		catch(Exception e)
		{
			System.out.println("Catch exception " + e);
		}
	}

	public void UserClickOnPassengerComboBox()
	{
		try 
		{
			//new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(passengerComboBox));	
			new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(passengerComboBox));
			driver.findElement(passengerComboBox).click();	
		}
		catch(Exception e)
		{
			System.out.println("Catch exception " + e);
		}
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
		try {
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
		catch(Exception e)
		{
			System.out.println("Catch exception " + e);
			return false;
		}
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
				catch(NoSuchElementException e) // in case span father not containing span children, we put space to get expected string
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
