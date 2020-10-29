package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_DropDown {
	WebDriver driver;
	Select select;
	String firstName,lastName, dateOfBirth, monthOfBirth, yearOfBirth,email,password;
	List<WebElement> listOfAllOptions;
	
	By dateBy = By.name("DateOfBirthDay");
	By monthBy = By.name("DateOfBirthMonth");
	By yearBy = By.name("DateOfBirthYear");
	
	
	 
@BeforeClass
public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  
	  firstName = "Bill";
	  lastName = "Fage";
	  
	  dateOfBirth = "1";
	  monthOfBirth = "May";
	  yearOfBirth = "1980";
	  
	  email = generateEmailAddress();
	 
}

private String generateEmailAddress() {
	Random random = new Random();
	return random.nextInt(99999)+"@gmail.com";
}

@Test
public void TC01_Handle_HTML_DropDownList() { 
	driver.get("https://automationfc.github.io/basic-form/index.html");
	
	select = new Select(driver.findElement(By.id("job1")));
	Assert.assertTrue(!select.isMultiple());
	select.selectByVisibleText("Mobile Testing");
	Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
	
	select.selectByValue("manual");
	Assert.assertEquals(select.getFirstSelectedOption().getText(),"Manual Testing");
	
	select.selectByIndex(9);
	Assert.assertEquals(select.getFirstSelectedOption().getText(),"Functional UI Testing");
	
	List<WebElement> listOfOptions = driver.findElements(By.xpath("//select[@id='job1']/option"));
	Assert.assertEquals(listOfOptions.size(), 10);
	
	select = new Select(driver.findElement(By.id("job2")));
	Assert.assertTrue(select.isMultiple());
	
	select.selectByVisibleText("Automation");
	select.selectByVisibleText("Mobile");
	select.selectByVisibleText("Desktop");
	

	List<String> listOfExpectedOption = new ArrayList<>();
	listOfExpectedOption.add("Automation");
	listOfExpectedOption.add("Mobile");
	listOfExpectedOption.add("Desktop");
	
	List<WebElement> listSelectedOption = select.getAllSelectedOptions();
	List<String> listOfActualOption = new ArrayList<>();
	for(WebElement option:listSelectedOption) {
		listOfActualOption.add(option.getText());
	}
	
	Assert.assertEquals(listOfActualOption, listOfExpectedOption);
	
	select = new Select(driver.findElement(By.id("job2")));
	select.deselectAll();
	
	listSelectedOption = select.getAllSelectedOptions();
	Assert.assertTrue(listSelectedOption.size()==0);
}

@Test
public void TC02_Handle_HTML_DropDownList() { 
	driver.get("https://demo.nopcommerce.com/register");
	
	driver.findElement(By.className("ico-register")).click();
	driver.findElement(By.id("gender-male")).click();
	driver.findElement(By.id("FirstName")).sendKeys(firstName);
	driver.findElement(By.id("LastName")).sendKeys(lastName);
	
	select = new Select(driver.findElement(dateBy));
	select.selectByVisibleText(dateOfBirth);
	
	listOfAllOptions = driver.findElements(By.xpath("//select[@name='DateOfBirthDay']/option"));
	Assert.assertTrue(listOfAllOptions.size()==32);
	
	select = new Select(driver.findElement(monthBy));
	select.selectByVisibleText(monthOfBirth);
	
	listOfAllOptions = driver.findElements(By.xpath("//select[@name='DateOfBirthMonth']/option"));
	Assert.assertTrue(listOfAllOptions.size()==13);
	
	select = new Select(driver.findElement(yearBy));
	select.selectByVisibleText(yearOfBirth);
	
	listOfAllOptions = driver.findElements(By.xpath("//select[@name='DateOfBirthYear']/option"));
	Assert.assertTrue(listOfAllOptions.size()==112);
	
	driver.findElement(By.id("Email")).sendKeys(email);
	driver.findElement(By.id("Password")).sendKeys(password);
	driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
	driver.findElement(By.name("register-button")).click();
	
	
	Assert.assertEquals(driver.findElement(By.className("result")).getText(),"Your registration completed");
	
	driver.findElement(By.className("ico-account")).click();
	
	Assert.assertEquals(driver.findElement(dateBy).getText(), dateOfBirth);
	Assert.assertEquals(driver.findElement(monthBy).getText(), monthOfBirth);
	Assert.assertEquals(driver.findElement(yearBy).getText(), yearOfBirth);
	
	
}


@AfterClass
public void afterClass() {
	  driver.quit();
}


}
