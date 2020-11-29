package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//Remove attribute
public class Topic_14_JavaScript_Executor_PartII_RemoveAttribute {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor js;

	String emailAddress;
	String userID = "mngr294086";
	String password = "zazubAj";
	String name, dateOfBirth, address, city, state, pin, mobileNumber, email, passwordNewCustomer;
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
		System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 20);
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");

		emailAddress = "automation" + getRandomEmail();
		name = "Windy Vo";
		dateOfBirth = "1991-02-19";
		address = "6 Lawn Alley";
		city = "Trenton";
		state = "New Jersey";
		pin = "086089";
		mobileNumber = "6095535050";

	}

	public void TC01_Create_New_Account() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		System.out.println("USER ID:" + userID + "  Pass:" + password);
	}

	
	public void TC02_Login() {
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
		
		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']")).click();
		
//		Input all data and remove attribute "type" for date picker
		removeAttributeInDOM(driver, "//input[@id='dob']", "type");
		sleepInSecond(5);
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
	
	}

	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}


	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
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
		return random.nextInt(99999) + "@gmail.com";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
