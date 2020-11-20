package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Alert {
	WebDriver driver;
	WebDriverWait webDriverWait;
	Alert alert;
	
	String userName = "admin";
	String password = "admin";
	
	By resultBy = By.xpath("//p[@id='result']");
	
	@BeforeClass
	public void beforeClass() {
		  driver = new FirefoxDriver();
		  webDriverWait = new WebDriverWait(driver, 10);
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
//		Wait until alert appear
		webDriverWait.until(ExpectedConditions.alertIsPresent());
		
//		Switch to alert
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(resultBy).getText(), "You clicked an alert successfully");
		
	}
	
	@Test
	public void TC_02_Dismiss_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
//		Wait until alert appear
		webDriverWait.until(ExpectedConditions.alertIsPresent());
		
//		Switch to alert
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		alert.dismiss();
		
		Assert.assertEquals(driver.findElement(resultBy).getText(), "You clicked: Cancel");
		
	}
	
	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
//		Wait until alert appear
		webDriverWait.until(ExpectedConditions.alertIsPresent());
		
//		Switch to alert
		alert = driver.switchTo().alert();
		
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		String helloMessage = "automationtesting";
		
		alert.sendKeys(helloMessage);
		
		alert.accept();
		
		Assert.assertEquals(driver.findElement(resultBy).getText(), "You entered: "+helloMessage);
		
	}
	
	@Test
	public void TC_04_Authentication_Alert() {
		driver.get("http://"+userName+":"+password+"@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com");
		
		String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		driver.get(getAuthenticationUrl(basicAuthenUrl, userName, password));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	private String getAuthenticationUrl(String oldUrl, String username, String password) {
		String[] urlValue = oldUrl.split("//");
		return urlValue[0]+"//"+username+":"+password+"@"+urlValue[1];
	}
	//AUTOIT
	
	@AfterClass
	public void afterClass() {
		  driver.quit();
	}
}
