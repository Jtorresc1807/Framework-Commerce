package com.commerce.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.commerce.logs.Log;
import com.commerce.pages.HomePage;
import com.commerce.pages.LoginPage;
import com.commerce.pages.OrderPage;
import com.commerce.pages.RegisterPage;
import com.commerce.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//#2
public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected OrderPage orderPage;
    public ExtentSparkReporter spark;
    public ExtentReports extent;
    public ExtentTest test;
    String reportName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());


    @BeforeTest
    public void loadSettings() {
        Log.info("Loading Base Url for all testing");
    }

    @BeforeTest
    public void starReport() {
        // config report
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/extentreport/" + reportName + ".html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Test(groups = {"Functional", "Regression", "Integration"})
    @BeforeMethod                       //Ejecuta un grupo de sentencias en cierto orden.
    public void setUp(){                //setUP solo sirve para configurar el web driver y pasar las clase
        Log.info("Instantiating Browser");
        driver = new FirefoxDriver();    //Instanciar el Driver
        Log.info("Initializing Page Object Model");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        orderPage = new OrderPage(driver);
        //extent = new ExtentReports();              //Instancia el extend test =extent.createTest que invocan las clases test y los archivos testng.xml
        //Instanciado ejecuta los testng pero no genera extentreport
        //Sin instaciar ejecuta functionaltest no genera extentreport y sale error ... because "this.extent" is null
        //Sin instaciar ejecuta logintest, OrderTest, RegisterTest loginregistertest y SI genera extentreport
    }

    @AfterMethod
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

    @AfterTest
    public void makeReport() {
        extent.flush();				// Genera el reporte
    }

    }