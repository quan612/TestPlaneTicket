package seleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
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
	 * It keeps clicking on the date picker arrow until it finds a year that matches, then month, then day.	 * 
	 * 
	 */
	public void SelectADayInDatePicker(String date, WebDriver driver)
	{
		try
		{			
			By byCalendarArrow = By.cssSelector("a[data-handler='next']");	
			By byLeftTable = By.cssSelector("div[class='ui-datepicker-group ui-datepicker-group-first']");							
			By byCalendarBody = By.cssSelector("div[class='ui-datepicker-group ui-datepicker-group-first'] >  table[class='ui-datepicker-calendar'] > tbody");	
			
			System.out.println("date " + date);
			
			String expectedDay = GetDayFromDate(date);
			String expectedMonth = GetMonthFromDate(date);
			String expectedYear = GetYearFromDate(date); 		
						
			WebElement calendarArrow;	
			WebElement dpTable = driver.findElement(byLeftTable);		
			WebElement dpTitle = dpTable.findElement(By.className("ui-datepicker-title"));
			WebElement dpFromMonth = dpTitle.findElement(By.className("ui-datepicker-month"));
			WebElement dpFromYear = dpTitle.findElement(By.className("ui-datepicker-year"));			
			
			System.out.println("Trying to pick the date in Date picker control " + date);
			System.out.println("Current Calendar month is " + dpFromMonth.getText());
			System.out.println("Current Calendar year is " + dpFromYear.getText());			 

			System.out.println("Expected day is " + expectedDay);
			System.out.println("Expected month is "  + (expectedMonth));
			System.out.println("Expected year is "  + expectedYear); 

			// loop to find match month and year			 
			// == cannot be used to compare string, == mean having the same memory storing location
			while(!dpFromMonth.getText().equals(String.valueOf(expectedMonth)) || !dpFromYear.getText().equals(String.valueOf(expectedYear))) 
			{
				System.out.println("Actual Calendar month is " + dpFromMonth.getAttribute("innerText"));
				if(driver.findElements(byCalendarArrow).size() <1)
				{
					break;
				}
				//loop clicking arrow
				calendarArrow = driver.findElement(byCalendarArrow);				
				calendarArrow.click();
				
				//find the whole calendar month and year again, the date picker element is stale / reset
//				dpTable = driver.findElement(byLeftTable);	
//				dpTitle = dpTable.findElement(By.className("ui-datepicker-title"));
//				dpFromYear = dpTitle.findElement(By.className("ui-datepicker-year"));
//				dpFromMonth = dpTitle.findElement(By.className("ui-datepicker-month"));		 
			} 			
			
			if(!dpFromMonth.getText().equals(expectedMonth))
			{
				byCalendarBody = By.cssSelector("div[class='ui-datepicker-group ui-datepicker-group-last'] >  table[class='ui-datepicker-calendar'] > tbody");	
			}
			
			System.out.println("expect day no value of "  + expectedDay); 
			
			WebElement calendarBody = driver.findElement(byCalendarBody);		
			
			List<WebElement> rowToBeSelected = calendarBody.findElements(By.tagName("tr"));
			//System.out.println("Count test "  + rowToBeSelected.size()); 
			List<WebElement> days;
			WebElement dayTobeClicked = null;
			
			int i = 0;
			for (WebElement temp : rowToBeSelected) {    // 5 rows
				days = temp.findElements(By.tagName("a"));
				for (WebElement temp2 : days) {          // day within rows
					 if(temp2.getText().equals(expectedDay)) 
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
		
	private Calendar ParseDate(String date) throws ParseException
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");			
		Date temporary = dateFormat.parse(date);
		Calendar c = Calendar.getInstance();
		c.setTime(temporary);	
		return c;
	}
	
	private String GetDayFromDate(String date) throws ParseException
	{
		Calendar c = ParseDate(date);
		return String.valueOf(c.get(Calendar.DATE));		
	}
	
	private String GetMonthFromDate(String date) throws ParseException
	{
		Calendar c = ParseDate(date);
		return c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
	}
	
	private String GetYearFromDate(String date) throws ParseException
	{
		Calendar c = ParseDate(date);
		return String.valueOf(c.get(Calendar.YEAR));		
	}
}
