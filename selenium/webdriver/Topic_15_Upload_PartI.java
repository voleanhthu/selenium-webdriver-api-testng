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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;



public class Topic_15_Upload_PartI {
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
		System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver");
//		driver = new ChromeDriver();
		
		explicitWait = new WebDriverWait(driver,200);
		

		
//		System.setProperty("webdriver.edge.driver", "./browserDrivers/msedgedriver");
//		driver = new EdgeDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		
	}
	
	
	public void TC_01_SendKeys() {
		driver.get("https://gofile.io/uploadFiles");
	
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hanoiPath);
	}
	
	
	public void TC_02_SendKeys_One_File_Per_Time() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hochiminhPath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(danangPath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hanoiPath);
		
//		Verify file loaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+hochiminhName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+hanoiName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+danangName+"']")).isDisplayed());
		
//		Click start button to upload each file
		List<WebElement> listOfStartButton = driver.findElements(By.cssSelector("table .start"));
		for(WebElement startButton :listOfStartButton) {
			startButton.click();
			sleepInSeconds(1);
		}
		
//		Verify file uploaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+hochiminhName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+hanoiName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+danangName+"']")).isDisplayed());
		
	}
	

	public void TC_02_SendKeys_Multiple_File_Per_Time() {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hochiminhPath+"\n"+danangPath+"\n"+hanoiPath);
		
//		Verify file loaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+hochiminhName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+hanoiName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='"+danangName+"']")).isDisplayed());
		
//		Click start button to upload each file
		List<WebElement> listOfStartButton = driver.findElements(By.cssSelector("table .start"));
		for(WebElement startButton :listOfStartButton) {
			startButton.click();
			sleepInSeconds(1);
		}
		
//		Verify file uploaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+hochiminhName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+hanoiName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+danangName+"']")).isDisplayed());
		
		Assert.assertTrue(isImageLoadedSuccess("//a[@title='"+hochiminhName+"']/img"));
		Assert.assertTrue(isImageLoadedSuccess("//a[@title='"+hanoiName+"']/img"));
		Assert.assertTrue(isImageLoadedSuccess("//a[@title='"+danangName+"']/img"));
		
	}
	
	@Test
	public void TC_03_SendKeys() {
		driver.get("https://gofile.io/?t=uploadFiles");
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(hochiminhPath+"\n"+danangPath+"\n"+hanoiPath);
		
//		Verify file loaded successfully
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+hochiminhName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+hanoiName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+danangName+"']")).isDisplayed());
		
//		Click start button to upload each file
		driver.findElement(By.id("uploadFiles-btnUpload")).click();
		
//		Verify file uploaded successfully
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success span")));
		Assert.assertEquals(driver.findElement(By.cssSelector(".alert-success span")).getText(),"Your files have been successfully uploaded");
		
		String parentWindowID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//td[text()='Download link']/following-sibling::td/a")).click();
		
		switchToWindowByID(parentWindowID);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='"+hochiminhName+"']")));
		
		Assert.assertTrue(isDownloadedIconDisplay(danangName, "download"));
		Assert.assertTrue(isDownloadedIconDisplay(hanoiName, "download"));
		Assert.assertTrue(isDownloadedIconDisplay(hochiminhName, "download"));
		
		Assert.assertTrue(isDownloadedIconDisplay(danangName, "showInfo"));
		Assert.assertTrue(isDownloadedIconDisplay(hanoiName, "showInfo"));
		Assert.assertTrue(isDownloadedIconDisplay(hochiminhName, "showInfo"));
		
		
	}
	
	public boolean isDownloadedIconDisplay(String fileName, String action) {
		return driver.findElement(By.xpath("//td[text()='"+fileName+"']/following-sibling::td/a[contains(@class,'"+action+"')]")).isDisplayed();
	}

	private void switchToWindowByID(String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for(String windowID:allWindowIDs) {
			if(!windowID.equals(parentID)) {
				driver.switchTo().window(windowID);
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
	
//	Check image broken
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
