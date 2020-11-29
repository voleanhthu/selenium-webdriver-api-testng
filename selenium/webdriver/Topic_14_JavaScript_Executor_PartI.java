package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_JavaScript_Executor_PartI {
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

	
	public void TC_01_JavascriptExecutor() {
		navigateToUrlByJS(driver, "http://live.demoguru99.com/");
		
		Assert.assertEquals("live.demoguru99.com", executeForBrowser(driver, "return document.domain"));
		
		Assert.assertEquals("http://live.demoguru99.com/", executeForBrowser(driver, "return document.URL"));
		
		Assert.assertEquals("http://live.demoguru99.com/", executeForBrowser(driver, "return document.URL"));
		
		clickToElementByJS(driver, "//div[@id='header-nav']//a[text()='Mobile']");
		
		clickToElementByJS(driver, "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		Assert.assertTrue(areExpectedTextInInnerText(driver, "Samsung Galaxy was added to your shopping cart."));

		clickToElementByJS(driver, "//a[text()='Customer Service']");
		
		Assert.assertEquals(executeForBrowser(driver, "return document.title"), "Customer Service");
		
		
		scrollToElement(driver, "//input[@id='newsletter']");
		
		sendkeyToElementByJS(driver, "//input[@id='newsletter']", getRandomEmail());
		
		clickToElementByJS(driver, "//button[@title='Subscribe']");
		
		Assert.assertTrue(areExpectedTextInInnerText(driver, "Thank you for your subscription."));
		
		navigateToUrlByJS(driver, "http://demo.guru99.com/v4/");
		
		Assert.assertEquals("demo.guru99.com", executeForBrowser(driver, "return document.domain"));
	}
	

	public void TC_02_HTML5_ValidationMessage() {
		driver.get("https://automationfc.github.io/html5/index.html");
		
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='fname']"), "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='fname']")).sendKeys("Testing");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='pass']"), "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='em']"), "Please fill out this field.");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("132@435@3545");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='em']"), "Please enter an email address.");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//input[@id='em']")).clear();
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys("123@456");
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='em']"), "Please match the requested format.");
		
		driver.findElement(By.xpath("//input[@id='em']")).clear();
		driver.findElement(By.xpath("//input[@id='em']")).sendKeys(getRandomEmail());
		driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
		Assert.assertEquals(getElementValidationMessage(driver, "//select"), "Please select an item in the list.");
		
	}

	@Test
	public void TC_03_HTML5_ValidationMessage() {
		driver.get("https://login.ubuntu.com/");
		
		driver.findElement(By.xpath("//button[text()='Accept all and visit site']")).click();
		
		driver.findElement(By.xpath("//button[@name='continue']")).click();
		
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='id_email']"), "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='id_email']")).sendKeys("132@345@345");
		driver.findElement(By.xpath("//button[@name='continue']")).click();
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='id_email']"), "Please enter an email address.");
		
		driver.findElement(By.xpath("//input[@id='id_email']")).clear();
		driver.findElement(By.xpath("//input[@id='id_email']")).sendKeys(getRandomEmail());
		driver.findElement(By.xpath("//button[@name='continue']")).click();
		Assert.assertEquals(getElementValidationMessage(driver, "//input[@id='id_password']"), "Please fill out this field.");
		
	}
	
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}
	
	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
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

	public void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

	public WebElement getElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
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
