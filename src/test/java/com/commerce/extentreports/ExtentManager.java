package com.commerce.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("Workshop Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Commerce Store", "Extent Report");
        return extentReports;
    }
}
