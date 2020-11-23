package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.el.GreaterThanOperator;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
import static org.hamcrest.Matchers.*;

public class Topic_12_Frame_iFrame {
	WebDriver driver;
	
	
	@BeforeClass
	public void beforeClass() {
//		  driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver");
		driver = new ChromeDriver();
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		  driver.manage().window().maximize();
		  
	}
	
	
	public void TC_01_iFrame() {
//		Using Firefox driver
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Facebook Social Plugin')]")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Automation FC']")).getText(),"Automation FC");
		
//		1.9K likes
		String numberLikesElement = driver.findElement(By.xpath("//a[@title='Automation FC']/parent::div/following-sibling::div")).getText();
		
		int numberLike = Integer.parseInt(numberLikesElement.split(" ")[0].replace("K", "000").replace(".", ""));
		MatcherAssert.assertThat(numberLike, greaterThan(1901));
		
		driver.switchTo().defaultContent();
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='post-title']")).getText(),"[Training Online] – Fullstack Selenium WebDriver Framework in Java (Livestream)");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".exportFormTitle")).getText(), "KHÓA HỌC SELENIUM AUTOMATION TESTING");
	}
	
	@Test
	public void TC_02_iFrame() {
//		Using chrome driver
		driver.get("https://kyna.vn/");
		
		driver.switchTo().frame("cs_chat_iframe");
		
		Assert.assertTrue(driver.findElement(By.xpath("//textarea[@ng-model='chatMessage.content']")).isDisplayed());
		driver.findElement(By.xpath("//textarea[@ng-model='chatMessage.content']")).sendKeys("Testing");
		
		driver.findElement(By.xpath("//textarea[@ng-model='chatMessage.content']")).sendKeys(Keys.ENTER);
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//input[@ng-model='userInfo.username']")).isDisplayed());
		sleepInSeconds(3);
		
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//i[@class='far fa-search']")).click();
		
		
		driver.findElement(By.xpath("//input[@id='m-live-search-bar']")).sendKeys("Excel");
		driver.findElement(By.xpath("//input[@id='m-live-search-bar']")).sendKeys(Keys.ENTER);
		
		sleepInSeconds(3);
		
		List<WebElement> listOfCourseElement = driver.findElements(By.xpath("//div[@class='content']//h4"));
		
		for(WebElement courseName:listOfCourseElement) {
			Assert.assertTrue(courseName.getText().contains("Excel"));
		}
	}
	
	
	private void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		}catch(Exception e) {
			
		}
	}
	
	@AfterClass
	public void afterClass() {
		  driver.quit();
	}
	

}
