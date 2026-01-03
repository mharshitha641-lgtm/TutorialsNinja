package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.Basetest;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getName());
        test.set(extentTest);
        test.get().info("Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable()); // log the exception

        WebDriver driver = Basetest.driver;
        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());

        if (screenshotPath != null) {
            test.get().addScreenCaptureFromPath(screenshotPath); // attach screenshot to report
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // writes everything to the HTML report
    }

    @Override public void onStart(ITestContext context) { }
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) { }
}
