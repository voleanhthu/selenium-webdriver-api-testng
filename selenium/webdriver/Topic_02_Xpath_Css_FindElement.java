package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css_FindElement {
	 WebDriver driver;
	 String firstName = "Test";
	 String lastName = "Automation";
	 String password = "123456789";
	 String emailAddress = "";
  
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
//	  Access page
	  driver.get("http://live.demoguru99.com/");
  }
  
  @Test
  public void TC01_Login_Empty_Email_And_Password() { 
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[contains(@class,'account-cart')]//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
	  
//	  Send empty string to email and password
	  driver.findElement(By.id("email")).sendKeys("");
	  driver.findElement(By.id("pass")).sendKeys("");
	  
//	  Click Login
	  driver.findElement(By.name("send")).click();
	  
//	  Verify error message display on 2 fields
	  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
			  "This is a required field.");
	  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
			  "This is a required field.");
  }
  
  @Test
  public void TC02_Login_Invalid_Email() {
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[contains(@class,'account-cart')]//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
	  
//	  Send invalid email and valid password
	  driver.findElement(By.id("email")).sendKeys("123456@123.456.67");
	  driver.findElement(By.id("pass")).sendKeys("123456");
	  
//	  Click Login
	  driver.findElement(By.name("send")).click();
	  
//	  Verify error message display
	  Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),
			  "Please enter a valid email address. For example johndoe@domain.com.");
  }
  
  @Test
  public void TC03_Login_Password_Less_Than_6Characters() {
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[contains(@class,'account-cart')]//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
	  
//	  Send valid email and invalid password (less than 6 characters)
	  driver.findElement(By.id("email")).sendKeys("athu983.at49@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123");
	  
//	  Click Login
	  driver.findElement(By.name("send")).click();
	  
//	  Verify error message display
	  Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),
			  "Please enter 6 or more characters without leading or trailing spaces.");
  }
  
  @Test
  public void TC04_Login_Incorrect_Password() {
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[contains(@class,'account-cart')]//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
	  
//	  Send correct email and incorrect password
	  driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123123123123123");
	  
//	  Click Login
	  driver.findElement(By.name("send")).click();
	  
//	  Verify error message display
//	  Assert.assertNotNull(driver.findElement(By.xpath("//span[text()='Invalid login or password.']'")));
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
  }
  
  @Test
  public void TC04_Login_Incorrect_Email() {
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[contains(@class,'account-cart')]//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
	  
//	  Send incorrect email and correct password
	  driver.findElement(By.id("email")).sendKeys("automation1@gmail.com");
	  driver.findElement(By.id("pass")).sendKeys("123456");
	  
//	  Click Login
	  driver.findElement(By.name("send")).click();
	  
//	  Verify error message display
//	  Assert.assertNotNull(driver.findElement(By.xpath("//span[text()='Invalid login or password.']'")));
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
  }
  
  private String getRandomEmail() {
	  Random random = new Random();
	  return "automationtestting"+random.nextInt(99999)+"@gmail.com";
  }
  
  @Test
  public void TC05_Create_New_Account() {
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
//	  Click on button Create An Account
	  driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	  
//	  Input First name
	  driver.findElement(By.id("firstname")).sendKeys(firstName);
	  
//	  Input Last name
	  driver.findElement(By.id("lastname")).sendKeys(lastName);
	  
//	  Input Email Address
	  emailAddress = getRandomEmail();
	  driver.findElement(By.id("email_address")).sendKeys(emailAddress);
	  
//	  Input Password
	  driver.findElement(By.id("password")).sendKeys(password);
	  
//	  Input Confirm Password
	  driver.findElement(By.id("confirmation")).sendKeys(password);
	  
//	  Click on Register button
	  driver.findElement(By.xpath("//button[@title='Register']")).click();
	  
//	  Verify the successful message
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
	  
//	  Verify new information on My Dashboard
	  String information = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	  
	  Assert.assertTrue(information.contains(firstName)&&information.contains(lastName));
	  
//	  CLick on Account on the top of page
	  driver.findElement(By.xpath("//div[contains(@class,'account-cart')]//span[text()='Account']")).click();
	  
//	  Click on Log Out
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	  
//	  Check that we come back Home page
	  Assert.assertTrue(driver.findElement(By.cssSelector("img[src$='logo.png']")).isDisplayed());
  }
  
  @Test
  public void TC06_LogIn_With_New_Email_And_Pasword() {
//	  Click on link "My Account" to access Log in page
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  
//	  Input email and password
	  driver.findElement(By.cssSelector("input[name='login[username]")).sendKeys(emailAddress);
	  driver.findElement(By.id("pass")).sendKeys(password);
	  
//	  Click on LogIn
	  driver.findElement(By.cssSelector("button[name='send")).click();
	  
//	  Verify displayed information
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h1[text()='My Dashboard']")).isDisplayed());
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, "+firstName+" "+lastName+"!");

	  String information = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
	  System.out.println("INFOR..."+information);
	  Assert.assertTrue(information.contains(firstName)&&information.contains(lastName));
	  
//	  String email = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p/br")).getText();
//	  
//	  Assert.assertTrue(information.contains(firstName)&&information.contains(lastName));

  }
  
 

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
