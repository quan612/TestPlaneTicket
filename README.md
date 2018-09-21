# TestPlaneTicket  [![Build Status](https://travis-ci.com/quan612/TestPlaneTicket.png?branch=master)](https://travis-ci.com/quan612/TestPlaneTicket)
A Simple Selenium project written in Java to demonstrate how I build the test automation in my workplace. 

## Getting Started
The test can be run as TestNG test, using @Test annotations or using Cucumber feature file in which the steps are separated into different classes. 

The project follows page object framework design pattern. Page objects classes contain elements, methods, actions, behaviors for a page. Then there are separated tests - cucumber or testNG, calling to these methods and perform assertions.


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
* [Maven](https://maven.apache.org/) - Dependency Management
* [Cucumber Java 1.2.5](https://docs.cucumber.io/installation/java/) - Test Driven Design
* [TestNG 6.14.3](https://testng.org/) - Test Framework supports using of Annotations
* [ClueCumber 1.4](https://github.com/trivago/cluecumber-report-plugin/) - Generate Cucumber report based on Cucumber json result
* [Extent Report 3.1.5](http://extentreports.com/) - Logger

## Acknowledgments
The project follows guidelines from following:

*ClueCumberReport https://github.com/trivago/cluecumber-report-plugin

*Extent Report v3 configuration https://github.com/swtestacademy/extent-reports-version-3-example

*Many other blog posts
