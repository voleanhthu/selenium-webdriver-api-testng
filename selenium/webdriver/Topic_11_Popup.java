package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	
	@BeforeClass
	public void beforeClass() {
		  driver = new FirefoxDriver();
		
		  explicitWait = new WebDriverWait(driver, 20);
		  jsExecutor = (JavascriptExecutor)driver;
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		  driver.manage().window().maximize();
		  
	}
	
//	@Test
//	public void TC_01_Fixed_Popup() {
//		driver.get("https://www.zingpoll.com/");
//		
//		driver.findElement(By.id("Loginform")).click();
//		
//		By formBy = By.xpath("//form[@id='loginForm']");
////		In case network is so bad, using expicitWait to handle this time
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(formBy));
//		Assert.assertTrue(driver.findElement(formBy).isDisplayed());
//		
//		driver.findElement(By.xpath("//div[@id='Login']//button[@class='close']")).click();
//		
////		Wait until element is invisible
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(formBy));
//		Assert.assertFalse(driver.findElement(formBy).isDisplayed());
//	}
//	
//	@Test
//	public void TC_02_Fixed_Popup() {
//		driver.get("https://bni.vn/");
//		
//		By formBy = By.id("sgpb-popup-dialog-main-div");
////		In case network is so bad, using expicitWait to handle this time
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(formBy));
//		Assert.assertTrue(driver.findElement(formBy).isDisplayed());
//		
//		driver.findElement(By.xpath("//img[@alt='Close']")).click();
//		
////		Wait until element is invisible
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(formBy));
//		Assert.assertFalse(driver.findElement(formBy).isDisplayed());
//	}
//	
//	@Test
//	public void TC_02_Fixed_Popup2() {
//		driver.get("https://bni.vn/");
//		
//		By formBy = By.id("sgpb-popup-dialog-main-div");
////		In case network is so bad, using expicitWait to handle this time
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(formBy));
//		Assert.assertTrue(driver.findElement(formBy).isDisplayed());
//		
//		driver.findElement(By.xpath("//input[@value='JOIN WITH US']")).click();
//		
//		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']")));
//		driver.findElement(By.xpath("//img[@alt='Close']")).click();
//		
////		Wait until element is invisible
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(formBy));
//		Assert.assertFalse(driver.findElement(formBy).isDisplayed());
//	}
	
	@Test
	public void TC_03_Random_Popup_In_DOM() {
		driver.get("https://blog.testproject.io/");
		
//		If the popup is displayed, close it and move to next step
//		If the popup is not displayed, move to next step.
		
		sleepInSeconds(7);
		
		By closeBy = By.id("close-mailch");
		
		if(driver.findElement(By.xpath("//div[@class='mailch-wrap rocket-lazyload']")).isDisplayed()) {
			
			System.out.println("Heee...");
			sleepInSeconds(10);
			explicitWait.until(ExpectedConditions.elementToBeClickable(closeBy));
			driver.findElement(closeBy).click();
			
			sleepInSeconds(2);
			
		}
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section//input[@class='search-field']")));
		driver.findElement(By.xpath("//section//input[@class='search-field']")).sendKeys("Selenium");
		
		
//		Click to search icon after sending search text
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section//span[@class='glass']")));
		
		scrollToElement(driver.findElement(By.xpath("//h1[text()='Test Automation Blog']")));
		
		driver.findElement(By.xpath("//section//span[@class='glass']")).click();
		sleepInSeconds(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//section[@id='primary']//span[text()='\"Selenium\":']")).isDisplayed());
		
		
		List<WebElement> listofTitleResult = driver.findElements(By.xpath("//div[@class='post-on-archive-page']//h3[@class='post-title']/a"));
		for(WebElement title:listofTitleResult) {
			Assert.assertTrue(title.getText().contains("Selenium"));
		}
				
		//a[@class='next page-numbers']
		
		
		
	
		
	}
	
	
//	@Test
//	public void TC_04_Random_Popup_Not_In_DOM() {
//		driver.get("https://shopee.vn/");
//		
//		sleepInSeconds(5);
//		List<WebElement> listOfImageBanner = driver.findElements(By.xpath("//img[@alt='home_popup_banner']"));
//		
//		if(listOfImageBanner.size()>0 && listOfImageBanner.get(0).isDisplayed()) {
////			Close popup
//			System.out.println("Popup displays");
//			explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='shopee-popup__close-btn']"))).click();
//		}else {
//			System.out.println("Popup does not display");
//		}
//			
//		sleepInSeconds(2);
//		
//
//	}
	
	private void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		}catch(Exception e) {
			
		}
	}
	
	private void scrollToElement(WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	
	@AfterClass
	public void afterClass() {
		  driver.quit();
	}
	

}
