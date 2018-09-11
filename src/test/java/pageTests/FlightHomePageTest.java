package pageTests;

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

	//@Test
	public void Verify_User_Is_Presented_With_Date_Picker_Control_When_Clicked_On_Depart_TextBox() 
	{
		try
		{			
			flightHomePage = new FlightHomePage(driver);			
		}
		catch(Exception e)
		{			
			Assert.fail("This test2 is fail!!! " + e);
		}
	}

	//@Test
	public void Verify_User_Can_Search_For_Flight() 
	{
		String strDestination = "Ho Chi Minh City, Vietnam - Tan Son Nhat International [SGN]";
		String strDepartDate = "15/11/2018";
		String strReturnDate = "16/12/2018";

		flightHomePage = new FlightHomePage(driver);
		flightHomePage.UserSearchForFlight(strDestination, strDepartDate, strReturnDate);

		flightResultPage = new FlightResultPage(driver);
		Assert.assertTrue(flightResultPage.VerifySearchFareModalDialogExist(), "Search modal dialog is not exist, please recheck");
		Assert.assertTrue(flightResultPage.VerifySearchCheapestFaresText(), "Search cheapest fare text is not exist, please recheck");

	}

	//@Test
	public void Verify_User_Can_Select_Different_Business_Class() 
	{
		flightHomePage = new FlightHomePage(driver);	
		flightHomePage.UserClickOnFlightClassComboBox();
		Assert.assertTrue(flightHomePage.VerifyBusinessClassComboBox());

	}

	@Test
	public void Verify_User_Can_Select_Different_Passenger_Type() 
	{
		flightHomePage = new FlightHomePage(driver);	
		flightHomePage.UserClickOnPassengerComboBox();
		Assert.assertTrue(flightHomePage.VerifyPassengerComboBox());

	}
}
