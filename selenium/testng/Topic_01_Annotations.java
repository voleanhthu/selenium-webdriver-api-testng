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

public class Topic_01_Annotations {
//  @Test(dataProvider = "dp")
//  public void f(Integer n, String s) {
//  }

//	Testcase
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method...");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method...");
	}

//  @DataProvider
//  public Object[][] dp() {
//    return new Object[][] {
//      new Object[] { 1, "a" },
//      new Object[] { 2, "b" },
//    };
//  }

	@Test
	public void TC01() {
		System.out.println("TC01...");
	}

	@Test
	public void TC02() {
		System.out.println("TC02...");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class...");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After Class...");
	}

//  Test suite
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test..");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("After Test...");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite...");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite...");
	}

}
