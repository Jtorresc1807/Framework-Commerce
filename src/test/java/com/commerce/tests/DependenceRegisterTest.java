package com.commerce.tests;

import com.commerce.logs.Log;
import com.github.javafaker.Faker;
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
import java.util.Locale;

import static com.commerce.utils.Variables.TIME_OUT;

@Listeners(com.commerce.listeners.CustomListeners.class)
@Epic("Epic - Test execution with method dependency")
@Feature("Feature - Create account, login with new account and add products to cart")

public class DependenceRegisterTest extends DependenceBaseTest {

    Faker faker = new Faker(new Locale("en-US"));

    String expected_result = "Your registration completed";
    String email = "jaimebrs4@gmail.com";
    String pass = "A2wns0#04*#j";
    String company = faker.company().name();

    /*  public DependenceRegisterTest(WebDriver driver) {
        super();
    }   */

    @Test(testName = "TC-Register-01 Register new customer successful", priority = 1, groups = "Functional")
    @Description("Test Description: User provides valid information, generating a new customer.")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Jaime Torres")
    @Story("User story 434532, Sprint 65 ")
    @Name("TC-Register-01 Register new customer successful")
    public void registerNewCustomer(Method method) throws InterruptedException{
        homePage.goToRegisterOption();
        Log.info("Enter the menú Register and fill Out Form");
        dependenceRegisterPage.fillOutFormToNewCustomer(faker.name().firstName(), faker.name().lastName(), email, company, pass);
        dependenceRegisterPage.completeRegister();
        /*   test = extent.createTest("Register new customer successful", "User provides valid information, generating a new customer.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional");
        test.assignDevice("Win 10");  */

        Log.debug("Assertion to validate correct registration");
        WebElement labelAccount = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='result']")));
        Assert.assertEquals(labelAccount.getText(),expected_result);
        System.out.println("The account has been created with Email: " + email + " and Password: " + pass);
    }

}