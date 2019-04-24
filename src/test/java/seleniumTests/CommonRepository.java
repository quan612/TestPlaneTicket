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
			By byCalendarBody; 				
			By byLeftTable = By.cssSelector("div[class='ui-datepicker-group ui-datepicker-group-first']");
			By byRightTable = By.cssSelector("div[class='ui-datepicker-group ui-datepicker-group-last']");						
			By byTable;	
			
			System.out.println("date " + date);
			
			String expectedDay = GetDayFromDate(date);
			String expectedMonth = GetMonthFromDate(date);
			String expectedYear = GetYearFromDate(date); 
			
			Boolean isCurrentYear = IsTheDateInCurrentYear(date);
			Boolean isCurrentMonth = IsTheDateInCurrentMonth(date);
			
			System.out.println("Is Current year " + isCurrentYear);
			System.out.println("Is Current month " + isCurrentMonth);
			
			byTable = byLeftTable;
			byCalendarBody = By.cssSelector("div[class='ui-datepicker-group ui-datepicker-group-first'] >  table[class='ui-datepicker-calendar'] > tbody");	
			
//			if(isCurrentYear || (!isCurrentYear && !isCurrentMonth))
//			{
//				byTable = byLeftTable;
//				byCalendarBody = By.cssSelector("div[class='ui-datepicker-group ui-datepicker-group-first'] >  table[class='ui-datepicker-calendar'] > tbody");	
//			}
//			else 
//			{
//				System.out.println("not current year, but in same month ");
//				byTable = byRightTable;
//				byCalendarBody = By.cssSelector("div[class='ui-datepicker-group ui-datepicker-group-last'] >  table[class='ui-datepicker-calendar'] > tbody");	
//			}			
			
			WebElement calendarArrow;	
			WebElement dpTable = driver.findElement(byTable);		
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
				//loop clicking arrow
				calendarArrow = driver.findElement(byCalendarArrow);				
				calendarArrow.click();
				
				//find the whole calendar month and year again, the date picker element is stale / reset
				dpTable = driver.findElement(byLeftTable);	
				dpTitle = dpTable.findElement(By.className("ui-datepicker-title"));
				dpFromYear = dpTitle.findElement(By.className("ui-datepicker-year"));
				dpFromMonth = dpTitle.findElement(By.className("ui-datepicker-month"));		 
			}  
			
			if(!dpFromMonth.getText().equals(String.valueOf(expectedMonth)))
			{
				
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
	
	private Boolean IsTheDateInCurrentYear(String expectDate) throws ParseException
	{
		Calendar c = ParseDate(expectDate);
		int expectYear = c.get(Calendar.YEAR);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (expectYear == year)
			return true;
		else
			return false;
	}
	
	private Boolean IsTheDateInCurrentMonth(String expectDate) throws ParseException
	{
		Calendar c = ParseDate(expectDate);
		int expectMonth = c.get(Calendar.MONTH) + 1;
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		System.out.println("expect month "  + expectMonth); 
		System.out.println("month "  + month); 
		if (expectMonth == month)
			return true;
		else
			return false;
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
