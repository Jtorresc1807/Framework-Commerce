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

//#10
@Listeners(com.commerce.listeners.CustomListeners.class)
@Epic("Epic - Purchase Order Module")
@Feature("Feature - Purchase Order validation for the application demo.nopcommerce.com with different test cases")
public class OrderTest extends BaseTest{

    String expected_notification = "The product has been added to your shopping cart";

    @Test(testName = "TC-Order-01 Add products to cart", priority = 1, groups = "Regression")
    @Description("Test Description: A logged-in user can add products to the shopping cart, selecting products from a list or catalog page, or searching by category.")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Jaime Torres")
    @Story("User story 435678, Sprint 65")
    @Name("TC-Order-01 Add products to cart")
    public void AddProductsToShoppingCart(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In and enter valid credentials");
        loginPage.signInWithCredentialsValids();
        loginPage.completeLogin();
        Log.info("Add products to cart");
        orderPage.addProductsToCart();
        /* test = extent.createTest("Add products to cart", "A logged-in user can add products to the shopping cart, selecting products from a list or catalog page, or searching by category.");
        test.assignAuthor("Jaime");
        test.assignCategory("Regression");
        test.assignDevice("Win 10");   */

        Log.debug("Assertion to valid Add products to cart");
        WebElement notificationSuccess = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class='content']")));
        Assert.assertEquals(notificationSuccess.getText(),expected_notification);
    }

    @Test(testName = "TC-Order-02 Remove products from cart", priority = 2, groups = "Regression")
    @Description("Test Description: Remove products from shopping cart shopping cart.")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Jaime Torres")
    @Story("User story 435678, Sprint 65")
    @Name("TC-Order-02 Remove products from cart")
    public void removeProductsFromToShoppingCart(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In and enter valid credentials");
        loginPage.signInWithCredentialsValids();
        loginPage.completeLogin();
        Log.info("Add products to cart");
        orderPage.addProductsToCart();
        Log.info("Remove products to cart");
        orderPage.removeProductsToCart();
        /*  test = extent.createTest("Remove products from cart", "Remove products from shopping cart shopping cart.");
        test.assignAuthor("Jaime");
        test.assignCategory("Regression");
        test.assignDevice("Win 8");   */

        Log.debug("Assertion to valid Remove products from shopping cart");
        WebElement noticeShoppingCart = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='no-data']")));
        //Assert.assertEquals(noticeShoppingCart.getText(),expected_notice);
        Assert.assertTrue(noticeShoppingCart.isDisplayed());
    }

    @Test(testName = "TC-Order-03 Modify Quantity Of Products In The Cart", priority = 3, groups = {"Functional", "Integration"})
    @Description("Test Description: Adjust the quantity of products in the cart before proceeding to checkout, allowing you to increase or decrease the quantity of each item.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Jaime Torres")
    @Story("User story 345672, Sprint 65")
    @Name("TC-Order-03 Modify Quantity Of Products In The Cart")
    public void modifyQuantityOfProductsInTheCart(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In and enter valid credentials");
        loginPage.signInWithCredentialsValids();
        loginPage.completeLogin();
        Log.info("Add products to cart");
        orderPage.addProductsToCart();
        Log.info("Increase the quantity of products");
        orderPage.increaseQuantityProducts();
        Log.info("Reduce the quantity of products");
        orderPage.reduceQuantityProducts();
        /*  test = extent.createTest("Modify Quantity Of Products In The Cart", "Adjust the quantity of products in the cart before proceeding to checkout, allowing you to increase or decrease the quantity of each item.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional", "Integration");
        test.assignDevice("Win Xp");  */

        Log.debug("Assertion to valid reduce the quantity of products");
        WebElement assertUpdateRed = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='2']")));
        Assert.assertTrue(assertUpdateRed.isDisplayed());
    }

    @Test(testName = "TC-Order-04 Order creation successful", priority = 4, groups = "Regression")
    @Description("Test Description: Selects 3 different products, provides valid shipping and payment information, and successfully completes the order creation process, receiving an order confirmation.")
    @Severity(SeverityLevel.MINOR)
    @Owner("Jaime Torres")
    @Story("User story 456356, Sprint 65")
    @Name("TTC-Order-04 Order creation successful")
    public void makeOrderCreationSuccessful(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In and enter valid credentials");
        loginPage.signInWithCredentialsValids();
        loginPage.completeLogin();
        Log.info("Add products to cart");
        orderPage.addProductsToCart();
        Log.info("Do Checkout to products");
        orderPage.orderProductsCreation();
        Log.info("Fill Out Form Billing Address");
        orderPage.orderCkechoutBillingAddress();
        Log.info("Fill Out Form Shippin And Paymentg Method");
        orderPage.orderCkechoutShippinAndPaymentgMethod();
        Log.info("Fill Out Form Payment Information");
        orderPage.orderCkechoutPaymentInformation();

        /*  test = extent.createTest("Make Order Creation Successful", "Selects 3 different products, provides valid shipping and payment information, and successfully completes the order creation process, receiving an order confirmation.");
        test.assignAuthor("Jaime");
        test.assignCategory("Regression");
        test.assignDevice("Win Xp");  */

        Log.debug("Assertion to valid Creation to purchase Order Successful");
        WebElement assertUpdateRed = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")));
        Assert.assertTrue(assertUpdateRed.isDisplayed());
    }

}