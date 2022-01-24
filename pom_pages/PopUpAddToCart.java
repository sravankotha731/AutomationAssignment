package pom_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PopUpAddToCart {
	private static WebElement element = null;
	
	public static WebElement CloseWindow(WebDriver driver) {
		element = driver.findElement(By.xpath("//span[@title='Close window']"));
		return element;
	}

}
