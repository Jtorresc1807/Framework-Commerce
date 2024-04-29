package com.commerce.tests;

import com.commerce.logs.Log;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Name;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.Duration;

import static com.commerce.utils.Variables.TIME_OUT;

@Listeners(com.commerce.listeners.CustomListeners.class)
@Epic("Epic - Test execution with method dependency")
@Feature("Feature - Create account, login with new account and add products to cart")

public class DependenceLoginTest extends DependenceRegisterTest {

    String inpEmail = "jaimebrs4@gmail.com";
    String inpPass = "A2wns0#04*#j";
    String expected_greeting = "Welcome to our store";


    @Test(testName = "TC-Login-01 Login with credentials and dependency of new register", priority = 1, groups = "Regression",
            dependsOnMethods = {"registerNewCustomer"})
    @Description("Test Description: Provide valid credentials and the system allows access and depending of new register")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Jaime Torres")
    @Story("User story 478065, Sprint 65 ")
    @Name("TC-Login-01 Login with credentials success depending of new register")
    public void loginWithCredentials(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In and enter valid credentials");
        dependenceLoginPage.signInWithCredentials();
        dependenceLoginPage.completeLog();
        System.out.println("Credential success, your email is: " + inpEmail + " and Password is: " + inpPass);
        /* test = extent.createTest("Login with credentials success", "Provide valid credentials and the system allows access.");
        test.assignAuthor("Jaime");
        test.assignCategory("Regression", "Integration");
        test.assignDevice("Win 10"); */

        Log.debug("Assertion to valid correct login");
        WebElement panelWelcome = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Welcome to our store')]")));
        Assert.assertEquals(panelWelcome.getText(),expected_greeting);
        AssertJUnit.assertEquals(driver.getTitle(), "nopCommerce demo store");
    }

}