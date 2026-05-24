package base.listeners;

import base.DriverFactory;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListeners implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        // Initialize the report instance when the suite starts
        ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test node inside the report for the current thread
        var test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test passed cleanly!");
        ExtentManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String screenshotName = testName + "_" + timestamp + ".png";

        // Log the failure details first so they are always in the report
        ExtentManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        // 🌟 THE GUARDRAIL: Only attempt screenshot capture if an active browser driver exists
        if (DriverFactory.getDriver() != null) {
            // Relative path so the HTML report can locate the image locally
            String relativePath = "screenshots/" + screenshotName;
            String fullPath = System.getProperty("user.dir") + "/target/" + relativePath;

            File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            File destFile = new File(fullPath);

            try {
                if (destFile.getParentFile() != null) {
                    destFile.getParentFile().mkdirs();
                }
                FileUtils.copyFile(srcFile, destFile);

                // 🌟 Bind the captured screenshot into the HTML dashboard
                ExtentManager.getTest().addScreenCaptureFromPath(relativePath);
            } catch (IOException e) {
                System.err.println("Failed to bind screenshot to report: " + e.getMessage());
            }
        } else {
            System.out.println("ℹ️ API Failure detected for " + testName + " - Skipping screenshot capture since no browser is active.");
        }

        // Always keep this in the finally block to prevent thread memory leaks!
        ExtentManager.removeTest();
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush writes all data to the file system at the very end
        if (ExtentManager.getInstance() != null) {
            ExtentManager.getInstance().flush();
        }
    }

    @Override public void onTestSkipped(ITestResult result) {}
}