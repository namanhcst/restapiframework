package api.test;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import api.utilities.ExtentReportManager;

public class BaseTest {

	@BeforeSuite
	public void beforeSuite() {
		ExtentReportManager.initializeExtentReports();

	}

	@BeforeTest
	public void beforeTest() {

	}

	@BeforeClass
	public void beforeClass() {

	}

	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		ExtentReportManager.createTest(result.getMethod().getMethodName());

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			ExtentReportManager.logPass("passed !");
		} else {
			ExtentReportManager.logPass("failed !");

		}

	}

	@AfterClass
	public void afterClass() {

	}

	@AfterTest
	public void afterTest() {

	}

	@AfterSuite
	public void afterSuite() {
		ExtentReportManager.flushReports();

	}

}
