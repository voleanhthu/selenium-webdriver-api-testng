package webdriver;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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



public class Topic_15_Download {
	WebDriver driver;
	WebDriverWait explicitWait;
	
	String projetLocation = System.getProperty("user.dir");
	String downloadLocation = projetLocation  + "/download";
	
//	Chrome:
//		ChromeOptions chromeOptions = new ChromeOptions();
//		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
//		chromePrefs.put("profile.default_content_settings.popups", 0);
//		chromePrefs.put("download.default_directory", projectLocation  + "\\downloadFiles");
//		chromeOptions.setExperimentalOption("prefs", chromePrefs);
//		driver = new ChromeDriver(chromeOptions);
//
//		FirefoxOptions options = new FirefoxOptions();
//		options.addPreference("browser.download.folderList", 2);
//		options.addPreference("browser.download.dir", projectLocation  + "\\downloadFiles");
//		options.addPreference("browser.download.useDownloadDir", true);
//		options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
//		options.addPreference("pdfjs.disabled", true);
//		driver = new FirefoxDriver(options);
	
//	options.addPreference("browser.helperApps.neverAsk.saveToDisk","multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,text/csv,image/png ,image/jpeg, application/pdf, text/html,text/plain,  application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream");
//	("browser.helperApps.neverAsk.saveToDisk", "text/plain, application/octet-stream, application/binary, text/csv, application/csv, application/excel, text/comma-separated-values, text/xml, application/xml")
	
	
	
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver");
//		FirefoxOptions options = new FirefoxOptions();
//		options.addPreference("browser.download.folderList", 2);
//		options.addPreference("browser.download.dir", downloadLocation);
//		options.addPreference("browser.download.useDownloadDir", true);
//		options.addPreference("browser.helperApps.neverAsk.saveToDisk", "image/jpeg, image/jpg, application/x-msexcel, application/octet-stream");
//		options.addPreference("pdfjs.disabled", true);
//		driver = new FirefoxDriver(options);
		
		System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver");
		ChromeOptions chromeOptions = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadLocation);
		chromeOptions.setExperimentalOption("prefs", chromePrefs);
		driver = new ChromeDriver(chromeOptions);
		
		deleteAllFileInFolder();
	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		
	}
	
	
	@Test
	public void TC_01_Download() {
		driver.get("https://unsplash.com/photos/ueLA4x2gZf8");
		
//		
		
		
		
		driver.findElement(By.xpath("//span[text()='Download free']")).click();
		
		
//		Verify 1 file downloaded
		waitForDownloadFileContainsNameCompleted("unsplash.jpg");
		
//		Calculate number of files downloaded in folder
		
		int countFile = countFilesInDirectory();
		System.out.println("NUMBER OF FILE...."+countFile);
		

		Assert.assertEquals(countFile, 1);
		
		
	
	}
	
	public void deleteAllFileInFolder() {
		 try {
		 File file = new File(downloadLocation);
		 File[] listOfFiles = file.listFiles();
		 for (int i = 0; i < listOfFiles.length; i++) {
		 if (listOfFiles[i].isFile()) {
		 new File(listOfFiles[i].toString()).delete();
		 }
		 }
		 } catch (Exception e) {
		 System.out.print(e.getMessage());
		 }
		 }
	
	public void waitForDownloadFileFullnameCompleted(String fileName) throws Exception {
		 int i = 0;
		 while (i < 30) {
		
		 if (isFileExists(fileName) == true) {
		 i = 30;
		 }
		 Thread.sleep(500);
		 i = i + 1;
		 }
		 }
	
	public void waitForDownloadFileContainsNameCompleted(String fileName){
		 int i = 0;
		 while (i < 40) {
		 boolean exist = isFileContain(fileName);
		 if (exist == true) {
		 i = 40;
		 }
		 sleepInSeconds(1);
		 i = i + 1;
		 }
		 }
		 
	
	
	public boolean isFileExists(String file) {
		 try {
		 File files = new File(downloadLocation + file);
		 boolean exists = files.exists();
		 return exists;
		 } catch (Exception e) {
		 System.out.print(e.getMessage());
		 return false;
		 }
		 }
		 
		 public int countFilesInDirectory() {
		 File file = new File(downloadLocation);
		 int i = 0;
		 for (File listOfFiles : file.listFiles()) {
		 if (listOfFiles.isFile()) {
		 i++;
		 }
		 }
		 return i;
		 }
		 
		 public boolean isFileContain(String fileName) {
			 try {
			 boolean flag = false;
			 File dir = new File(downloadLocation);
			 File[] files = dir.listFiles();
			 if (files == null || files.length == 0) {
			 flag = false;
			 }
			 for (int i = 0; i < files.length; i++) {
			 if (files[i].getName().contains(fileName)) {
			 flag = true;
			 }
			 }
			 return flag;
			 } catch (Exception e) {
			 System.out.print(e.getMessage());
			 return false;
			 }
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
