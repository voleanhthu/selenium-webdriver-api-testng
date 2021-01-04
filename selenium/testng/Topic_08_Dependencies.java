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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

@Listeners(ReportListener.class)
public class Topic_08_Dependencies {

	
	@Test()
	public void TC_01_Create() {
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "TC_01_Create")
	public void TC_02_Edit() {

	}

	@Test(dependsOnMethods = "TC_01_Create")
	public void TC_03_Delete() {

	}
}
