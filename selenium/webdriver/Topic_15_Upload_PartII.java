package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;



public class Topic_15_Upload_PartII {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	String projetLocation = System.getProperty("user.dir");
	
	String hanoiName = "Ha Noi.jpeg";
	String hanoiPath = projetLocation+"/image/"+hanoiName;
	String danangName = "Da Nang.jpg";
	String danangPath = projetLocation+"/image/"+danangName;
	String hochiminhName = "Ho Chi Minh.jpg";
	String hochiminhPath = projetLocation+"/image/"+hochiminhName;
	
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver");
		driver = new ChromeDriver();
		
		explicitWait = new WebDriverWait(driver,200);
		

		
//		System.setProperty("webdriver.edge.driver", "./browserDrivers/msedgedriver");
//		driver = new EdgeDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		
	}
	
	
	@Test
	public void TC_06_Sikuli() throws FindFailed {
		driver.get("http://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".fileinput-button")).click();
		
		sleepInSeconds(10);
		System.out.println("PATH..."+projetLocation + "/sikuliImages/search.png");
		
		Pattern fileNameInput = new Pattern(projetLocation + "/sikuliImages/search.png");
		Pattern openButton = new Pattern(projetLocation + "/sikuliImages/openButton.png");

		Screen screen = new Screen();
		screen.click(fileNameInput);
		screen.type(fileNameInput, danangName);
		screen.click(openButton);

		// Verify file loaded success
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + danangName + "']")).isDisplayed());
	}
	
	
	private void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {

		}
	}
	
	public boolean isImageLoadedSuccess(String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(locator));
		return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof "
				+ "arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
