package com.commerce.tests;

import com.commerce.logs.Log;
import com.commerce.pages.HomePage;
import com.commerce.pages.LoginPage;
import com.commerce.pages.OrderPage;
import com.commerce.pages.RegisterPage;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.URL;

public class BaseTestDocker {
    protected WebDriver driver;
    protected HomePage homePage;
    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected OrderPage orderPage;

    //String nodeURL = "http://209.38.140.87:4444/";
    String nodeURL = "http://localhost:4444";

    public WebDriver getDriver() {
        return driver;
    }

    @Parameters({ "browser" })
    @BeforeMethod       //@BeforeTest
    public void Setup(String browser) throws Exception {
        Log.info("Setup browser ... " + browser );

        Capabilities options;

        if (browser.equalsIgnoreCase("edge")) {
            options = new EdgeOptions();
        } else if (browser.equalsIgnoreCase("firefox")) {
            options = new FirefoxOptions();
        } else if (browser.equalsIgnoreCase("chrome")) {
            options = new ChromeOptions();
        } else {
            throw new Exception("Browser not supported");
        }

        driver = new RemoteWebDriver(new URL(nodeURL),options);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void initialMethods() {
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        orderPage = new OrderPage(driver);

    }

    @AfterClass
    public void teardown() {
        Log.info("Tests are ending ... ");
        driver.quit();
    }
}
