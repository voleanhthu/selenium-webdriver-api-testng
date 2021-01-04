package testng;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ReportListener implements ITestListener{

	@Override
	public void onFinish(ITestContext arg0) {
		System.out.println("Finish..");
		
	}

	@Override
	public void onStart(ITestContext arg0) {
		System.out.println("Start..");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		System.out.println("Failed..");
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Skipp..");
		
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("On Test start..");
		
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		System.out.println("Success..");
		
	}

}
