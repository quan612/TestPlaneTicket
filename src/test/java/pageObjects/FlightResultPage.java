package pageObjects;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumTests.CommonRepository;

public class FlightResultPage {
	WebDriver driver;
	CommonRepository common;

	// search fare model dialog
	By searchFaresModalDialog = By.id("fare-loading-modal");
	By searchCheapestFaresText = By.className("search-modal-searching-text");

	// flight result page left side bar
	By leftSideBarContent = By.id("sidebar-content");
	By leftSideBarShowMoreLink = By.cssSelector("a[class='pointer more-airlines slide-toggle-button collapsed'] > span[class='show-when-collapsed']");
	By leftSideBarSelectAllCheckBox = By.cssSelector("input[id='checkboxSelectAllAirlines']");
	By leftSideBarAllAirlineBrands = By.xpath("//div[@class='container-filter airlines-filter']/div[@class='filter-content']/ul");
	
	// flight result page center
	By flightResultWrap = By.className("flights-results-wrap");
	By progressSearchBar = By.id("fares-search-progress-bar");	
	By flightSearchResult = By.className("packages-os-container");
	By flightPriceWrap = By.xpath(".//div[@class='packages-os-price-wrap']"); //child of flightSearchResult
	By filteringOverlay = By.id("filtering-overlay");

	// constructor
	public FlightResultPage(WebDriver driver)
	{
		this.driver = driver;	
		common = new CommonRepository(driver);		
	}	

	/* ** Public methods ** */	
	public void WaitForFlightResultPageLoad() //done
	{
		try 
		{			
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(progressSearchBar));
			WebElement progressBar = driver.findElement(progressSearchBar);			
			while(progressBar.getCssValue("display").contains("block"))
			{				
				//waiting	for load			
			}
			if(progressBar.getCssValue("display").contains("none"))
			{
				System.out.println("All tickets load!!!");
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Catch exception " + e);
		}
	}

	public void GetAllPriceInFlightResultDetail() //no
	{
		common.ExplicitWaitVisibilityOfElementLocated(flightSearchResult);
		WebElement ulPackageOsExtension = driver.findElement(flightSearchResult);		
		List<WebElement> liListAllFlightDetail = ulPackageOsExtension.findElements(By.cssSelector("li[data-package-list='one-step']"));
		//List<WebElement> test2 = ulPackageOsExtension.findElements(By.xpath(".//li[@data-package-list='one-step']"));
		
		getPricesFromPackageList(liListAllFlightDetail);
	}


	/* ** flight result left side bar ** */
	public void ClickOnShowMoreLinkToSeeMoreAirlineOptions() //ok
	{
		try 
		{
			System.out.println("Wait show more link");						
			common.ExplicitWaitVisibilityOfElementLocated(leftSideBarShowMoreLink);
			WebElement leftSideBarShowMoreControl = driver.findElement(leftSideBarShowMoreLink);
			if(leftSideBarShowMoreControl.isDisplayed())				
				leftSideBarShowMoreControl.click();
			else
				//new Exception("Cannot click on show more link tag");
				System.out.println("There is no show more link ");
		} 
		catch (NoSuchElementException e) {
			System.out.println("No show more link " + e);
		}
		catch (Exception e) {
			System.out.println("Exception is " + e);
		}

	}

	public void ClickOnSelectAllAirlinesCheckBox() //ok
	{
		try {
			System.out.println("Clicking on select all check box");
			common.ExplicitWaitVisibilityOfElementLocated(leftSideBarSelectAllCheckBox);			
			WebElement leftSideBarSelectAllCheckBoxControl = driver.findElement(leftSideBarSelectAllCheckBox);
			if(leftSideBarSelectAllCheckBoxControl.isDisplayed())			
				leftSideBarSelectAllCheckBoxControl.click();
		} 
		catch (Exception e) {
			System.out.println("Exception is " + e);
		}		
	}

	public void SelectPreferAirlineBrand(String strAirlineBrand) 
	{
		//Actions action = new Actions(driver);
		System.out.println("Selecting a prefer airline brand" + strAirlineBrand);
		common.ExplicitWaitVisibilityOfElementLocated(leftSideBarAllAirlineBrands);
		WebElement leftSideBarAirlineBrandsControl = driver.findElement(leftSideBarAllAirlineBrands);		
		List<WebElement> airlineBrands = leftSideBarAirlineBrandsControl.findElements(By.tagName("li"));

		// clicking on prefer airline brand		
		airlineBrands.remove(0); //remove the first li because it contains select all text but not the airline brand
					
		for (WebElement liParents : airlineBrands) {	
			WebElement divParent = liParents.findElement(By.tagName("div"));
			WebElement childInput = divParent.findElement(By.tagName("input"));	
			WebElement childLabel = divParent.findElement(By.tagName("label"));	
			if(childLabel.getText().toString().equals(strAirlineBrand))
			{
				System.out.println("Selecting airline brand !!!!! " + strAirlineBrand);
				//action.moveToElement(childInput).perform();
				childInput.click();
				break;
			}					
		}
	}

