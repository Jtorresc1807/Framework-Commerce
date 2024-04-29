package com.commerce.tests;

import com.commerce.logs.Log;
import com.commerce.pages.DependenceLoginPage;
import com.commerce.pages.DependenceOrderPage;
import com.commerce.pages.DependenceRegisterPage;
import com.commerce.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DependenceBaseTest {

              WebDriver driver;
    protected HomePage homePage;
    protected DependenceRegisterPage dependenceRegisterPage;
    protected DependenceLoginPage dependenceLoginPage;
    protected DependenceOrderPage dependenceOrderPage;

    @BeforeTest
    public void loadSettings() {
        Log.info("Loading Base Url for all testing");
    }

    @Test(groups = {"Functional", "Regression", "Integration"})
    @BeforeMethod                    //Ejecuta un grupo de sentencias en cierto orden.
    public void setUp(){                //setUP solo sirve para configurar el web driver y pasar las clase

        FirefoxOptions options = new FirefoxOptions();
        //Log.info("Run Browser withuot UI");
        //options.addArguments("-headless");        // ejecucion sin ui del navegador

        Log.info("Instantiating Browser");
        driver = new FirefoxDriver(options);    //Instanciar el Driver
        Log.info("Initializing Page Object Model");
        homePage = new HomePage(driver);
        dependenceRegisterPage = new DependenceRegisterPage(driver);
        dependenceLoginPage = new DependenceLoginPage(driver);
        dependenceOrderPage = new DependenceOrderPage(driver);

        //extent = new ExtentReports();              //Instancia el extend test =extent.createTest que invocan las clases test y los archivos testng.xml
        //Instanciado ejecuta los testng pero NO extentreport allure OK
        //Sin instaciar ejecuta loginregistertest.xml genera extentreport pero solo de RegisterTest  allure OK
        //Sin instaciar ejecuta logintest, OrderTest, RegisterTest OK extentreport OK allure OK
        //Sin instaciar ejecuta groupstests.xml y sale error ... because "this.extent" is null extentreport NO
    }

 /*   @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.FAILURE) {
            // agregar label
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test case Failed", ExtentColor.RED));

            // posible root cause
            test.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getThrowable() + " - Possible root cause", ExtentColor.AMBER));

            // take screenshot
            String screenPath = Utils.getScreenshotForReport(driver, result.getName());
            test.fail("More details view Picture", MediaEntityBuilder.createScreenCaptureFromPath(screenPath).build());

        } else if (result.getStatus() == ITestResult.SUCCESS) {

            test.log(Status.PASS,
                    MarkupHelper.createLabel(result.getName() + " - Test case Passed", ExtentColor.GREEN));

        } else if (result.getStatus() == ITestResult.SKIP) {

            test.log(Status.SKIP,
                    MarkupHelper.createLabel(result.getName() + " - Test case Passed", ExtentColor.ORANGE));
        }

        Log.info("Closing the driver instance");
        if (driver != null) {
            //driver.quit();
        }
    }

    @AfterSuite
    public void makeReports() {
        extent.flush();				// Genera el reporte
    }    */


}