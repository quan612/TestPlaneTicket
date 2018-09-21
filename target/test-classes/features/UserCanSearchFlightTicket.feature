#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
#@tag
#Feature: Title of your feature
#  I want to use this template for my feature file
# @tag1
#  Scenario: Title of your scenario
#    Given I want to write a step with precondition
#   And some other precondition
#   When I complete action
#  And some other action
# And yet another action
#  Then I validate the outcomes
#  And check more outcomes
# @tag2
# Scenario Outline: Title of your scenario outline
#   Given I want to write a step with <name>
#   When I check for the <value> in step
#   Then I verify the <status> in step
#   Examples:
#     | name  | value | status  |
#    | name1 |     5 | success |
#     | name2 |     7 | Fail    |
Feature: User Can search for Flight ticket

@scenario
	Scenario: User is asked to select destination, departure, return date if leaving those fields blank
	Given the user is on flight home page
    When user clicks on search flight button
    Then user is presented with validation errors asking to enter flight information
    
#@scenario
  Scenario: User is present with flight price after searching for ticket
    Given the user is on flight home page
    When user enters flight destination
	And user enters the date he wants to depart
	And user enteres the date he wants to return
	And user clicks on search flight button
	Then user is show all available flight tickets within that time period
	
