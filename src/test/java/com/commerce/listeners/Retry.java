package com.commerce.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count = 0;      // Contador para llevar la cuenta de los reintentos
    private static int maxTry = 1;// Reintenta 1  vez

    // Este método se llama cada vez que una prueba falla
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {     // Si la prueba falló
            if (count < maxTry) {       // Si el número de reintentos es menor que el máximo permitido
                count++;                // Incrementa el contador de reintentos
                iTestResult.setStatus(ITestResult.FAILURE);     // Marca la prueba como fallida
                 //extendReportsFailOperations(iTestResult);        // Método para manejar el reporte de fallas (no se muestra en el código)
                return true;        // Retorna true para indicar que se debe reintentar la prueba
            }
        } else {        // Si la prueba tuvo éxito
            iTestResult.setStatus(ITestResult.SUCCESS);     // Marca la prueba como exitosa
        }
        return false;       // Retorna false para indicar que no se debe reintentar la prueba
    }

    /* public void extendReportsFailOperations(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        String base64Screenshot = "data:image/png;base64,"
                + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }       */
}