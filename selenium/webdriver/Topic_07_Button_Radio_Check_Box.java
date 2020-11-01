package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_07_Button_Radio_Check_Box {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	By btnLogin = By.cssSelector(".fhs-btn-login");
	By btnPhone = By.xpath("//input[@id='register_phone']");
	By emailBy = By.cssSelector("#login_username");
	By passwordBy = By.cssSelector("#login_password");
	
	By radioButtonBy = By.xpath("//div[contains(text(),'Summer')]/preceding-sibling::div/input");
	By checkedCheckBoxBy = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::div/input");
	By IndeterminateBy = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::div/input");
	
	@BeforeClass
	public void beforeClass() {
		  driver = new FirefoxDriver();
		  jsExecutor = (JavascriptExecutor)driver;
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		Assert.assertFalse(isElementEnabled(btnLogin));
		
		driver.findElement(emailBy).sendKeys("automation@gmail.com");
		driver.findElement(passwordBy).sendKeys("123456");
		
		sleepInSeconds(2);
		
		Assert.assertTrue(isElementEnabled(btnLogin));
		
		driver.navigate().refresh();
		
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		removeDisabledAttributeByJS(btnLogin);
		
		sleepInSeconds(2);
		
		driver.findElement(btnLogin).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(), "Thông tin này không thể để trống");
	}
	
	@Test
	public void TC_02_Default_CheckBox_And_RadioButton() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		
//		Select checkbox
		checkToCheckBoxOrRadio(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		
		Assert.assertTrue(isElementSelected(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		
//		Deselect checkbox
		unCheckCheckBox(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		
		Assert.assertFalse(isElementSelected(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")));
		
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		
//		Select radio button
		checkToCheckBoxOrRadio(driver.findElement(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input")));
		
		Assert.assertTrue(isElementSelected(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input")));
		
//		Select Radiobutton again
		unCheckCheckBox(driver.findElement(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input")));
	
		Assert.assertTrue(isElementSelected(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input")));
		
	}
	
	@Test
	public void TC_03_Custom_CheckBox_And_RadioButton() {
		driver.get("https://material.angular.io/components/radio/examples");
		clickOnElementByJS(driver.findElement(radioButtonBy));
		Assert.assertTrue(isElementSelected(radioButtonBy));
		
		
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		clickOnElementByJS(driver.findElement(checkedCheckBoxBy));
		Assert.assertTrue(isElementSelected(checkedCheckBoxBy));
		
		clickOnElementByJS(driver.findElement(IndeterminateBy));
		Assert.assertTrue(isElementSelected(IndeterminateBy));
		
		clickOnElementByJS(driver.findElement(checkedCheckBoxBy));
		Assert.assertFalse(isElementSelected(checkedCheckBoxBy));
		
		clickOnElementByJS(driver.findElement(IndeterminateBy));
		Assert.assertFalse(isElementSelected(IndeterminateBy));
		
		
	}
	
	private void checkToCheckBoxOrRadio(WebElement element) {
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	private void unCheckCheckBox(WebElement element) {
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementSelected(By by) {
		return driver.findElement(by).isSelected()?true:false;
	}
	
	private void removeDisabledAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",element);
	}
	
	private void clickOnElementByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();",element);
	}
	
	public boolean isElementEnabled(By by) {
		return driver.findElement(by).isEnabled()?true:false;
	}
	
	
	private void sleepInSeconds(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		}catch(Exception e) {
			
		}
	}

@AfterClass
public void afterClass() {
	  driver.quit();
}
}
