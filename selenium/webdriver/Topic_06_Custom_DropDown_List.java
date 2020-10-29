package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_06_Custom_DropDown_List {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	 
@BeforeClass
public void beforeClass() {
	  driver = new FirefoxDriver();
	  explicitWait = new WebDriverWait(driver, 10);
	  jsExecutor = (JavascriptExecutor)driver;
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
}

//@Test
//public void TC_01_JQuery() throws InterruptedException {
//	driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
//	
//	selectItemOnDropDownList("19", By.id("number-button"), By.xpath("//ul[@id='number-menu']//div"));
//	sleepInSeconds(2);
//	
//	Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"19");
//}



//@Test
//public void TC_02_Angular() throws InterruptedException {
//	driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
//	
//	By parentXpath = By.xpath("//ejs-dropdownlist[@id='games']/span");
//	By allItemXpath = By.xpath("//ul[@id='games_options']/li");
//	
//	selectItemOnDropDownList("Football", parentXpath,allItemXpath);
//	sleepInSeconds(2);
//
//	Assert.assertEquals(getAngularSelectedValueByJS(),"Football");
//	
//	selectItemOnDropDownList("Hockey", parentXpath, allItemXpath);
//	sleepInSeconds(2);
//
//	Assert.assertEquals(getAngularSelectedValueByJS(),"Hockey");
//
//}

//@Test
//public void TC_03_ReactJS() throws InterruptedException {
//	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
//	
//	By parentXpath = By.xpath("//div[@role='listbox']");
//	By allItemsBy = By.xpath("//div[@role='option']//span[@class='text']");
//	
//	selectItemOnDropDownList("Jenny Hess", parentXpath, allItemsBy);
//	sleepInSeconds(2);
//	Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Jenny Hess']")).isDisplayed());
//	
//	selectItemOnDropDownList("Justen Kitsune", parentXpath, allItemsBy);
//	sleepInSeconds(2);
//	Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Justen Kitsune']")).isDisplayed());
//	
//	selectItemOnDropDownList("Stevie Feliciano", parentXpath, allItemsBy);
//	sleepInSeconds(2);
//	Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Stevie Feliciano']")).isDisplayed());
//
//	
//	
//}

//@Test
//public void TC_04_VueJS() throws InterruptedException {
//	driver.get("https://mikerodham.github.io/vue-dropdowns/");
//	
//	By parentBy = By.xpath("//li[@class='dropdown-toggle']");
//	By allItemsBy =By.xpath("//ul[@class='dropdown-menu']//a");
//
//
//selectItemOnDropDownList("Second Option",parentBy,allItemsBy);
//sleepInSeconds(2);
//Assert.assertEquals(driver.findElement(parentBy).
//	getText(),"Second Option");
//
//selectItemOnDropDownList("First Option",parentBy,allItemsBy);
//sleepInSeconds(2);
//Assert.assertEquals(driver.findElement(parentBy).
//	getText(),"First Option");
//
//
//selectItemOnDropDownList("Third Option",parentBy,allItemsBy);
//sleepInSeconds(2);
//Assert.assertEquals(driver.findElement(parentBy).
//	getText(),"Third Option");
//
//	
//	
//}




//@Test
//public void TC_05_Editable() throws InterruptedException {
//	driver.get("http://indrimuska.github.io/jquery-editable-select/");
//	
//	By parentBy = By.xpath("//div[@id='default-place']/input");
//	By allItemsBy =By.xpath("//div[@id='default-place']//li");
//	
//	enterValueOnEditableDropDownList("Audi",parentBy,allItemsBy);
//	sleepInSeconds(2);
//	Assert.assertEquals(driver.findElement(parentBy).getAttribute("value"),"Audi");
//	
//	enterValueOnEditableDropDownList("Jeep",parentBy,allItemsBy);
//	sleepInSeconds(2);
//	Assert.assertEquals(driver.findElement(parentBy).getAttribute("value"),"Jeep");
//	
//	
//	enterValueOnEditableDropDownList("Land Rover",parentBy,allItemsBy);
//	sleepInSeconds(2);
//	Assert.assertEquals(driver.findElement(parentBy).getAttribute("value"),"Land Rover");
//	
//}

//@Test
//public void TC_05_Editable_Part2() throws InterruptedException {
//	driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
//	
//	By parentBy = By.xpath("//input[@class='search']");
//	By allItemsBy =By.xpath("//div[@role='listbox']//span[@class='text']");
//	
//	enterValueOnEditableDropDownList("Algeria",parentBy,allItemsBy);
//	sleepInSeconds(2);
//	Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Algeria']")).isDisplayed());
//	
//	enterValueOnEditableDropDownList("Bangladesh",parentBy,allItemsBy);
//	sleepInSeconds(2);
//	Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Bangladesh']")).isDisplayed());
//	
//}

@Test
public void TC_06_Multiple_DropDown_List() throws InterruptedException {
	driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
	
	By parentBy = By.xpath("(//button[@class='ms-choice'])[1]");
	By allItemsBy =By.xpath("(//button[@class='ms-choice'])[1]/following-sibling::div//span");
	
	By itemSelectedBy = By.xpath("//li[@class='selected']//input");
	String[] selectedItem = {"January","March","December"};
	
	selectMultipleItemsOnDropDownList(selectedItem, parentBy, allItemsBy);
	sleepInSeconds(2);
	Assert.assertTrue(areItemsSelected(selectedItem, itemSelectedBy));
	

	
}


private void selectItemOnDropDownList(String selectText, By parentElement, By allItems) {

	driver.findElement(parentElement).click();
	
	List<WebElement> listItems =explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItems));

	for(WebElement item:listItems) {
		System.out.println("Item:   "+item.getText());
		if(item.getText().equals(selectText)) {
			item.click();
		
			
			break;
		}
	}
}

