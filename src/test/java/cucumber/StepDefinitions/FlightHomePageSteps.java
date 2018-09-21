package cucumber.StepDefinitions;

import java.util.List;

import org.testng.Assert;

import cucumber.TestRunner.CucumberRunner;
import cucumber.api.PendingException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.junit.Cucumber;
import pageObjects.FlightHomePage;
import pageObjects.FlightResultPage;



public class FlightHomePageSteps  extends CucumberRunner {
	FlightHomePage flightHomePage;
	FlightResultPage flightResultPage;
	String strDestination = "Ho Chi Minh City, Vietnam - Tan Son Nhat International [SGN]";
	String strDepartDate = "15/11/2018";
	String strReturnDate = "16/12/2018";	
	
	@Given("^the user is on flight home page$")
	public void the_user_is_on_flight_home_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("https://www.flighthub.com/");	
		flightHomePage = new FlightHomePage(driver);
	}

    @When("^user enters flight destination$")
    public void user_enters_flight_destination() throws Throwable {
    	flightHomePage.UserEnterFlightDestination(strDestination);
    }
   
    @When("^user enters the date he wants to depart$")
    public void user_enters_the_date_he_wants_to_depart() throws Throwable {
    	flightHomePage.UserPickDepartDate(strDepartDate);
    }

    @When("^user enteres the date he wants to return$")
    public void user_enteres_the_date_he_wants_to_return() throws Throwable {
    	flightHomePage.UserPickReturnDate(strReturnDate);		
    }
    
    @When("^user clicks on search flight button$")
    public void user_clicks_on_search_flight_button() throws Throwable {
    	flightResultPage = flightHomePage.UserClickSearchFlightAndGoToFlightResultPage();		
    }

    @Then("^user is show all available flight tickets within that time period$")
    public void user_is_show_all_available_flight_tickets_within_that_time_period() throws Throwable {
    	Assert.assertTrue(flightResultPage.VerifySearchFareModalDialogExist());
		Assert.assertTrue(flightResultPage.VerifySearchCheapestFaresText());		
		Assert.assertTrue(flightResultPage.VerifySearchProgessBarExist());	
    }
    
    @Then("^user is presented with validation errors asking to enter flight information$")
    public void user_is_presented_with_validation_errors_asking_to_enter_flight_information() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        List<String> results = flightHomePage.GetValidationErrors();
        Assert.assertEquals(results.get(0), "Please select a destination city.");
        Assert.assertEquals(results.get(1), "Please select a departure date.");
        Assert.assertEquals(results.get(2), "Please select a return date.");
        
    }
}