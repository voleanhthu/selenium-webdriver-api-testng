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
	 WebElement password;
	 
//	 By
	 By email = By.id("mail");
	 By ageUnder18Xpath = By.id("under_18");
//			 By.xpath("//label[text()='Age:']/following-sibling::label[text()='Under 18']");
	 By education = By.id("edu");
 
 @BeforeClass
 public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
 }
 
 @Test
 public void TC01_Check_Displayed_Elements() { 
	  driver.get("https://automationfc.github.io/basic-form/index.html");
//	  Check email field
	  WebElement emailArea = driver.findElement(email);
	  checkDisplayEnableOrDisableOfElement(emailArea.isDisplayed(), "Displayed");
	  emailArea.sendKeys("AutomationTesting");
	  
//	  Check age - under 18 checkbox
	  WebElement ageUnder18 = driver.findElement(ageUnder18Xpath);
	  Assert.assertTrue(ageUnder18.isDisplayed());
	  ageUnder18.click();
	  
//	  Check education field
	  Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Education:']")).isDisplayed());
	  WebElement educationArea = driver.findElement(education);
	  checkDisplayEnableOrDisableOfElement(educationArea.isDisplayed(), "Displayed");
	  educationArea.sendKeys("AutomationTesting");
 }
 
 
 @Test
 public void TC02_Check_Enabled_Disabled_Elements() { 
	  driver.get("https://automationfc.github.io/basic-form/index.html");
//	  Check email field
	  checkDisplayEnableOrDisableOfElement(driver.findElement(email).isEnabled(),"Enabled");
	  
//	  Check age - under 18 checkbox
	  checkDisplayEnableOrDisableOfElement(driver.findElement(ageUnder18Xpath).isEnabled(),"Enabled");
	  
//	  Check education field
	  checkDisplayEnableOrDisableOfElement(driver.findElement(education).isEnabled(),"Enabled");
	  
//	  Check Job Role 01
	  checkDisplayEnableOrDisableOfElement(driver.findElements(
			  By.xpath("//label[text()='Job Role 01 - Single Dropdown:']/following-sibling::select"))
			  .get(0).isEnabled(),"Enabled");
	  
//	  Check Job Role 02
	  checkDisplayEnableOrDisableOfElement(
			  driver.findElement(By.xpath("//label[text()='Job Role 02 - Multiple Dropdown:']/following-sibling::select[@multiple='multiple']"))
			  .isEnabled(),"Enabled");
	  
//	  Check Interests (Development) checkbox
	  checkDisplayEnableOrDisableOfElement(driver.findElement(
			  By.id("development")).isEnabled(),"Enabled");
	  
//	  Check Slider 01
	  checkDisplayEnableOrDisableOfElement(
			  driver.findElements(By.xpath("//label[text()='Slider 01:']/following-sibling::input"))
			  .get(0).isEnabled(),"Enabled");
	  

	  
//	  Check Password
	  checkDisplayEnableOrDisableOfElement(!driver.findElement(
			  By.id("password")).isEnabled(),"Disabled");
	  
//	  Check Age - Radio button is disabled
	  checkDisplayEnableOrDisableOfElement(!driver.findElement(
			  By.id("radio-disabled")).isEnabled(),"Disabled");
	  
//	  Check Biography
	  checkDisplayEnableOrDisableOfElement(!driver.findElement(
			  By.xpath("//textarea[@name='user_bio']")).isEnabled(),"Disabled");
	
//	  Check Job Role 03
	  checkDisplayEnableOrDisableOfElement(!driver.findElement(
			  By.xpath("//label[text()='Job Role 03 - Disable Dropdown:']/following-sibling::select"))
			  .isEnabled(),"Disabled");
	  
//	  Check Interests is disabled
	  checkDisplayEnableOrDisableOfElement(!driver.findElement(
			  By.id("check-disbaled"))
			  .isEnabled(),"Disabled");
	  
	  
//	  Check Slider 02 disabled
	  checkDisplayEnableOrDisableOfElement(!driver.findElement(
			  By.xpath("//label[text()='Slider 02 (Disabled):']/following-sibling::input"))
			  .isEnabled(),"Disabled");
	
	  
 }
 
 @Test
 public void TC_03_Check_Selected_Elements() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
//	  select age - under 18 radiobutton
	  WebElement ageRadioButton = driver.findElement(ageUnder18Xpath);
	  ageRadioButton.click();
	  checkDisplayEnableOrDisableOfElement(ageRadioButton.isSelected(), "Selected");
	  
//	  select Language - Java
	  WebElement languageJava = driver.findElement(By.id("java"));
	  languageJava.click();
	  checkDisplayEnableOrDisableOfElement(languageJava.isSelected(), "Selected");
	  
//	  Unselect language java checkbox
	  languageJava.click();
	  checkDisplayEnableOrDisableOfElement(!languageJava.isSelected(), "UnSelected");
	  
 }
 
 @Test
 public void TC_04_Register_Function_At_MailChimp() throws InterruptedException {
	  driver.get("https://login.mailchimp.com/signup/");
	  
//	  Enter email and username
	  driver.findElement(By.id("email")).sendKeys("automationfc.vn@gmail.com");
	  driver.findElement(By.id("new_username")).sendKeys("Automation");
	  
//	  Enter password
	  
	  password = driver.findElement(By.id("new_password"));
	  password.sendKeys("1");
	  Assert.assertNotNull(driver.findElement(By.xpath("//li[contains(@class,'number-char completed')]")));
	  
	 
	  
	  password.sendKeys("a");
	  Assert.assertNotNull(driver.findElement(By.xpath("//li[contains(@class,'lowercase-char completed')]")));
	  
	  
	  password.sendKeys("A");
	  Assert.assertNotNull(driver.findElement(By.xpath("//li[contains(@class,'uppercase-char completed')]")));
	 
	  
	  password.sendKeys("$");
	  Assert.assertNotNull(driver.findElement(By.xpath("//li[contains(@class,'special-char completed')]")));
	  
	  
	  password.sendKeys("567890");
	  Assert.assertTrue(!driver.findElement(By.xpath("//li[contains(@class,'8-char')]")).isDisplayed());
	  
	  
	  WebElement signUpButton = driver.findElement(By.id("create-account"));
	  Assert.assertTrue(signUpButton.isEnabled());
	  
	  password.clear();
	  password.sendKeys("123");
	  signUpButton = driver.findElement(By.id("create-account"));
	  Assert.assertTrue(!signUpButton.isEnabled());
	  
	  WebElement checkBox = driver.findElement(By.xpath("//input[@name='marketing_newsletter']"));
	  Assert.assertTrue(!checkBox.isSelected());
	  checkBox.click();
	  checkBox = driver.findElement(By.xpath("//input[@name='marketing_newsletter']"));
	  Assert.assertTrue(checkBox.isSelected());
	  
 }
 
 
 
 private void checkDisplayEnableOrDisableOfElement(boolean flag, String title) {
	  Assert.assertTrue(flag);
	  System.out.println(checkIfElementIsDisplayed(flag,title));
 }
 
 private String checkIfElementIsDisplayed(boolean flag, String tag) {
	  if(flag) {
		  return "Element is "+tag;
	  }else {
		  return "Element is not "+tag;
	  }
 }

 @AfterClass
 public void afterClass() {
	  driver.quit();
 }

}
