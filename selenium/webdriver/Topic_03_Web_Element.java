package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Web_Element {

	WebDriver driver;
 
 @BeforeClass
 public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
//	  Access page
	  driver.get("http://live.demoguru99.com");
 }
 
 @Test
 public void TC01_Verify_CyrrentURL() { 
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
//	  Verify the url of login page
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	  
//	  Click on button Create An Account
	  WebElement a = driver.findElement(By.xpath("//a[@title='Create an Account']"));
	  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", a);
	  
//	  Verify the url of create page
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	
 }
 
 @Test
 public void TC02_Verify_Title() {
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
//	  Verify the title of login page
	  Assert.assertEquals(driver.getTitle(), "Customer Login");
	  
//	  Click on button Create An Account
	  WebElement a = driver.findElement(By.xpath("//a[@title='Create an Account']"));
	  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", a);
	  
//	  Verify the title of create page
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	  
 }
 
 @Test
 public void TC03_Navigation_Back_Or_Forward() {
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
//	  Click on button Create An Account
	  WebElement a = driver.findElement(By.xpath("//a[@title='Create an Account']"));
	  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", a);
	  
//	  Verify the url of create page
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	
//	  Back previous page
	  driver.navigate().back();
	  
//	  Verify the url of login page
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	 
//	  Forward to register page
	  driver.navigate().forward();
	  
//	  Verify the title of create page
	  Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

 }
 
 @Test
 public void TC04_Get_Page_Source() {
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
//	  Verify Login Page contain text
	  Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
	  
//	  Click on button Create An Account
	  WebElement a = driver.findElement(By.xpath("//a[@title='Create an Account']"));
	  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", a);
	  
//	  Verify create Page contain text
	  Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

 }

 @AfterClass
 public void afterClass() {
	  driver.quit();
 }

}
