package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_05_Parameter {
	WebDriver driver;

	@Parameters({"browser","url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver");
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver");
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "./browserDrivers/msedgedriver");
			driver = new EdgeDriver();
		}else {
			throw new RuntimeException("Please input browser name again");
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);

	}

	@Test(dataProvider = "dp")
	public void createAccount(String email, String password) {
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']")).click();
		
		sleepInSeconds(3);
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys("Michael");
		driver.findElement(By.id("lastname")).sendKeys("Leeee");
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();

		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();

		driver.findElement(By.xpath("//a[text()='Log Out']")).click();

	}

	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { getRandomEmail(), "4567894" } };

	}

	private String getRandomEmail() {
		Random random = new Random();
		return "autoamtion1" + random.nextInt(999) + "@gmail.com";
	}
	
	private void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {

		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
