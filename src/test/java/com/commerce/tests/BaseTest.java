package com.commerce.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.commerce.logs.Log;
import com.commerce.pages.DependenceLoginPage;
import com.commerce.pages.DependenceRegisterPage;
import com.commerce.pages.HomePage;
import com.commerce.pages.LoginPage;
import com.commerce.pages.OrderPage;
import com.commerce.pages.RegisterPage;
import com.commerce.utils.Utils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

//#2
public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected OrderPage orderPage;
    protected DependenceRegisterPage dependenceRegisterPage;
    protected DependenceLoginPage dependenceLoginPage;

    public ExtentSparkReporter spark;
    public ExtentReports extent;
    public ExtentTest test;
    String reportName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeTest                         //este método se ejecutará antes de los tests
    public void loadSettings()  {
        Log.info("Loading Base Url for all testing");
    }

    @BeforeTest
    public void starReport() {
        // config extend report
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/extentreport/" + reportName + ".html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }


    @Test(groups = {"Functional", "Regression", "Integration"})
    @BeforeMethod                    //Ejecuta un grupo de sentencias en cierto orden.
    public void setUp(){                //setUP solo sirve para configurar el web driver y pasar las clase

        /* FirefoxOptions options = new FirefoxOptions();
        Log.info("Run Browser withuot UI");
        options.addArguments("-headless");   */       // ejecucion sin ui del navegador

        Log.info("Instantiating Browser");
        driver = new FirefoxDriver();    //Instanciar el Driver (Quitar el options cuando se va a ejecutar con ui del navegador FirefoxDriver(options);)
        Log.info("Initializing Page Object Model");
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        orderPage = new OrderPage(driver);
        dependenceRegisterPage = new DependenceRegisterPage(driver);
        dependenceLoginPage = new DependenceLoginPage(driver);

        extent = new ExtentReports();              //Instancia el extend test =extent.createTest que invocan las clases test y los archivos testng.xml
        //Instanciado ejecuta los testng pero NO extentreport allure OK
        //Sin instaciar ejecuta loginregistertest.xml genera extentreport pero solo de RegisterTest  allure OK
        //Sin instaciar ejecuta logintest, OrderTest, RegisterTest OK extentreport OK allure OK
        //Sin instaciar ejecuta groupstests.xml y sale error ... because "this.extent" is null extentreport NO
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        Log.info("Tests are ending ... ");
        if (driver != null) {
            //driver.quit();
        }
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

    /* public void makeReports() {
        extent.flush();				// Genera el reporte
    }    */

}