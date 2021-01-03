package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_04_DataProvider {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(dataProvider = "dp")
	public void createAccount(String email, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
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
		return new Object[][] { new Object[] { getRandomEmail(), "4567894" }, 
			{ getRandomEmail(), "456723" }};
		

	}

	private String getRandomEmail() {
		Random random = new Random();
		return "autoamtion1" + random.nextInt(999) + "@gmail.com";
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
