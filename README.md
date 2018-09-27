# TestPlaneTicket         [![Build Status](https://travis-ci.com/quan612/TestPlaneTicket.png?branch=master)](https://travis-ci.com/quan612/TestPlaneTicket)

A simple Selenium project written in Java to demonstrate how I built the test automation in my workplace. 

## Introduction 

The project follows page object framework design pattern. The page objects classes contain page elements, methods, actions, test methods for a page. Then there are separated test classes- using cucumber step definitions or TestNG annotations, calling these methods and perform assertions.

## Features
+ Page Object framework pattern
+ Using explicit wait to wait for element dynamically
+ Test methods are separated from automation framework
+ All tests are independent.

## Project structure
 ```bash
    ├── ...
    ├── src/test/java                     # Test files (alternatively `spec` or `tests`)
    │   ├── cucumber.StepDefinitions          # Step definitions for cucumber feature file
    │   ├── cucumber.TestRunner               # Define cucumber options, features, tags and matching glue steps
    │   ├── cucumber.Hooks                    # Hooks for Cucumber set up
    │   ├── pageObjects                       # Classes contain objects, methods for the page
    │   ├── pageTest                          # Test classes, calling objects from pageObjects and do assertion
    │   ├── seleniumTests                     # Base test set up and common repository
    │   ├── utils.ExtentReports               # Extent Reports library class
    │   └── utils.Listeners                   # Event listener for Extent Report
    ├── src/test/resources                # Feature file, for Cucumber
    └── ...
```

## Built With

* [Selenium 3](https://www.seleniumhq.org/) - The testing framework
* [Maven 3.5.3](https://maven.apache.org/) - Dependency Management
* [Cucumber Java 1.2.5](https://docs.cucumber.io/installation/java/) - Test Driven Design
* [Java JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html/)
* [TestNG 6.14.3](https://testng.org/) - Test Framework supports using of Annotations
* [ClueCumber 1.4](https://github.com/trivago/cluecumber-report-plugin/) - Generate Cucumber report based on Cucumber json result
* [Extent Report 3.1.5](http://extentreports.com/) - Logger

## Usage

The test can be run as TestNG test - using @Test annotations or using Cucumber feature file in which the steps are separated into different classes.

1. Download the project and extract the folder.
2. Modify environment.
 **BaseTestUsingTestNG.java** or **CucumberRunner.java**
 ```bash
 Modify gecko driver path
 Modify Chrome path
 Modiy Firefox path
 ```
3. Specify how the tests run.

 **testng.xml**
 
 *Specify class name to have the tests run with testNG*
 ```bash
 <class name="pageTests.FlightResultPageTest"></class>
 <class name="pageTests.FlightHomePageTest"></class>       
 ```
 *Specify class name to have the tests run with Cucumber feature*
 ```bash 
 <class name="cucumber.TestRunner.CucumberRunner"></class>  
 ```
 
 **Maven**
 
 *Executing the tests using Maven command*
  ```bash
 mvn clean
 mvn test -- for TestNG
 mvn install -- for Cucumber
 ```

## Issues :x:
Having issue running the test on Chrome as the browser opens the link on new tab. Adding "setAttribute('target', '_self')" does not solve the issue
## Acknowledgments
The project follows guidelines from the following:

[ClueCumberReport](https://github.com/trivago/cluecumber-report-plugin)

[Extent Report v3 configuration](https://github.com/swtestacademy/extent-reports-version-3-example)

[TestNG - Cucumber boilerplate](https://github.com/igniteram/testng-cucumber)

*Many other blog posts
