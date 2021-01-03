package webdriver;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_16_Wait_PartIV {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
	}


	public void TC_01_FoundElement() {
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		System.out.println("START EXPLICIT...."+getDateTimeNow());
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		
		System.out.println("END EXPLICIT...."+getDateTimeNow());
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
		System.out.println("END IMPLICIT...."+getDateTimeNow());
		
	}
	

	public void TC_02_Not_FoundElement_Only_Implicit() {
		
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		System.out.println("START IMPLICIT...."+getDateTimeNow());
		
//		It will not be failed as we use try catch
		try {
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("END IMPLICIT...."+getDateTimeNow());
		
	}
	

	public void TC_03_Not_FoundElement_Implicit_LessThan_Explicit() {
		System.out.println("Implicit Less Than Explicit");
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		explicitWait = new WebDriverWait(driver, 5);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		
		System.out.println("START IMPLICIT...."+getDateTimeNow());
		

		try {
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		}catch(Exception e) {
//			e.printStackTrace();
		}
		System.out.println("END IMPLICIT...."+getDateTimeNow());
		
		
System.out.println("START EXPLICIT...."+getDateTimeNow());
		

		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
			
		}catch(Exception e) {
//			e.printStackTrace();
		}
		System.out.println("END EXPLICIT...."+getDateTimeNow());
		
	}
	
	

	public void TC_03_Not_FoundElement_Implicit_Equal_Explicit() {
		System.out.println("Implicit euqual Explicit");
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		explicitWait = new WebDriverWait(driver, 3);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		
		System.out.println("START IMPLICIT...."+getDateTimeNow());
		

		try {
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		}catch(Exception e) {
//			e.printStackTrace();
		}
		System.out.println("END IMPLICIT...."+getDateTimeNow());
		
		
System.out.println("START EXPLICIT...."+getDateTimeNow());
		

		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
			
		}catch(Exception e) {
//			e.printStackTrace();
		}
		System.out.println("END EXPLICIT...."+getDateTimeNow());
		
	}
	

	public void TC_03_Not_FoundElement_Implicit_GreaterThan_Explicit() {
		System.out.println("Implicit greater Than Explicit");
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		explicitWait = new WebDriverWait(driver, 3);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
		System.out.println("START IMPLICIT...."+getDateTimeNow());
		

		try {
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='emailAddress']")).isDisplayed());
		}catch(Exception e) {
//			e.printStackTrace();
		}
		System.out.println("END IMPLICIT...."+getDateTimeNow());
		
		
System.out.println("START EXPLICIT...."+getDateTimeNow());
		

		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
			
		}catch(Exception e) {
//			e.printStackTrace();
		}
		System.out.println("END EXPLICIT...."+getDateTimeNow());
		
	}
	
	@Test
	public void TC_04_Not_FoundElement_Only_Explicit_Param_By() {
		System.out.println("Implicit greater Than Explicit");
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		explicitWait = new WebDriverWait(driver, 3);
		
		
System.out.println("START EXPLICIT...."+getDateTimeNow());
		

		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='emailAddress']")));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("END EXPLICIT...."+getDateTimeNow());
		
	}
	
	@Test
	public void TC_04_Not_FoundElement_Only_Explicit_Param_Element() {
		System.out.println("Implicit greater Than Explicit");
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		explicitWait = new WebDriverWait(driver, 3);
		
		
System.out.println("START EXPLICIT...."+getDateTimeNow());
		

		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='emailAddress']"))));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("END EXPLICIT...."+getDateTimeNow());
		
	}
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	

	

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
