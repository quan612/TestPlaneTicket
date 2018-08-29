package stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.chrome.ChromeOptions;

public class Steps {
	WebDriver driver;
	
	@Given("^the user is on Home Page$")
	public void the_user_is_on_Home_Page()  {
	    // Write code here that turns the phrase above into concrete actions
		System.setProperty("webdriver.chrome.driver","C:\\Cucumber\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		//driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.shop.demoqa.com");
	}
	
	@When("^he searches for \"([^\"]*)\"$")
	public void he_searches_for(String arg1)  {
		driver.navigate().to("http://shop.demoqa.com/?s=" + "dress" + "&post_type=product");
	}

	@When("^chooses to buy the first item$")
	public void chooses_to_buy_the_first_item() throws Throwable {
		List<WebElement> items = driver.findElements(By.cssSelector(".noo-product-inner"));
		items.get(0).click();
		
		WebElement addToCart = driver.findElement(By.cssSelector("button.single_add_to_cart_button"));
		addToCart.click();	
	}		
	
	@When("^moves to checkout from mini cart$")
	public void moves_to_checkout_from_mini_cart() throws Throwable {
		WebElement cart = driver.findElement(By.cssSelector(".cart-button"));
		cart.click();
		
		WebElement continueToCheckout = driver.findElement(By.cssSelector(".checkout-button.alt"));
		continueToCheckout.click();		
	}
	
	@When("^enters personal details on checkout page$")
	public void enters_personal_details_on_checkout_page() throws Throwable {
		Thread.sleep(5000);
		WebElement firstName = driver.findElement(By.cssSelector("#billing_first_name"));
		firstName.sendKeys("Lakshay");
		
		WebElement lastName = driver.findElement(By.cssSelector("#billing_last_name"));
		lastName.sendKeys("Sharma");
		
		WebElement emailAddress = driver.findElement(By.cssSelector("#billing_email"));
		emailAddress.sendKeys("test@gmail.com");
		
		WebElement phone = driver.findElement(By.cssSelector("#billing_phone"));
		phone.sendKeys("07438862327");
				
		WebElement countryDropDown = driver.findElement(By.cssSelector("#billing_country_field .select2-arrow"));
		countryDropDown.click();
		Thread.sleep(2000);
		
		List<WebElement> countryList = driver.findElements(By.cssSelector("#select2-drop ul li"));
		for(WebElement country : countryList){
			if(country.getText().equals("India")) {
				country.click();	
				Thread.sleep(3000);
				break;
			}		
		}
						
		WebElement city = driver.findElement(By.cssSelector("#billing_city"));
		city.sendKeys("Delhi");
		Thread.sleep(3000);
		
		WebElement address = driver.findElement(By.cssSelector("#billing_address_1"));
		address.sendKeys("Shalimar Bagh");
		Thread.sleep(3000);
		
		WebElement postcode = driver.findElement(By.cssSelector("#billing_postcode"));
		postcode.sendKeys("110088");	
		Thread.sleep(3000);
	}
	
	@When("^selects same delivery address$")
	public void selects_same_delivery_address() throws Throwable {
		WebElement shipToDifferetAddress = driver.findElement(By.cssSelector("#ship-to-different-address-checkbox"));
		shipToDifferetAddress.click();
		Thread.sleep(3000);
	}	
	
	@When("^selects payment method as \"([^\"]*)\" payment$")
	public void selects_payment_method_as_payment(String arg1) throws Throwable {
		//List<WebElement> paymentMethod = driver.findElements(By.cssSelector("ul.wc_payment_methods li"));
		//paymentMethod.get(0).click();
		//Thread.sleep(3000);
	}
	
	@When("^places the order\\.$")
	public void places_the_order() throws Throwable {
	//	WebElement acceptTC = driver.findElement(By.cssSelector("#terms.input-checkbox"));
		//acceptTC.click();
	//	Thread.sleep(3000);
		
	//	WebElement placeOrder = driver.findElement(By.cssSelector("#place_order"));
	//	placeOrder.submit();
	//	Thread.sleep(3000);
	}

	//driver.quit();
}
