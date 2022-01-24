package assignment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Webdriver {
	public WebDriver driver;
	String baseurl = "http://automationpractice.com/";
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	
	@BeforeSuite
	public void extent() {
		
		htmlReporter = new ExtentHtmlReporter("extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	

	@BeforeTest
	public void driver() {
			
		System.setProperty("webdriver.chrome.driver",
	                "C:\\Users\\sravan.kotha\\Desktop\\QA_Automation\\QA_Automation\\driver\\chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.get(baseurl);
		}

}
