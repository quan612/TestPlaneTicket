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
	
	@Test(description="User can see a progress bar when ticket being searched")
	public void Verify_There_Is_A_Progess_Bar_When_Ticket_Is_Being_Searched()
	{				
			flightHomePage = new FlightHomePage(driver);			
			flightHomePage.UserEnterFlightDestination(strDestination);
			flightHomePage.UserPickDepartDate(strDepartDate);
			flightHomePage.UserPickReturnDate(strReturnDate);					
			flightResultPage = flightHomePage.UserClickSearchFlightAndGoToFlightResultPage();			
			
			Assert.assertTrue(flightResultPage.VerifySearchFareModalDialogExist());					
			Assert.assertTrue(flightResultPage.VerifySearchProgessBarExist());					
	}
	
	@Test(description="User can see all airline brands after searching for a flight")
	public void Verify_There_Is_A_List_Of_Airline_Brands_That_User_Can_Select()
	{					
			flightHomePage = new FlightHomePage(driver);
			flightHomePage.UserEnterFlightDestination(strDestination);
			flightHomePage.UserPickDepartDate(strDepartDate);
			flightHomePage.UserPickReturnDate(strReturnDate);					
			flightResultPage = flightHomePage.UserClickSearchFlightAndGoToFlightResultPage();	
				
			flightResultPage.WaitForFlightResultPageLoad();
			flightResultPage.ClickOnShowMoreLinkToSeeMoreAirlineOptions();
			Assert.assertTrue(flightResultPage.VerifyListOfAirlineBrandsExist());
			flightResultPage.GetAllAirlineBrandsName();	
	}
	
	@Test(description="User can see cheapest price for a certain flight brand")
	public void Verify_User_Can_Get_Cheapest_Price_After_Selecting_An_Airline_Brand()
	{					
			flightHomePage = new FlightHomePage(driver);
			flightHomePage.UserEnterFlightDestination(strDestination);
			flightHomePage.UserPickDepartDate(strDepartDate);
			flightHomePage.UserPickReturnDate(strReturnDate);			
			
			flightResultPage = flightHomePage.UserClickSearchFlightAndGoToFlightResultPage();				
			flightResultPage.WaitForFlightResultPageLoad();
			
			flightResultPage.ClickOnShowMoreLinkToSeeMoreAirlineOptions();
			flightResultPage.SelectPreferAirlineBrand(preferAirline);
			flightResultPage.WaitForFlightResultWhenSelectingPreferAirline();
			flightResultPage.GetAllPriceInFlightResultDetail();
	}
}
