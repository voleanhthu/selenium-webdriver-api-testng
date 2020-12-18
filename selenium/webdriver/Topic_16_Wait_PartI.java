package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_Wait_PartI {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new  FirefoxDriver();
//		Apply only for findElement/finElements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	public void TC_01_Find_Element() {
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
//		Return 0 matching
//		Every 0.5 s will find one time until timeout. Whenever element is found, it will pass
//		After timeout, tests failed, throw NoSuchElement exception
		driver.findElement(By.xpath("//input[@id='FirstName']"));
		
		
//		Return 1 element, find immediately
//		Don't need to wait timeout as element is in DOM
		if(driver.findElement(By.xpath("//input[@id='Email']")).isDisplayed()) {
			System.out.print("Yes");
		}else {
			System.out.print("No");
		}
		
		
		
//		Return n matching node
//		ONly use first element to use
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("aaa");
		
		
		
	}
	
	public void TC_02_Find_Elements() {
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
//		0 matching node
//	The same with findElement except for
//		After timeout, testcase doesnot fail, and return an empty list
		List<WebElement>list = driver.findElements(By.xpath("//input[@type='radio']"));
		System.out.println("SIZE:..."+list.size());

		
//		Return 1 element (List<WebElement), 1 matching node
		Assert.assertFalse(driver.findElements(By.xpath("//input[@id='Email']")).isEmpty());
		

		
//		Return n matching node
		List<WebElement> listElement = driver.findElements(By.xpath(""));
		System.out.println("SIZE:..."+listElement.size());
		for(WebElement e:listElement) {
			e.click();
		
		}
		
	}
	
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
