package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_PartI {
	WebDriver driver;
	Actions action;
	
	@BeforeClass
	public void beforeClass() {
		  driver = new FirefoxDriver();
		  
		  action = new Actions(driver);
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Hover_Mouse() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		
		Assert.assertEquals(driver.findElement(By.xpath(
				"//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
		
	
	}
	
	@Test
	public void TC_02_Hover_Mouse() {
		driver.get("http://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
		
		
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
	}
	
	@Test
	public void TC_03_Hover_Mouse() {
		driver.get("https://hn.telio.vn/");
		
		action.moveToElement(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//span[text()='Đồ ăn vặt']"))).perform();
		sleepInSeconds(2);
		
		System.out.println("Size:..."+(driver.findElements(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='Hoa quả sấy']"))).size());
		
		driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='Hoa quả sấy']")).click();
		
		sleepInSeconds(1);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Hoa quả sấy']")).isDisplayed());
	}
	
	@Test
	public void TC_04_Click_And_Hold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> listOfElements = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("Total elements: "+listOfElements.size());
		
		action.clickAndHold(listOfElements.get(0)).moveToElement(listOfElements.get(3)).release().perform();
		
		Assert.assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).size(), 4);
	}
	
	@Test
	public void TC_05_Click_And_Hold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> listOfElements = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("Total elements: "+listOfElements.size());
		
		action.keyDown(Keys.CONTROL).perform();
		
		action.click(listOfElements.get(0))
		.click(listOfElements.get(2))
		.click(listOfElements.get(5))
		.click(listOfElements.get(10))
		.perform();
		
		action.keyUp(Keys.CONTROL).perform();
		
		Assert.assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).size(), 4);
	}
	
	@Test
	public void TC_06_DoubleCLick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='demo' and text()='Hello Automation Guys!']")).isDisplayed());
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
