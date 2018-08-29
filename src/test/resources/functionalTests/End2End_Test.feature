Feature: Automated End2End Tests
Description: The purpose of this feature is to test End 2 End integration.

Scenario: Customer places an order by purchasing an item from search
	Given the user is on Home Page
	When he searches for "dress"
	And chooses to buy the first item
	And moves to checkout from mini cart
	And enters personal details on checkout page
	And selects same delivery address
	And selects payment method as "check" payment
	And places the order.