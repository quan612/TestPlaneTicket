package seleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonRepository {

	WebDriver driver;
	
	public CommonRepository(WebDriver driver)
	{
		this.driver = driver;		
	}
	
	public boolean isElementPresent(By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }
	
	public void ExplicitWaitVisibilityOfElementLocated(By el)
	{
		new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(el));
		System.out.println("wait");	
	}
	
	
	/*
	 * The method will select the date on the date picker control.
	 * It keeps clicking on the arrow until it finds a year that matches, then month, then day.	 * 
	 * 
	 */
	public void SelectADayInDatePicker(String date, WebDriver driver)
	{
		try
		{			
			By byCalendarArrow = By.cssSelector("a[data-handler='next']");	
			By byCalendarBody = By.cssSelector("div[class='ui-datepicker-group ui-datepicker-group-first'] >  table[class='ui-datepicker-calendar'] > tbody");					
			By byLeftTable = By.cssSelector("div[class='ui-datepicker-group ui-datepicker-group-first']");
									
			WebElement calendarArrow;	
			WebElement dpLeftTable = driver.findElement(byLeftTable);		
			WebElement dpLeftTitle = dpLeftTable.findElement(By.className("ui-datepicker-title"));
			WebElement dpFromMonth = dpLeftTitle.findElement(By.className("ui-datepicker-month"));
			WebElement dpFromYear = dpLeftTitle.findElement(By.className("ui-datepicker-year"));			
			
			System.out.println("Trying to pick the date in Date picker control " + date);
			System.out.println("Current Calendar month is " + dpFromMonth.getText());
			System.out.println("Current Calendar year is " + dpFromYear.getText());

			//This is the expected date, to be compared with the date in the web site
			SimpleDateFormat parseDate = new SimpleDateFormat("dd/MM/yyyy");			
			Date temporary = parseDate.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(temporary);

			String expectedDay = String.valueOf(c.get(Calendar.DATE));
			String expectedMonth = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
			String expectedYear = String.valueOf(c.get(Calendar.YEAR));		  

			System.out.println("Expected day is " + expectedDay);
			System.out.println("Expected month is "  + (expectedMonth));
			System.out.println("Expected year is "  + expectedYear); 

			// loop to find match month and year			 
			// == cannot be used to compare string, == mean having the same memory storing location
			while(!dpFromMonth.getText().equals(String.valueOf(expectedMonth)) || !dpFromYear.getText().equals(String.valueOf(expectedYear))) 
			{
				//System.out.println("Actual Calendar month is " + dpFromMonth.getAttribute("innerText"));
				//loop clicking arrow
				calendarArrow = driver.findElement(byCalendarArrow);				
				calendarArrow.click();
				
				//find the whole calendar month and year again, the date picker element is stale / reset
				dpLeftTable = driver.findElement(byLeftTable);	
				dpLeftTitle = dpLeftTable.findElement(By.className("ui-datepicker-title"));
				dpFromYear = dpLeftTitle.findElement(By.className("ui-datepicker-year"));
				dpFromMonth = dpLeftTitle.findElement(By.className("ui-datepicker-month"));		 
			}  
			
			// click to select the date
			WebElement calendarBody = driver.findElement(byCalendarBody);		
			
			List<WebElement> rowToBeSelected = calendarBody.findElements(By.tagName("tr"));
			//System.out.println("Count test "  + rowToBeSelected.size()); 
			List<WebElement> days;
			WebElement dayTobeClicked = null;
			
			int i = 0;
			for (WebElement temp : rowToBeSelected) {    // 5 rows
				days = temp.findElements(By.tagName("a"));
				for (WebElement temp2 : days) {          // day within rows
					 if(temp2.getText().equals(String.valueOf(expectedDay))) 
					 {
						 dayTobeClicked = temp2;
						 i = 1;
						 break;
					 }
				}
				if(i == 1)
					break;
			}
			dayTobeClicked.click();		
		}
		catch(Exception e)
		{
			System.out.println(e); 
		}
	}	
}
