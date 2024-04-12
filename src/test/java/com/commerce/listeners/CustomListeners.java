package com.commerce.listeners;

import com.commerce.logs.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Log.info("Im on the test TC-Login " + result.getName() + " Test .... Starting ...");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("The " + result.getName() + " Test .... was Success ...");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info("The " + result.getName() + " Test .... was Failured ...");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Log.info("The " + result.getName() + " Test .... Skiped ...");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        ITestListener.super.onFinish(context);
    }
}
