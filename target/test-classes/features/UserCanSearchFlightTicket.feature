Feature: User Can search for Flight ticket

@scenario
	Scenario: User is asked to select destination, departure, return date if leaving those fields blank
	Given the user is on flight home page
    When user clicks on search flight button
    Then user is presented with validation errors asking to enter flight information
    
@scenario
<<<<<<< HEAD
  Scenario: User is present with flight price after searching for ticket
    Given the user is on flight home page
    When user enters flight destination
	And user enters the date he wants to depart
	And user enteres the date he wants to return
	And user clicks on search flight button
	Then user is show all available flight tickets within that time period
	
@scenario
  Scenario: User is present with flight price after searching for ticket
=======
  Scenario: User can see different airline brands after searching for a ticket
>>>>>>> 835dfdb55bc1d886f474d42cc5a30b2b290d814f
    Given the user is on flight home page
    When user enters flight destination
	And user enters the date he wants to depart
	And user enteres the date he wants to return
	And user clicks on search flight button
<<<<<<< HEAD
	And user select a prefer airline brand
	Then user is show all available flight tickets within that time period
=======
	Then user is show all available flight tickets and available airline brand
>>>>>>> 835dfdb55bc1d886f474d42cc5a30b2b290d814f
	
@scenario
  Scenario: User can see the cheapest ticket for a certain airline brand
    Given the user is on flight home page
    When user enters flight destination
	And user enters the date he wants to depart
	And user enteres the date he wants to return
	And user clicks on search flight button
	And user select prefer airline brand
	Then user can see the cheapest ticket of that airline brand