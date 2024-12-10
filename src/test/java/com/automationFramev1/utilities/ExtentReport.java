package com.automationFramev1.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport implements ITestListener {
    public ExtentSparkReporter SparkReporter;
    public ExtentReports extendreport;
    public ExtentTest extenttest;

    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
        String myreport = "Test-report-" + timestamp + ".html";

        SparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + myreport);
        SparkReporter.config().setDocumentTitle("Automation Report");
        SparkReporter.config().setReportName("Functional Testing");
        SparkReporter.config().setTheme(Theme.DARK);

        extendreport = new ExtentReports();
        extendreport.attachReporter(SparkReporter);
        extendreport.setSystemInfo("computer name", "local host");
        extendreport.setSystemInfo("environment", "QA");
        extendreport.setSystemInfo("tester name", "Mallikarjun");
        extendreport.setSystemInfo("OS", "Windows 10");
        extendreport.setSystemInfo("browser name", "Chrome");
    }

    public void onTestSuccess(ITestResult result) {
        extenttest = extendreport.createTest(result.getName());
        extenttest.log(Status.PASS, "Test passed: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        extenttest = extendreport.createTest(result.getName());
        extenttest.log(Status.FAIL, "Test failed: " + result.getName());

        // Log the reason for failure if available
        if (result.getThrowable() != null) {
            extenttest.log(Status.FAIL, "Reason: " + result.getThrowable().getMessage());
        }

        // Construct screenshot path
        String screenshotDir = System.getProperty("user.dir") + File.separator + "screenshot";
        new File(screenshotDir).mkdirs(); // Ensure directory exists
        String Screenshotpath = screenshotDir + File.separator + result.getName() + ".png";

        // Add screenshot to the report if it exists
        File file = new File(Screenshotpath);
        if (file.exists()) {
            try {
                extenttest.fail("Screenshot below: " + extenttest.addScreenCaptureFromPath(Screenshotpath));
            } catch (Exception e) {
                extenttest.log(Status.FAIL, "Failed to attach screenshot: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            extenttest.log(Status.FAIL, "Screenshot not found at: " + Screenshotpath);
        }
    }


     

    public void onTestSkipped(ITestResult result) {
        extenttest = extendreport.createTest(result.getName());
        extenttest.log(Status.SKIP, "Test skipped: " + result.getName());
    }

    public void onFinish(ITestContext context) {
        extendreport.flush();
    }
}
