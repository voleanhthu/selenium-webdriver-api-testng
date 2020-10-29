package webdriver;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_TextBox_TextArea {
	WebDriver driver;
	String emailAddress, userID, password; 
	String name, dateOfBirth,address, city,state, pin, mobileNumber,email, passwordNewCustomer;
	String edtAddress, edtCity, edtState, edtPin, edtMobileNumber, edtEmail;
	String customerID;
	String currentUrl;
	
	By nameBy = By.name("name");
	By dateOfBirthBy = By.name("dob");
	By addressBy = By.name("addr");
	By cityBy = By.name("city");
	By stateBy = By.name("state");
	By pinBy = By.name("pinno");
	By mobileNumberBy = By.name("telephoneno");
	By emailby = By.name("emailid");
	By passwordBy = By.name("password");
	
	 
@BeforeClass
public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("http://demo.guru99.com/v4/");
	  
	  currentUrl = driver.getCurrentUrl();
	  
	  emailAddress = "automation"+generateEmailAddress();
	  name = "Windy Vo";
	  dateOfBirth = "1991-02-19";
	  address = "6 Lawn Alley";
	  city = "Trenton";
	  state ="New Jersey";
	  pin="086089";
	  mobileNumber="6095535050";
	  
	  edtEmail = "automation"+generateEmailAddress();
	  edtAddress = "9480 Melvin Drive";
	  edtCity = "Paterson";
	  edtState ="New Jersey1";
	  edtPin="986089";
	  edtMobileNumber="0095535050";
}

private String generateEmailAddress() {
	Random random = new Random();
	return random.nextInt(99999)+"@gmail.com";
}

@Test
public void TC01_Create_New_Account() { 
	driver.findElement(By.xpath("//a[text()='here']")).click();
	driver.findElement(By.name("emailid")).sendKeys(emailAddress);
	driver.findElement(By.name("btnLogin")).click();
	userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	 
}


@Test
public void TC02_Login() { 
	driver.get(currentUrl);
	driver.findElement(By.name("uid")).sendKeys(userID);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("btnLogin")).click();
	Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),"Welcome To Manager's Page of Guru99 Bank");
	 
}

@Test
public void TC03_Add_New_Customer() { 
	driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	driver.findElement(nameBy).sendKeys(name);
	driver.findElement(dateOfBirthBy).sendKeys(dateOfBirth);
	driver.findElement(addressBy).sendKeys(address);
	driver.findElement(cityBy).sendKeys(city);
	driver.findElement(stateBy).sendKeys(state);
	driver.findElement(pinBy).sendKeys(pin);
	driver.findElement(mobileNumberBy).sendKeys(mobileNumber);
	driver.findElement(emailby).sendKeys(emailAddress);
	driver.findElement(passwordBy).sendKeys(password);
	driver.findElement(By.name("sub")).click();
	
	Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Customer Registered Successfully!!!");
	
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
//	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),;
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirth);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobileNumber);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAddress);

	customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	System.out.println("Customer ID: "+customerID);
}

@Test
public void TC04_Edit_New_Customer() { 
	driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	driver.findElement(By.name("cusid")).sendKeys(customerID);
	driver.findElement(By.name("AccSubmit")).click();
	
	Assert.assertEquals(driver.findElement(nameBy).getAttribute("value"),name);
	Assert.assertEquals(driver.findElement(dateOfBirthBy).getAttribute("value"),dateOfBirth);
	
	Assert.assertEquals(driver.findElement(addressBy).getText(),address);
	Assert.assertEquals(driver.findElement(cityBy).getAttribute("value"),city);
	Assert.assertEquals(driver.findElement(stateBy).getAttribute("value"),state);
	Assert.assertEquals(driver.findElement(pinBy).getAttribute("value"),pin);
	Assert.assertEquals(driver.findElement(mobileNumberBy).getAttribute("value"),mobileNumber);
	Assert.assertEquals(driver.findElement(emailby).getAttribute("value"),emailAddress);
	
	
	driver.findElement(emailby).clear();
	driver.findElement(emailby).sendKeys(edtEmail);
	
	driver.findElement(addressBy).clear();
	driver.findElement(addressBy).sendKeys(edtAddress);
	
	
	driver.findElement(stateBy).clear();
	driver.findElement(stateBy).sendKeys(edtState);
	
	driver.findElement(cityBy).clear();
	driver.findElement(cityBy).sendKeys(edtCity);
	
	driver.findElement(pinBy).clear();
	driver.findElement(pinBy).sendKeys(edtPin);
	
	
	driver.findElement(mobileNumberBy).clear();
	driver.findElement(mobileNumberBy).sendKeys(edtMobileNumber);
	
	
	driver.findElement(By.name("sub")).click();
	
	Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Customer details updated Successfully!!!");
	
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(),customerID);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateOfBirth);
	
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),edtAddress);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),edtCity);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),edtState);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),edtPin);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),edtMobileNumber);
	Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),edtEmail);
	
}



@AfterClass
public void afterClass() {
	  driver.quit();
}


}
