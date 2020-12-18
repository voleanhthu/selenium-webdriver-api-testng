package webdriver;

import java.util.List;
import java.util.Random;
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


public class Topic_16_Wait_PartII {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new  FirefoxDriver();
//		Apply only for findElement/finElements
		explicitWait = new WebDriverWait(driver, 3);
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}


	public void TC_06_ExplicitWait() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
//		Wait for date picker presence
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_RadCalendar1")));
		
//		Before selecting any day, the selected date box is different
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='16']/parent::td"))).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='16']/parent::td[@class='rcSelected']")));
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']/parent::div[not(@style='display:none;')]")));
		
		
		
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Wednesday, December 16, 2020");
		
		
		
		
	}
	
	@Test
	public void TC_07_ExplicitWait() {
		driver.get("https://gofile.io/?t=uploadFiles");
		
//		Wait for date picker presence
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_ContentPlaceholder1_RadCalendar1")));
		
//		Before selecting any day, the selected date box is different
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='16']/parent::td"))).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='16']/parent::td[@class='rcSelected']")));
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']/parent::div[not(@style='display:none;')]")));
		
		
		
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "Wednesday, December 16, 2020");
		
		
		
		
	}
	

	

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
