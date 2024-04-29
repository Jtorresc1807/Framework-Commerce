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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.Duration;

import static com.commerce.utils.Variables.TIME_OUT;

@Listeners(com.commerce.listeners.CustomListeners.class)
@Epic("Epic - Test execution with method dependency")
@Feature("Feature - Create account, login with new account and add products to cart")
public class DependenceOrderTest extends DependenceLoginTest{

    String expected_title = "Shopping cart";

    @Test(testName = "TC-Order-01 Add products to cart", priority = 1, groups = "Regression", dependsOnMethods = {"loginWithCredentials"})
    @Description("Test Description: A logged-in user can add products to the shopping cart, depending of new register and login")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Jaime Torres")
    @Story("User story 412321, Sprint 65")
    @Name("TC-Order-01 Add products to cart depending of new register and login")
    public void AddProductsToShoppingCart(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In and enter valid credentials");
        dependenceLoginPage.signInWithCredentials();
        dependenceLoginPage.completeLog();
        Log.info("Add products to cart");
        dependenceOrderPage.addProductsToCart();
        /* test = extent.createTest("Add products to cart", "A logged-in user can add products to the shopping cart, selecting products from a list or catalog page, or searching by category.");
        test.assignAuthor("Jaime");
        test.assignCategory("Regression");
        test.assignDevice("Win 10");   */

        Log.debug("Assertion to valid Add products to cart");
        WebElement notificationSuccess = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='page-title']")));
        Assert.assertEquals(notificationSuccess.getText(),expected_title);
    }
}