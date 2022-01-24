package assignment;
import org.testng.annotations.*;
import pom_pages.*;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.ExtentTest;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment extends Webdriver {
	SoftAssert assertion = new SoftAssert();

	@Test(priority=1)
	public void success() throws InterruptedException {
		ExtentTest test = extent.createTest("TestCase 1 Success Message");
		//SoftAssert assertion = new SoftAssert();
		String Title="My Store";
		if(driver.getTitle()!=null && driver.getTitle().equals(Title)) {
			System.out.println("Web page is opend");
			test.pass("Navigated to website");
		
			pom_pages.Home.ContactUs(driver).click();
			test.pass("CLicked on ContactUs");
			
			driver.manage().window().maximize();
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("scroll(0,500)");
		    
		    pom_pages.ContactUs_page.SubjectHeading(driver).click();		    
		    pom_pages.ContactUs_page.SubjectHeadingSelection(driver,1);
		    
		    pom_pages.ContactUs_page.Email(driver).sendKeys("abc@gmail.com");
		    
		    pom_pages.ContactUs_page.Message(driver).sendKeys("Testing");
		    
		    pom_pages.ContactUs_page.Submit(driver).click();
		    test.pass("Filled the details");
		   
		    String SuccessMessage = pom_pages.ContactUs_page.SuccessMessage(driver).getText();

		    if(SuccessMessage!=null && SuccessMessage.equals("Your message has been successfully sent to our team.")){
		    	System.out.println("Message sent successfully and Test Case 1 passed");
		    	test.pass("Email sent successfully");
		    }
		    else {
		    	assertion.assertEquals(SuccessMessage, "Your message has been successfully sent to our team.");
		    	test.pass("Mail did not send succesfully");
		    }
		    Thread.sleep(3000);
		    //driver.quit();
		    
		}
		else {
			assertion.assertEquals(driver.getTitle(), Title);
			test.fail("Website opened is not correct");			
		}	
		driver.quit();
		assertion.assertAll();
	}
	
	
	@Test(priority=2)
	public void fail() throws InterruptedException {
	    ExtentTest test2 = extent.createTest("TestCase 2 Error Message");
	    driver();
		//SoftAssert assertion = new SoftAssert();
		String Title="My Store";
		if(driver.getTitle()!=null && driver.getTitle().equals(Title)) {
			System.out.println("Web page is opend");
			test2.pass("Navigated to website");
			
			pom_pages.Home.ContactUs(driver).click();
			test2.pass("CLicked on ContactUs");
			
			driver.manage().window().maximize();
		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("scroll(0,500)");
		    
		    pom_pages.ContactUs_page.SubjectHeading(driver).click();		    
		    pom_pages.ContactUs_page.SubjectHeadingSelection(driver,1);
		    
		    pom_pages.ContactUs_page.Email(driver).sendKeys("abc@gmail");
		    
		    pom_pages.ContactUs_page.Message(driver).sendKeys("Testing");
		    
		    pom_pages.ContactUs_page.Submit(driver).click();
		    test2.pass("Filled the details");
		    
		    String InvalidMessage = pom_pages.ContactUs_page.InvalidMessage(driver).getText();
		    if(InvalidMessage!=null && InvalidMessage.equals("Invalid email address.")){
		    	System.out.println("Got error message and Test Case 2 passed");
		    	test2.pass("Mail did not send succesfully");
		    }
		    else {
		    	assertion.assertEquals(InvalidMessage, "Invalid email address.");
		    	test2.fail("Error message not matching");
		    }
		    Thread.sleep(3000);
		    //driver.quit();
		}
		else {
			assertion.assertEquals(driver.getTitle(), Title);
			test2.fail("Website opened is not correct");			
		}
		driver.quit();
		assertion.assertAll();
	}
	
	
	@Test(priority=3)
	public void AddToCart() throws InterruptedException {
		ExtentTest test3 = extent.createTest("TestCase 3 Add to cart");
		driver();
		//SoftAssert assertion = new SoftAssert();
		String Title="My Store";
		if(driver.getTitle()!=null && driver.getTitle().equals(Title)) {
		driver.manage().window().maximize();
		
		WebElement dresses = pom_pages.Home.DressesLink(driver);
		Actions action = new Actions(driver);
		action.moveToElement(dresses).perform();
		
		pom_pages.Home.CasualDressesLink(driver).click();
		Thread.sleep(3000);
		test3.pass("Clicked on Dress Image");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("scroll(0,700)");
	    
	    WebElement dressImg = pom_pages.CasualDresses.DressImage(driver);
	    action.moveToElement(dressImg).perform();
	    
	    String productName = pom_pages.CasualDresses.ProductName(driver).getText();
	    
	    String productPrice = pom_pages.CasualDresses.ProductPrice(driver).getText();
	    System.out.println("ProductPrice"+" "+productPrice);
	    
	    pom_pages.CasualDresses.AddToCart(driver).click();
	    Thread.sleep(5000);
	    test3.pass("Added to Cart");
	    
	    pom_pages.PopUpAddToCart.CloseWindow(driver).click();
	    test3.pass("Closed Add to cart Pop - up window");
	    
	    js.executeScript("scroll(0,-500)");
	    
	    pom_pages.Home.CompanyLogo(driver).click();
	    test3.pass("Redirecting to Home page");
	    
	    pom_pages.Home.Cart(driver).click();
	    js.executeScript("scroll(0,400)");
	    
	    String cartProductName = pom_pages.Cart.CartProductName(driver).getText();
	    
	    String cartProductPrice = pom_pages.Cart.CartProductPrice(driver).getText();
	     
	    System.out.println("Cart Product price"+" "+cartProductPrice);
	    
	    if(cartProductName.equals(productName) && cartProductPrice.equals(productPrice)) {
	    	System.out.println("Product matched");
	    	test3.pass("Product matched");
	    }
	    Thread.sleep(3000);
	    
		}
		else {
			assertion.assertEquals(driver.getTitle(), Title);
			test3.fail("Website opened is not correct");
		}
		driver.quit();
		assertion.assertAll();
	}
	
	@AfterSuite
	public void flush() {
		//assertion.assertAll();
		extent.flush();
	}
}
