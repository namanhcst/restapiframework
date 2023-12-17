package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	
	private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports initializeExtentReports() {
    	
    	//String timeStamp  = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
    	String timeStamp ="";
    	String reportName = "TestReport"+timeStamp;
    	ExtentSparkReporter htmlReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);
        return test;
    }

    public static void logInfo(String message) {
        extentTest.get().info(message);
    }

    public static void logPass(String message) {
        extentTest.get().pass(message);
    }

    public static void logFail(String message) {
        extentTest.get().fail(message);
    }

    public static void logSkip(String message) {
        extentTest.get().skip(message);
    }

    public static void flushReports() {
        extent.flush();
    }
	
	
}
