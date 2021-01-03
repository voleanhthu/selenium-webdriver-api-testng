package webdriver;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;



public class Topic_16_Wait_PartV {
	WebDriver driver;
	FluentWait<WebElement> fluentWaitElement;
	FluentWait<WebDriver> fluentWaitDriver;
	int timeoutInSeconds = 15;
	int intervalMilliseconds = 300;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
	}


	public void TC_01_FluentWait() {
		
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countDownTime = driver.findElement(By.id("javascript_countdown_time"));
		
		fluentWaitElement = new FluentWait<WebElement>(countDownTime);
		fluentWaitElement.withTimeout(Duration.ofSeconds(5))
		.pollingEvery(Duration.ofMillis(300))
		.ignoring(NoSuchElementException.class);
		
		fluentWaitElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement countDownTime) {
				// TODO Auto-generated method stub
				String text = countDownTime.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
		
	}
	
	
	public void TC_02_FluentWait() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		waitForElementAndClick(By.xpath("//div[@id='start']/button"));
		
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4")));
		
		Assert.assertEquals(getElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
		
		
	}
	
	@Test
	public void TC_03_FluentWait() {
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
		
		waitForElementAndClick(By.xpath("//div[@id='start']/button"));
		
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@id='finish']/h4")));
		
		Assert.assertEquals(getElement(By.xpath("//div[@id='finish']/h4")).getText(), "Hello World!");
		
		
	}
	
	public WebElement getElement(By locator) {
		fluentWaitDriver = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeoutInSeconds))
				.pollingEvery(Duration.ofMillis(intervalMilliseconds))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = fluentWaitDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	public void waitForElementAndClick(By locator) {
		getElement(locator).click();
	}
	
	public boolean isElementDisplayed(By locator) {
		WebElement element = getElement(locator);
		fluentWaitElement = new FluentWait<WebElement>(element)
				.withTimeout(Duration.ofSeconds(timeoutInSeconds))
				.pollingEvery(Duration.ofMillis(intervalMilliseconds))
				.ignoring(NoSuchElementException.class);
		
		return fluentWaitElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				return element.isDisplayed();
			}
		});
		
	}

	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	

	

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
