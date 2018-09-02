package pageTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.FlightHomePage;
import pageObjects.FlightResultPage;
import seleniumTests.BaseTestUsingTestNG;

public class FlightResultPageTest extends BaseTestUsingTestNG{
	FlightHomePage flightHomePage;
	FlightResultPage flightResultPage;
	
	String strDestination = "Ho Chi Minh City, Vietnam - Tan Son Nhat International [SGN]";
	String strDepartDate = "15/11/2018";
	String strReturnDate = "16/12/2018";
	
	//@Test
	public void Verify_There_Is_A_Progess_Bar_When_Ticket_Is_Being_Searched()
	{
		try
		{			
			flightHomePage = new FlightHomePage(driver);
			flightResultPage = flightHomePage.UserSearchForFlight(strDestination, strDepartDate, strReturnDate);			
			
		//	Assert.assertTrue(flightResultPage.VerifySearchFareModalDialogExist(), "Search modal dialog is not exist, please recheck");
		//	Assert.assertTrue(flightResultPage.VerifySearchCheapestFaresText(), "Search cheapest fare text is not exist, please recheck");
		
			Assert.assertTrue(flightResultPage.VerifySearchProgessBarExist(), "Search Progress bar is not exists. Pls recheck!!!");
			flightResultPage.WaitForFlightResultPageLoad();
			//Assert.assertFalse(flightResultPage.VerifySearchProgessBarExist(), "Search progress bar should be hidden now after page loaded. Pls recheck!!!");
		}
		catch(Exception e)
		{
			Assert.fail("Test case1 fail! Exception " + e);
		}
	}
	
	@Test
	public void Verify_There_Is_A_List_Of_Airline_Brands_That_User_Can_Select()
	{
		try
		{			
			flightHomePage = new FlightHomePage(driver);
			flightResultPage = flightHomePage.UserSearchForFlight(strDestination, strDepartDate, strReturnDate);
				
			flightResultPage.WaitForFlightResultPageLoad();
			Assert.assertTrue(flightResultPage.VerifyListOfAirlineBrands(), "List of airline brands does not exist. Pls recheck!");
			flightResultPage.GetAllAirlineBrandsName();
		}
		catch(Exception e)
		{
			Assert.fail("Test case2 fail! Exception " + e);
		}
	}
	

}