	public void GetAllAirlineBrandsName()
	{
		/* good
		List<String> airLineNames = new ArrayList<String>();		
		airlineBrands.remove(0); //remove the first li because it contains select all text but not the airline brand		
		for (WebElement liParents : airlineBrands) {	
			WebElement divParent = liParents.findElement(By.tagName("div"));			
			WebElement childLabel = divParent.findElement(By.tagName("label"));	
			airLineNames.add(childLabel.getText().toString());		
		}
		good */


		WebElement leftSideBarAirlineBrandsControl = driver.findElement(leftSideBarAllAirlineBrands);		
		List<WebElement> airlineBrands = leftSideBarAirlineBrandsControl.findElements(By.tagName("li"));
		airlineBrands.remove(0);  //remove the first li because it contains select all text but not the airline brand	

		List<String> airLineNames = new ArrayList<String>();
		airlineBrands.forEach(item -> airLineNames.add(item.findElement(By.tagName("div")).findElement(By.tagName("label")).getText())); //.stream().map(x -> x.getText()));   //.findElement(By.tagName("label")).stream().map(x -> x.getText()).collect(Collectors.toList());
		System.out.println("List of airline name !!!!! ");
		for (String string : airLineNames) 
		{
			System.out.println(string);
		}
	}

	public void WaitForFlightResultWhenSelectingPreferAirline() //ok
	{		
		WebElement wheelOverlay = driver.findElement(filteringOverlay);	
		while(wheelOverlay.getCssValue("display").contains("block") )
		{
			//System.out.println("waiting for the wheel");
			//do nothing, waiting for the wheel to go away, to indicate the search is finish
		}
		System.out.println("Search for prefer airline brand is done!!! Now it is time to get the cheap price!!!");
	}


	/* ** flight result private method ** */
	private void getPricesFromPackageList(List<WebElement> list) 
	{
		try
		{
			System.out.println("getPricesFromPackageList");
			List<WebElement> divPackageOSTop = new ArrayList<WebElement>();
			List<WebElement> spanPrice = new ArrayList<WebElement>();	
			//System.out.println("div price is " + divPrice.size());
			List<String> strPrice = new ArrayList<String>();
			String combinePrice = "";
			for (WebElement listChild : list) 
			{		
				divPackageOSTop.add(listChild.findElement(flightPriceWrap));				
			}

			for (WebElement listChild2 : divPackageOSTop) {
				spanPrice.addAll(listChild2.findElements(By.xpath(".//div/div/span")));
				//System.out.println("test 2  " + spanPrice.size());			
				for (WebElement temp : spanPrice) {
					combinePrice = combinePrice + temp.getText();
				}
				strPrice.add(combinePrice);
				spanPrice.clear();
				combinePrice = new String();
			}
			List<Double> prices = new ArrayList<Double>();

			for (String string : strPrice) 
			{			
				string = string.replace("$", "").replace(",", "");
				//System.out.println("Price is  " + string);	
				prices.add(Double.parseDouble(string));
			}
			double min = prices.get(0);
			for (Double price : prices) 
			{
				if(min >= price)
				{
					min = price;
				}
			}
			System.out.println("Minimum Price is  " + min);	
		}
		catch(Exception e)
		{
			//
		}
	}

	//*  **** Test methods **** *//	
	public boolean VerifySearchFareModalDialogExist()
	{
		try {
			System.out.println("Verify if search modal dialog exists");
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(searchFaresModalDialog));
			WebElement modalDialogExist = driver.findElement(searchFaresModalDialog);			

			if(modalDialogExist.isDisplayed() && modalDialogExist.getCssValue("visibility").equals("visible"))
			{
				System.out.println("Search flight modal exists");	
				return true;
			}
			else 
			{
				return false;
			} 				
		}
		catch (Exception e) {
			System.out.println("Exception is " + e);
			return false;
		}
	}
	public boolean VerifySearchCheapestFaresText()
	{
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(searchCheapestFaresText));
			WebElement searchFaresText = driver.findElement(searchCheapestFaresText);	
			
			if(searchFaresText.isDisplayed() 
					&& searchFaresText.getCssValue("visibility").equals("visible")
					&& searchFaresText.getText().equals("Searching for our cheapest fares!"))
			{
				System.out.println("Search for fare text is present!!!");		
				return true;
			}
			else 
			{
				return false;
			}
		}
		catch (Exception e) {
			System.out.println("Exception is " + e);
			return false;
		}
	}
	public boolean VerifySearchProgessBarExist()
	{
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(progressSearchBar));
		WebElement progressBar = driver.findElement(progressSearchBar);		
		if(progressBar.getCssValue("display").contains("block"))
		{
			System.out.println("Search progress bar exists!!!");		
			return true;
		}
		else 
		{
			return false;
		}
	}
	public boolean VerifyListOfAirlineBrandsExist()
	{
		common.ExplicitWaitVisibilityOfElementLocated(leftSideBarAllAirlineBrands);
		WebElement leftSideBarAirlineBrandsControl = driver.findElement(leftSideBarAllAirlineBrands);
		if(leftSideBarAirlineBrandsControl.isDisplayed()
				&& leftSideBarAirlineBrandsControl.findElements(By.tagName("li")).size() > 0 )
		{
			System.out.println("List of airline brands exists! " + leftSideBarAirlineBrandsControl.findElements(By.tagName("li")).size() + " airlines. " );
			return true;
		}
		else
			return false;
	}
}