private void enterValueOnEditableDropDownList(String selectText, By parentElementBy, By allItems) {
	WebElement parentElement = driver.findElement(parentElementBy);
	parentElement.clear();
	parentElement.sendKeys(selectText);
	
	List<WebElement> listItems =explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItems));

	for(WebElement item:listItems) {
		System.out.println("Item:   "+item.getText());
		if(item.getText().equals(selectText)) {
			item.click();
			break;
		}
	}
}

private void selectMultipleItemsOnDropDownList(String[] selectText, By parentElement, By allItems) {
	driver.findElement(parentElement).click();
	
	List<WebElement> listItems =explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItems));

	for(WebElement item:listItems) {
		for(String option:selectText) {
		if(item.getText().equals(option)) {
			System.out.println("Item:   "+item.getText()+" select Text"+option);
			item.click();
			sleepInSeconds(1);
			
			List<WebElement> listOfSelectedOption = driver.findElements(By.xpath("//li[@class='selected']/input"));
			if(listOfSelectedOption.size()==selectText.length) {
				break;
			}
		}
		}
	}
}

private boolean areItemsSelected(String[] itemSelectedText, By itemSelectedBy) {
	List<WebElement> listSelectedItem = driver.findElements(itemSelectedBy);
	int numberOfSelectedItem = listSelectedItem.size();
	System.out.println("Number selected:   "+numberOfSelectedItem);
	
	String allItemSelectedOnbar = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
	
	if(numberOfSelectedItem>0&&numberOfSelectedItem<=3) {
		for(String option:itemSelectedText) {
			System.out.println("option "+option);
			if(allItemSelectedOnbar.contains(option)) {
				System.out.println("1");
				break;
			}
		}
		return true;
	}else{
		return driver.findElement(
				By.xpath("//button[@class='ms-choice']/span[text()='"+numberOfSelectedItem+" of 12 selected']")).isDisplayed();
	}
	
}




private void sleepInSeconds(int seconds) {
	try {
		Thread.sleep(seconds*1000);
	}catch(Exception e) {
		
	}
}

private String getAngularSelectedValueByJS() {
	return (String)jsExecutor.executeScript
			("return document.querySelector(\"ejs-dropdownlist[id='games'] option\").text");
}









@AfterClass
public void afterClass() {
	  driver.quit();
}

}
