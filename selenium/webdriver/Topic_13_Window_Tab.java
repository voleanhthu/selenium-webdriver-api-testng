package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import org.apache.commons.el.GreaterThanOperator;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SwitchToWindow;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jdk.nashorn.internal.scripts.JS;

import static org.hamcrest.Matchers.*;

public class Topic_13_Window_Tab {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor js;

	@BeforeClass
	public void beforeClass() {
//		driver = new  FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 20);
		js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		  driver.manage().window().maximize();

	}

	public void TC_01_Only_2_() {
//		Using Firefox driver
		driver.get("https://automationfc.github.io/basic-form/");

//		Each window or tab will have ID
//		GUID - Global Unique Identifier

		String parentWindowID = driver.getWindowHandle();

		System.out.println("Active window/tab: " + parentWindowID);

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		switchToWindowByID(parentWindowID);

		Assert.assertTrue(driver.findElement(By.id("hplogo")).isDisplayed());

	}

	public void TC_02_Greater_Than_2_Window_Tab() {
//		Using Firefox driver
		driver.get("https://automationfc.github.io/basic-form/");
		String parentWindowID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		sleepInSeconds(2);

		switchToWindowByTitle("Google");

		Assert.assertTrue(driver.findElement(By.id("hplogo")).isDisplayed());

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		sleepInSeconds(2);

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSeconds(2);

		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

		Assert.assertTrue(driver.findElement(By.xpath("//button[@name='login']")).isDisplayed());

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		sleepInSeconds(2);

		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSeconds(2);

		switchToWindowByTitle("LAZADA Vietnam™ - Mua Hàng Trực Tuyến Giá Tốt");

		Assert.assertTrue(driver.findElement(By.id("q")).isDisplayed());

//		Close all tabs/Windows except for Parent window/tab
		closeAllWindowsExceptForParent(parentWindowID);

	}

	public void TC_03_Greater_Than_2_Window_Tab() {
//		Using Firefox driver
		driver.get("https://automationfc.github.io/basic-form/");
		String parentWindowID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		sleepInSeconds(2);

		switchToWindowByTitle("Google");

		Assert.assertTrue(driver.findElement(By.id("hplogo")).isDisplayed());

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		sleepInSeconds(2);

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSeconds(2);

		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

		Assert.assertTrue(driver.findElement(By.xpath("//button[@name='login']")).isDisplayed());

		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		sleepInSeconds(2);

		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSeconds(2);

		switchToWindowByTitle("LAZADA Vietnam™ - Mua Hàng Trực Tuyến Giá Tốt");

		Assert.assertTrue(driver.findElement(By.id("q")).isDisplayed());

//		Close all tabs/Windows except for Parent window/tab
		closeAllWindowsExceptForParent(parentWindowID);

		Assert.assertTrue(driver.getTitle().equals("SELENIUM WEBDRIVER FORM DEMO"));
	}

	public void TC_04_Greater_Than_2_Window_Tab() {
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		String parentWindowID = driver.getWindowHandle();

		driver.findElement(
				By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The product Sony Xperia has been added to comparison list.");

		driver.findElement(
				By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(
				By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		sleepInSeconds(3);
		
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		
		Assert.assertEquals(driver.findElements(By.xpath("//h2[@class='product-name']/a")).size(), 2);

		closeAllWindowsExceptForParent(parentWindowID);
		
		sleepInSeconds(3);
		
		driver.findElement(By.id("search")).sendKeys("Sony Xperia");
		
		driver.findElement(By.xpath("//button[@title='Search']")).click();
		
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		
		driver.switchTo().alert().accept();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The comparison list was cleared.");
		
	}
	
	@Test
	public void TC_05_Greater_Than_2_Window_Tab() {
		driver.get("https://kyna.vn/");
		
		
		String parentID = driver.getWindowHandle();
		
		scrollToBottomPage();
		
		driver.findElement(By.xpath("//ul[@class='bottom']/following-sibling::div//img[@alt='facebook']")).click();
		
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		sleepInSeconds(2);
		//
//		Assert.assertEquals(driver.findElement(By.id("pageTitle")).getText(),"Kyna.vn - Trang chủ | Facebook");
		Assert.assertTrue(driver.findElement(By.xpath("//u[text()='Facebook']")).isDisplayed());
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
		driver.switchTo().window(parentID);
		
		sleepInSeconds(2);
		
		driver.findElement(By.xpath("//ul[@class='bottom']/following-sibling::div//img[@alt='youtube']")).click();
		
		switchToWindowByTitle("Kyna.vn - YouTube");
		sleepInSeconds(2);
//		Assert.assertEquals(driver.findElement(By.xpath("//title")).getText(),"Kyna.vn - YouTube");
		
		Assert.assertTrue(driver.findElement(By.id("logo-icon-container")).isDisplayed());
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.youtube.com/user/kynavn");
		driver.switchTo().window(parentID);
		
		sleepInSeconds(2);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));
		
		driver.findElement(By.xpath("//a[@title='Kyna.vn']")).click();
		
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
//		Assert.assertEquals(driver.findElement(By.xpath("//title")).getText(),"Kyna.vn - Trang chủ | Facebook");
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".fb_logo")).isDisplayed());
		
		Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/kyna.vn");
		driver.switchTo().window(parentID);
		
	}

	private void closeAllWindowsExceptForParent(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String ID : allWindowIDs) {
			if (!ID.equals(parentID)) {
				driver.switchTo().window(ID);
				driver.close();
			}

			if (driver.getWindowHandles().size() == 1) {
				driver.switchTo().window(parentID);
				break;
			}
		}
	}

//	Apply for more than 2 windows/tabs
	private void switchToWindowByTitle(String expectedTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String ID : allWindowIDs) {
			driver.switchTo().window(ID);

			String actualTitle = driver.getTitle();
			System.out.println("TITLe: "+actualTitle);

			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

//	Apply for 2 windows/tabs
	private void switchToWindowByID(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String ID : allWindowIDs) {
			if (!ID.equals(parentID)) {
				driver.switchTo().window(ID);
				break;
			}
		}

	}

	private void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {

		}
	}
	
	private void scrollToBottomPage() {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	private void clickToElementByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		js.executeScript("arguments[0].click()", element);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
