package com.commerce.listeners;

import com.commerce.logs.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListeners implements ITestListener {

    @Override            // Método que se ejecuta antes de iniciar cada método de prueba
    public void onTestStart(ITestResult result) {
        Log.info("Im on the test TC-Login " + result.getName() + " Test .... Starting ...");
    }

    @Override           // Método que se ejecuta cuando un método de prueba tiene éxito
    public void onTestSuccess(ITestResult result) {
        Log.info("The " + result.getName() + " Test .... was Success ...");
    }

    @Override           // Método que se ejecuta cuando un método de prueba falla
    public void onTestFailure(ITestResult result) {
        Log.info("The " + result.getName() + " Test .... was Failured ...");
    }

    @Override            // Método que se ejecuta cuando un método de prueba es omitido
    public void onTestSkipped(ITestResult result) {
        Log.info("The " + result.getName() + " Test .... Skiped ...");
    }

    @Override           // Método que se ejecuta cuando un método de prueba falla, pero está dentro del porcentaje de éxito definido
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override           // Método que se ejecuta cuando un método de prueba falla por tiempo de espera agotado
    public void onTestFailedWithTimeout(ITestResult result) {
        // TODO Auto-generated method stub
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override           // Método que se ejecuta antes de iniciar los tests
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        ITestListener.super.onStart(context);
    }

    @Override           // Método que se ejecuta después de finalizar los tests
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        ITestListener.super.onFinish(context);
    }
}
