package pageTests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.FlightResultPage;
import pageObjects.FlightHomePage;
import seleniumTests.BaseTestUsingTestNG;

public class FlightHomePageTest extends BaseTestUsingTestNG {
	//WebDriver driver;
	FlightHomePage flightHomePage;
	FlightResultPage flightResultPage;
	//CommonRepository common;

	public String strAirlinePrefer = "China Eastern Airlines";
	//	public FlightHomePageTest(WebDriver driver)
	//	{
	//		this.driver = driver;		
	//		flightHomePage = new FlightHomePage(driver);
	//		//common = new CommonRepository(driver);
	//	}

	//	public boolean VerifyFlightHubPageTitle()
	//	{
	//		return true;
	//	}
	//	


	//@Test 123456
	public void Verify_Page_Title_And_User_Is_Presented_At_Flight_Tab_In_Flight_Home_Page() 
	{
		flightHomePage = new FlightHomePage(driver);
		Assert.assertTrue(flightHomePage.VerifyFlightHomePageTitle(), "Page title is not correct, please recheck!!!");
		Assert.assertTrue(flightHomePage.VerifyUserIsPresentFlightTab(), "User is not present with flight tab, please recheck!!!");
	}	

	@Test(description="User can search for flight after entering destination, depart date, return date")
	public void Verify_User_Can_Search_For_Flight() 
	{
		String strDestination = "Ho Chi Minh City, Vietnam - Tan Son Nhat International [SGN]";
		String strDepartDate = "15/11/2018";
		String strReturnDate = "16/12/2018";

		flightHomePage = new FlightHomePage(driver);
		flightHomePage.UserEnterFlightDestination(strDestination);
		flightHomePage.UserPickDepartDate(strDepartDate);
		flightHomePage.UserPickReturnDate(strReturnDate);				
		flightResultPage = flightHomePage.UserClickSearchFlightAndGoToFlightResultPage();	
		
		//Assert.assertTrue(flightResultPage.VerifySearchFareModalDialogExist(), "Search modal dialog is not exist, please recheck");
		//Assert.assertTrue(flightResultPage.VerifySearchCheapestFaresText(), "Search cheapest fare text is not exist, please recheck");
	}

	@Test(description="User can select different business class before searching for flight")
	public void Verify_User_Can_Select_Different_Business_Class() 
	{
		flightHomePage = new FlightHomePage(driver);	
		flightHomePage.UserClickOnFlightClassComboBox();
		Assert.assertTrue(flightHomePage.VerifyBusinessClassComboBox());
	}

	@Test(description="User can see passenger type before searching for flight")
	public void Verify_User_Can_Select_Different_Passenger_Type() 
	{
		flightHomePage = new FlightHomePage(driver);	
		flightHomePage.UserClickOnPassengerComboBox();
		Assert.assertTrue(flightHomePage.VerifyPassengerComboBox());
	}
	
	@Test(description="User will see validation error when clicking on search flight button")
	public void Verify_User_Is_Present_With_Validation_Errors() 
	{		
		flightHomePage = new FlightHomePage(driver);	
		Assert.assertTrue(driver.getTitle().contains("Cheap Flights, Airfare, and Hotels - FlightHub.com"));
		flightResultPage = flightHomePage.UserClickSearchFlightAndGoToFlightResultPage();
		List<String> results = flightHomePage.GetValidationErrors();
        Assert.assertEquals(results.get(0), "Please select a destination city.");
        Assert.assertEquals(results.get(1), "Please select a departure date.");
        Assert.assertEquals(results.get(2), "Please select a return date.");
	}
}
