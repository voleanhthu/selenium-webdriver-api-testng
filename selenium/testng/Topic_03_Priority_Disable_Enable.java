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

public class Topic_03_Priority_Disable_Enable {

//  @Test(priority = 1)
//  public void TC01() {
//	  System.out.println("TC01...");
//  }
//  
//  @Test(priority = 3)
//  public void TC02() {
//	  System.out.println("TC02...");
//  }
//  
//  @Test(priority = 2)
//  public void TC03() {
//	  System.out.println("TC03...");
//  }
//  
//  @Test(priority = 4)
//  public void TC04() {
//	  System.out.println("TC04...");
//  }

	@Test(enabled = true, description = "Testing with me")
	public void TC01() {
		System.out.println("TC01...");
	}

	@Test(enabled = false)
	public void TC02() {
		System.out.println("TC02...");
	}

	@Test(enabled = true)
	public void TC03() {
		System.out.println("TC03...");
	}

	@Test(enabled = false)
	public void TC04() {
		System.out.println("TC04...");
	}
}
