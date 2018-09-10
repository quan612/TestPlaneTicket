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
	String preferAirline = "China Eastern Airlines";
	//@Test
	public void Verify_There_Is_A_Progess_Bar_When_Ticket_Is_Being_Searched()
	{
				
			flightHomePage = new FlightHomePage(driver);
			flightResultPage = flightHomePage.UserSearchForFlight(strDestination, strDepartDate, strReturnDate);			
			
			Assert.assertTrue(flightResultPage.VerifySearchFareModalDialogExist());
			Assert.assertTrue(flightResultPage.VerifySearchCheapestFaresText());		
			Assert.assertTrue(flightResultPage.VerifySearchProgessBarExist());
			//flightResultPage.WaitForFlightResultPageLoad();			
	}
	
	//@Test
	public void Verify_There_Is_A_List_Of_Airline_Brands_That_User_Can_Select()
	{
					
			flightHomePage = new FlightHomePage(driver);
			flightResultPage = flightHomePage.UserSearchForFlight(strDestination, strDepartDate, strReturnDate);
				
			flightResultPage.WaitForFlightResultPageLoad();
			flightResultPage.ClickOnShowMoreLinkToSeeMoreAirlineOptions();
			Assert.assertTrue(flightResultPage.VerifyListOfAirlineBrandsExist());
			flightResultPage.GetAllAirlineBrandsName();	
	}
	
	//@Test
	public void Verify_User_Can_Get_Cheapest_Price_After_Selecting_An_Airline_Brand()
	{
					
			flightHomePage = new FlightHomePage(driver);
			flightResultPage = flightHomePage.UserSearchForFlight(strDestination, strDepartDate, strReturnDate);
				
			flightResultPage.WaitForFlightResultPageLoad();
			flightResultPage.ClickOnShowMoreLinkToSeeMoreAirlineOptions();
			flightResultPage.SelectPreferAirlineBrand(preferAirline);
			flightResultPage.WaitForFlightResultWhenSelectingPreferAirline();
			flightResultPage.GetFirstPriceInFlightResultDetail();
	}

}
