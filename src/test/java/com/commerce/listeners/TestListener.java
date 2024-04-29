package com.commerce.listeners;

import com.aventstack.extentreports.Status;
import com.commerce.tests.BaseTest;
import com.commerce.logs.Log;
import com.commerce.tests.BaseTestDocker;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Objects;

import static com.commerce.extentreports.ExtentManager.getExtentReports;
import static com.commerce.extentreports.ExtentTestManager.getTest;

public class TestListener extends BaseTestDocker implements ITestListener {       // Esta clase extiende de BaseTestDocker e implementa la interfaz ITestListener

    private static String getTestMethodName(ITestResult iTestResult) {      // Método privado y estático que obtiene el nombre del método de prueba a partir de un objeto ITestResult
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    //Image attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")          // Método que toma una captura de pantalla y la adjunta como una imagen en el reporte de Allure
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    //Text attachments for Allure           // Método que adjunta un mensaje de texto en el reporte de Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure           // Método que adjunta un contenido HTML en el reporte de Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override            // Método que se ejecuta antes de iniciar los tests
	public void onStart(ITestContext iTestContext) {
        Log.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }

    @Override           // Método que se ejecuta después de finalizar los tests
	public void onFinish(ITestContext iTestContext) {
        Log.info("I am in onFinish method " + iTestContext.getName());
        //ExtentReports reporting finishing
        getExtentReports().flush();
    }

    @Override           // Método que se ejecuta antes de iniciar cada método de prueba
	public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is Starting.");
    }

    @Override           // Método que se ejecuta cuando un método de prueba tiene éxito
	public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is Succeed.");
        //ExtentReports logs for passed tests.
        getTest().log(Status.PASS, "Test passed");
    }

    @Override       // Método que se ejecuta cuando un método de prueba falla
	public void onTestFailure(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is failed.");

        //Get driver from BaseTest and assign to local WebDriver variable.
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        // Si el objeto WebDriver no es nulo, captura una imagen de la pantalla y la adjunta al reporte de Allure
        if (driver != null) {
            System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
            saveScreenshotPNG(driver);
        }

        // Adjunta un mensaje de texto al reporte de Allure indicando que la prueba falló
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");

        //Take base64Screenshot screenshot for ExtentReports
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

        // Registra un registro de fallo en el reporte de ExtentReports y adjunta la imagen de la pantalla
        getTest().log(Status.FAIL, "Test Failed",getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }

    @Override           // Método que se ejecuta cuando un método de prueba es omitido
	public void onTestSkipped(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is skipped.");
        // Registra un registro de prueba omitida en el reporte de ExtentReports
        getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override           // Método que se ejecuta cuando un método de prueba falla, pero está dentro del porcentaje de éxito definido
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
        // Tarea por realizar ...
    }
}
