package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_02_Group {

	@Test(groups = "api")
	public void TC01() {
		System.out.println("TC01...");
	}

	@Test(groups = "web")
	public void TC02() {
		System.out.println("TC02...");
	}

	@Test(groups = "mobile")
	public void TC03() {
		System.out.println("TC03...");
	}

	@Test
	public void TC04() {
		System.out.println("TC04...");
	}

}
