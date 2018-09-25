Feature: User Can search for Flight ticket

@scenario
	Scenario: User is asked to select destination, departure, return date if leaving those fields blank
	Given the user is on flight home page
    When user clicks on search flight button
    Then user is presented with validation errors asking to enter flight information
    
@scenario
  Scenario: User is present with flight price after searching for ticket
    Given the user is on flight home page
    When user enters flight destination
	And user enters the date he wants to depart
	And user enteres the date he wants to return
	And user clicks on search flight button
	Then user is show all available flight tickets within that time period
	
@scenario
  Scenario: User is present with flight price after searching for ticket
    Given the user is on flight home page
    When user enters flight destination
	And user enters the date he wants to depart
	And user enteres the date he wants to return
	And user clicks on search flight button
	And user select a prefer airline brand
	Then user is show all available flight tickets within that time period
	
