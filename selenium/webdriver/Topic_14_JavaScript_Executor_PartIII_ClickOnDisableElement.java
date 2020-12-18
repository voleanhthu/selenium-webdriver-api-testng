package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_JavaScript_Executor_PartIII_ClickOnDisableElement {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor js;

	@BeforeClass
	public void beforeClass() {
		driver = new  FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20);
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_JavascriptExecutor() {
		navigateToUrlByJS(driver, "http://live.demoguru99.com/");
		
		clickToElementByJS(driver, "//div[@id='header-account']//a[text()='My Account']");
		
		clickToElementByJS(driver, "//span[text()='Create an Account']");
		
		sendkeyToElementByJSandID(driver, "firstname", "Testing");
		
		sendkeyToElementByJSandID(driver, "middlename", "Middle");
		
		sendkeyToElementByJSandID(driver, "lastname", "Automation");
		
		sendkeyToElementByJSandID(driver, "email_address", getRandomEmail());
		
		sendkeyToElementByJSandID(driver, "password", "123456");
		
		sendkeyToElementByJSandID(driver, "confirmation", "123456");
		
		clickToElementByJS(driver, "//div[@class='buttons-set']//button[@type='submit']");
		
		Assert.assertTrue(areExpectedTextInInnerText(driver, "Thank you for registering with Main Website Store."));
		
		clickToElementByJS(driver, "//div[@id='header-account']//a[text()='Log Out']");
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[@class='logo']")).isDisplayed());
		
	}
	

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	
	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	
	public String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

	public WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}
	
	public WebElement getElementByID(WebDriver driver, String idLocator) {
		return driver.findElement(By.id(idLocator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}
	
	public void sendkeyToElementByJSandID(WebDriver driver, String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElementByID(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private String getRandomEmail() {
		 Random random = new Random();
		  return "automationtestting"+random.nextInt(99999)+"@gmail.com";
	  }
	  

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
