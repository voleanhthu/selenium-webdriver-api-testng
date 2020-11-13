package webdriver;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction_PartII {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	
	String projectLocation = System.getProperty("user.dir");
	String jsHelperPath = projectLocation+"/dragAndDrop/drag_and_drop_helper.js";
	
	@BeforeClass
	public void beforeClass() {
		  driver = new FirefoxDriver();
		  
		  action = new Actions(driver);
		  jsExecutor = (JavascriptExecutor)driver;
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath(
				"//li[contains(@class,'context-menu-icon-quit') and not (contains(@class,'context-menu-visible')) and not (contains(@class,'context-menu-hover'))]")).isDisplayed());
		
	
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and not (contains(@class,'context-menu-visible')) and not (contains(@class,'context-menu-hover'))]"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath(
				"//li[contains(@class,'context-menu-icon-quit') and (contains(@class,'context-menu-visible')) and"
				+ " (contains(@class,'context-menu-hover'))]")).isDisplayed());
		
		driver.findElement(By.xpath(
				"//li[contains(@class,'context-menu-icon-quit') and (contains(@class,'context-menu-visible')) and"
				+ " (contains(@class,'context-menu-hover'))]")).click();
		
		sleepInSeconds(2);
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		driver.switchTo().alert().accept();
	}
	
	@Test
	public void TC_02_Drag_And_Drop_HTML4() {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
		
		driver.findElement(By.xpath("//button[text()='Accept Cookies']")).click();
		
		sleepInSeconds(2);
		
		WebElement sourceElement = driver.findElement(By.id("draggable"));
		WebElement targetElement = driver.findElement(By.id("droptarget"));
		
		scrollToElemnet(targetElement);
		
		sleepInSeconds(3);
		
		Assert.assertEquals(targetElement.getText(), "Drag the small circle here.");
		
		action.dragAndDrop(sourceElement, targetElement).perform();
		
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(), "You did great!");
		
		sleepInSeconds(2);
		
		Assert.assertEquals(getColorhelixFormat(targetElement),"#03a9f4");
	}
	
	private String getColorhelixFormat(WebElement element) {
		return org.openqa.selenium.support.Color.fromString(element.getCssValue("background-color")).asHex();
	}
	
	private void scrollToElemnet(WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	@Test
	public void TC_03_DragDrop_HTML5_Offset() throws InterruptedException, IOException, AWTException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");
	
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='A']"));
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));
	}
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	@Test
	public void TC_04_Drag_And_Drop_HTML5() throws InterruptedException, IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String sourceRectangleCss = "#column-a";
		String targetRectangleCss = "#column-b";

		String jsHelperContent = getJSFileContent(jsHelperPath);

		// A to B
		jsHelperContent = jsHelperContent + "$(\"" + sourceRectangleCss + "\").simulateDragDrop({ dropTarget: \"" + targetRectangleCss + "\"});";
		jsExecutor.executeScript(jsHelperContent);
		sleepInSeconds(2);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='B']"));

		// B to A
		jsExecutor.executeScript(jsHelperContent);
		sleepInSeconds(2);
		Assert.assertTrue(isElementDisplayed("//div[@id='column-a']/header[text()='A']"));
	}
	
	private void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		}catch(Exception e) {
			
		}
	}
	
	private boolean isElementDisplayed(String xpath) {
		return driver.findElement(By.xpath(xpath)).isDisplayed();
	}
	
	private String getJSFileContent(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	@AfterClass
	public void afterClass() {
		  driver.quit();
	}
	

}
