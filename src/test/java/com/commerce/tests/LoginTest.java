package com.commerce.tests;

import com.commerce.logs.Log;
import io.qameta.allure.Description;
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
import static org.assertj.core.api.Assertions.assertThat;

//#8
@Listeners(com.commerce.listeners.CustomListeners.class)
public class LoginTest extends BaseTest{

    String inpEmail = "ron.kshlerin@yahoo.com";
    String inpPass = "A2wns0#04*#j";
    String inpEmailUnregistered = "emailunregistered@notexist.com";
    String inpEmailIncorrect  = "manuela.xyz@hotmail.com";
    String inpPassIncorrect = "PassIncorrect123";
    String expected_greeting = "Welcome to our store";
    String messageCredentialIncorrects = "Login was unsuccessful. Please correct the errors and try again.\n" +
            "No customer account found";
    String messageNullCredential = "Please enter your email";
    String messagePasswordRecovery = "Email with instructions has been sent to you.";


    @Test(testName = "TC-Login-01 Login with credentials success", priority = 1, groups = {"Regression", "Integration"})
    @Description("Test Description: Provide valid credentials and the system allows access.")
    public void loginWithCredentialsValids(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In and enter valid credentials");
        loginPage.signInWithCredentialsValids();
        loginPage.completeLogin();
        System.out.println("Credential success, your email is: " + inpEmail + " and Password is: " + inpPass);
        test = extent.createTest("Login with credentials success", "Provide valid credentials and the system allows access.");
        test.assignAuthor("Jaime");
        test.assignCategory("Regression", "Integration");
        test.assignDevice("Win 10");

        Log.debug("Assertion to valid correct login");
        WebElement panelWelcome = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Welcome to our store')]")));
        Assert.assertEquals(panelWelcome.getText(),expected_greeting);
        AssertJUnit.assertEquals(driver.getTitle(), "nopCommerce demo store");
    }

    @Test(testName = "TC-Login-02 Login with credentials incorrect", priority = 2, groups = "Functional")
    @Description("Test Description: User provides incorrect credentials.")
    public void loginWithCredentialsIncorrects(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In and enter incorrect credentials");
        loginPage.signInWithCredentialsIncorrects();
        loginPage.completeLogin();
        System.out.println("Credential Incorrects, email enter is: " + inpEmailIncorrect  + " and Password is: " + inpPassIncorrect + " Login was unsuccessful, Please enter correct credentials");
        test =extent.createTest ("Login with credentials incorrect", "User provides incorrect credentials.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional");
        test.assignDevice("Win 11");

        Log.debug("Assertion to valid incorrect login");
        WebElement panelWelcome = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='message-error validation-summary-errors']")));
        Assert.assertEquals(panelWelcome.getText(),messageCredentialIncorrects);
    }

    @Test(testName = "TC-Login-03 Login with Null Credentials", priority = 3, groups = "Functional")
    @Description("Test Description: login by leaving the credential fields blank and displaying error messages indicating that both fields are required.")
    public void loginWithNullCredentials(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In and click on the LOGIN button");
        loginPage.signInWithNullCredentials();
        loginPage.completeLogin();
        System.out.println("Please enter your Email and correct Password, both fields are mandatory.");
        test = extent.createTest("Login with Null Credentials", "login by leaving the credential fields blank and displaying error messages indicating that both fields are required.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional");
        test.assignDevice("Win 8");

        Log.debug("Assertion to valid login with Null Credential");
        WebElement panelWelcome = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Please enter your email')]")));
        Assert.assertEquals(panelWelcome.getText(),messageNullCredential);
    }

    @Test(testName = "TC-Login-04 Login with Unregistered e-mail", priority = 4, groups = "Regression")
    @Description("Test Description: User tries to log in with an email address that is not registered in the database, displaying an error message indicating that the account does not exist.")
    public void loginWithUnregisteredEmail(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In and register with Unregistered e-mail");
        loginPage.signInWithUnregisteredEmail();
        loginPage.completeLogin();
        System.out.println("Email enter is: " + inpEmailUnregistered + " Please enter your Email correct.");
        test = extent.createTest("Login with Unregistered e-mail", "User tries to log in with an email address that is not registered in the database, displaying an error message indicating that the account does not exist.");
        test.assignAuthor("Jaime");
        test.assignCategory("Regression");
        test.assignDevice("Win 7");

        Log.debug("Assertion to valid login with Unregistered e-mail");
        WebElement messageValidationEmail = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='message-error validation-summary-errors']")));
        //Assert.assertEquals(messageValidationEmail.getText(),messageNullCredential);
        Assert.assertTrue(messageValidationEmail.isDisplayed());
    }

    @Test(testName = "TC-Login-05 Login with Forgotten password", priority = 5, groups = {"Functional", "Integration"})
    @Description("Test Description: Option to recover the password in case a user forgets it, and that it works correctly by sending a recovery email and allowing the user to reset their password.")
    public void loginWithForgottenPassword(Method method){
        Log.info("Go to Log In page");
        homePage.goToSignIn();
        Log.info("Enter the menú Log In to remember password");
        loginPage.signInToForgottenPassword();
        System.out.println("Your Forgotten Password has been reset. Please log in to your email and follow the instructions");
        test = extent.createTest("Login with Forgotten password", "Option to recover the password in case a user forgets it, and that it works correctly by sending a recovery email and allowing the user to reset their password.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional", "Integration");
        test.assignDevice("Win Xp");

        Log.debug("Assertion to valid Forgotten password");
        WebElement messagePasswordRecovery = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[contains(text(),'Email with instructions has been sent to you.')]")));
        //Assert.assertEquals(messageValidationEmail.getText(),messagePasswordRecovery);
        Assert.assertTrue(messagePasswordRecovery.isDisplayed());
        //assertThat(messagePasswordRecovery.getText().contains("Email with instructions has been sent to yo"));

    }
}