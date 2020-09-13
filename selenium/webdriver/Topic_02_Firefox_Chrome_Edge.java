package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Firefox_Chrome_Edge {
	WebDriver driver;
	static String a;
	
	@Test
	public void Testcase_01_Run_Firefox() {
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com");
		driver.quit();
	}
	
	@Test
	public void TestCase_02_Run_Chrome() {
//		String projectFolder = System.getProperty("user.dir");
//		System.setProperty("webdriver.chrome.driver", projectFolder+"/browserDrivers/chromedriver");
		
//		Relative 
		System.setProperty("webdriver.chrome.driver", "./browserDrivers/chromedriver");
		driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com");
		driver.quit();
		
		
		
		
	}
	
	@Test
	public void TestCase_03_Run_Edge() {
///		Relative 
		System.setProperty("webdriver.edge.driver", "./browserDrivers/msedgedriver");
		driver = new EdgeDriver();
		
		driver.get("https://www.facebook.com");
		driver.quit();
		
	}
	

	

}
