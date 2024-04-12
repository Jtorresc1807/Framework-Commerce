package com.commerce.tests;

import com.commerce.logs.Log;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
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
//#5
@Listeners(com.commerce.listeners.CustomListeners.class)
public class RegisterTest extends BaseTest{

    Faker faker = new Faker(new Locale("en-US"));

    String expected_result = "Your registration completed";
    String email = faker.internet().emailAddress();
    String email1 = "jaimebrs8@gmail.com";
    String email2 = "correogmail.kom";
    String email6 = email1;
    String company = faker.company().name();
    String password = faker.internet().password(10, 15, true, true, true);
    String pass = faker.internet().password(2,5, true, false, false);
    String pass2 = "";
    String pass3 = faker.internet().password(3,4, true, false, false);

    @Test(testName = "TC-Register-01 Register new customer successful", priority = 1, groups = "Functional")
    @Description("Test Description: User provides valid information, generating a new customer.")
    public void registerNewCustomerWithRequiredData(Method method) throws InterruptedException{
        homePage.goToRegisterOption();
        Log.info("Enter the menú Register and fill Out Form");
        registerPage.fillOutFormWithFakeDataMale(faker.name().firstName(), faker.name().lastName(), email1, company, password);
        registerPage.completeRegister();
        test = extent.createTest("Register new customer successful", "User provides valid information, generating a new customer.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional");
        test.assignDevice("Win 10");

        Log.debug("Assertion to validate correct registration");
        WebElement labelAccount = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='result']")));
        Assert.assertEquals(labelAccount.getText(),expected_result);
        System.out.println("The account has been created with Email: " + email1 + " and Password: " + password);
    }

    @Test(testName = "TC-Register-02 Registration with missing mandatory fields", priority = 2, groups = {"Functional", "Integration"})
    @Description("Test Description: Verify when the user tries to register without completing required fields.")
    public void registerNewCustomerWithoutMandatoryFields(Method method){
        homePage.goToRegisterOption();
        Log.info("Enter the menú Register and fill Out Form");
        registerPage.fillFormWithoutMandatoryFields(company);
        registerPage.completeRegister();
        test = extent.createTest("Registration with missing mandatory fields", "Verify when the user tries to register without completing required fields.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional", "Integration");
        test.assignDevice("Win 11");

        Log.debug("Assertion to validate register without mandatory fields");
        WebElement labelError = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("FirstName-error")));
        Assert.assertTrue(labelError.isDisplayed());
        System.out.println("First name, Last name, Email, and Password is required");
    }

    @Test(testName = "TC-Register-03 Valid email format", priority = 3, groups = {"Functional", "Regression"})
    @Description("Test Description: Validate email address, rejecting incorrect or incomplete addresses.")
    public void ValidateEmailFormat(Method method){
        homePage.goToRegisterOption();
        Log.info("Enter the menú Register and fill Out Form");
        registerPage.fillFormToValidEmailFormat(faker.name().firstName(), faker.name().lastName(), email2, company, password);
        registerPage.completeRegister();
        test = extent.createTest("Valid email format", "Validate email address, rejecting incorrect or incomplete addresses.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional", "Regression");
        test.assignDevice("Win 9");

        Log.debug("Assertion to validate Valid email format");
        WebElement labelError = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Wrong email')]")));
        Assert.assertTrue(labelError.isDisplayed());
        System.out.println("The email used is: " + email2 + " and haven´t valid format ");
    }

    @Test(testName = "TC-Register-04 Secure password", priority = 4, groups = "Regression")
    @Description("Test Description: Validates that it meets criteria (length, combination of characters, etc.) and rejects weak passwords.")
    public void ValidateSecurePassword(Method method){
        homePage.goToRegisterOption();
        Log.info("Enter the menú Register and fill Out Form");
        registerPage.fillFormToValidPasswordSecure(faker.name().firstName(), faker.name().lastName(), email, company, pass);
        registerPage.completeRegister();
        test = extent.createTest("Secure password", "Validates that it meets criteria (length, combination of characters, etc.) and rejects weak passwords.");
        test.assignAuthor("Jaime");
        test.assignCategory("Regression");
        test.assignDevice("Win 8");

        Log.debug("Assertion to validate secure password");
        WebElement labelError = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("Password-error")));
        Assert.assertTrue(labelError.isDisplayed());
        System.out.println("The password used is: " + pass + " and isn't a secure password, change password");
    }

    @Test(testName = "TC-Register-05 Confirmation password", priority = 5, groups = {"Functional", "Integration"})
    @Description("Test Description: Confirmation password during registration and matching passwords to complete the process.")
    public void ValidateConfirmationPassword(Method method){
        homePage.goToRegisterOption();
        Log.info("Enter the menú Register and fill Out Form");
        registerPage.fillFormToValidConfirmationPassword(faker.name().firstName(), faker.name().lastName(), email, company, pass, pass2, pass3);
        registerPage.completeRegister();
        test = extent.createTest("Confirmation password", "Confirmation password during registration and matching passwords to complete the process.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional", "Integration");
        test.assignDevice("Win Xp");

        Log.debug("Assertion to validate password null");
        WebElement labelError = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("ConfirmPassword-error")));
        Assert.assertTrue(labelError.isDisplayed());
        System.out.println("The password used is: " + pass + " and confirmation password is: " + pass2 + " null, Password is required.");

        Log.debug("Assertion to validate Valid Confirmation password");
        WebElement labelError2 = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("ConfirmPassword-error")));
        Assert.assertTrue(labelError2.isDisplayed());
        System.out.println("The password used is: " + pass + " and confirmation password is: " + pass3 + " the passwords do not match.");
    }

    @Test(testName = "TC-Register-06 Unique Email", priority = 6, groups = "Integration")
    @Description("Test Description: validate that the e-mail address address provided during registration is not already associated to another customer in the database, avoiding duplicate database, avoiding duplicate registrations.")
    public void ValidateRegisterWhitUniqueEmail(Method method){
        homePage.goToRegisterOption();
        Log.info("Enter the menú Register and fill Out Form");
        registerPage.fillOutFormToRegisterWhitUniqueEmail(faker.name().firstName(), faker.name().lastName(), email6, company, password);
        registerPage.completeRegister();
        test = extent.createTest("Unique Email", "validate that the e-mail address address provided during registration is not already associated to another customer in the database, avoiding duplicate database, avoiding duplicate registrations.");
        test.assignAuthor("Jaime");
        test.assignCategory("Integration");
        test.assignDevice("Win 98");

        Log.debug("Assertion to validate register Whit unique email");
        WebElement labelError = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='message-error validation-summary-errors']")));
        Assert.assertTrue(labelError.isDisplayed());
        System.out.println("The email used is: " + email6 + ", email associated with another customer.");

    }
}