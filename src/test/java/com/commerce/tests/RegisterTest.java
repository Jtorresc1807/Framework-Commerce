package com.commerce.tests;

import com.commerce.logs.Log;
import com.commerce.utils.GetDataProviderReg;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Name;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Locale;
import static com.commerce.utils.Variables.TIME_OUT;
//#5
@Listeners(com.commerce.listeners.CustomListeners.class)
@Epic("Epic - Register Module")
@Feature("Feature - Register validation for the application demo.nopcommerce.com with different test cases")
public class RegisterTest extends BaseTest{       //Para ejecutar con docker BaseTestDocker

    Faker faker = new Faker(new Locale("en-US"));

    String expected_result = "Your registration completed";
    String email = faker.internet().emailAddress();
    String email1 = "jaimebrs@gmail.com";
    String email2 = "correogmail.kom";
    String email6 = email1;
    String company = faker.company().name();
    String password = faker.internet().password(10, 15, true, true, true);
    String pass = faker.internet().password(2,5, true, false, false);
    String pass2 = "";
    String pass3 = faker.internet().password(3,4, true, false, false);

    @Test(testName = "TC-Register-01 Register new customer successful", priority = 1, groups = "Functional")
    @Description("Test Description: User provides valid information, generating a new customer.")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Jaime Torres")
    @Story("User story 412332, Sprint 65 ")
    @Name("TC-Register-01 Register new customer successful")
    public void registerNewCustomerWithRequiredData(Method method) throws InterruptedException{
        Allure.step("Go To Register Option");
        homePage.goToRegisterOption();
        Log.info("Enter the menú Register and fill Out Form");
        Allure.step("Enter the menú Register and fill Out Form");
        registerPage.fillOutFormWithFakeDataMale(faker.name().firstName(), faker.name().lastName(), email1, company, password);
        Allure.step("Click on the button Register to Complete the register Test");
        registerPage.completeRegister();
        /* test = extent.createTest("Register new customer successful", "User provides valid information, generating a new customer.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional");
        test.assignDevice("Win 10");  */
        Allure.step("Assert to validate the register...");

        Log.debug("Assertion to validate correct registration");
        WebElement labelAccount = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='result']")));
        Assert.assertEquals(labelAccount.getText(),expected_result);
        System.out.println("The account has been created with Email: " + email1 + " and Password: " + password);
    }

    @Test(testName = "TC-Register-02 Registration with missing mandatory fields", priority = 2, groups = {"Functional", "Integration"})
    @Description("Test Description: Verify when the user tries to register without completing required fields.")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Jaime Torres")
    @Story("User story 490872, Sprint 65 ")
    @Name("TC-Register-02 Registration with missing mandatory fields")
    public void registerNewCustomerWithoutMandatoryFields(Method method){
        Allure.step("Go To Register Option");
        homePage.goToRegisterOption();
        Log.info("Enter the menú Register and fill Out Form");
        Allure.step("Enter the menú Register and fill Out Form");
        registerPage.fillFormWithoutMandatoryFields(company);
        Allure.step("Click on the button Register to Complete the register Test");
        registerPage.completeRegister();
        /* test = extent.createTest("Registration with missing mandatory fields", "Verify when the user tries to register without completing required fields.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional", "Integration");
        test.assignDevice("Win 11");  */
        Allure.step("Assert to validate the register...");

        Log.debug("Assertion to validate register without mandatory fields");
        WebElement labelError = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("FirstName-error")));
        Assert.assertTrue(labelError.isDisplayed());
        System.out.println("First name, Last name, Email, and Password is required");
    }

    @Test(testName = "TC-Register-03 Valid email format", priority = 3, groups = {"Functional", "Regression"})
    @Description("Test Description: Validate email address, rejecting incorrect or incomplete addresses.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Jaime Torres")
    @Story("User story 429876, Sprint 65 ")
    @Name("TC-Register-03 Valid email format")
    public void ValidateEmailFormat(Method method){
        Allure.step("Go To Register Option");
        homePage.goToRegisterOption();
        Allure.step("Enter the menú Register and fill Out Form");
        Log.info("Enter the menú Register and fill Out Form");
        registerPage.fillFormToValidEmailFormat(faker.name().firstName(), faker.name().lastName(), email2, company, password);
        Allure.step("Click on the button Register to Complete the register Test");
        registerPage.completeRegister();
        /* test = extent.createTest("Valid email format", "Validate email address, rejecting incorrect or incomplete addresses.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional", "Regression");
        test.assignDevice("Win 9");  */
        Allure.step("Assert to validate the register...");
        Log.debug("Assertion to validate password null");

        WebElement labelError = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Wrong email')]")));
        Assert.assertTrue(labelError.isDisplayed());
        System.out.println("The email used is: " + email2 + " and haven´t valid format ");
    }

    @Test(testName = "TC-Register-04 Secure password", priority = 4, groups = "Regression")
    @Description("Test Description: Validates that it meets criteria (length, combination of characters, etc.) and rejects weak passwords.")
    @Severity(SeverityLevel.MINOR)
    @Owner("Jaime Torres")
    @Story("User story 412356, Sprint 65 ")
    @Name("TC-Register-04 Secure password")
    public void ValidateSecurePassword(Method method){
        Allure.step("Go To Register Option");
        homePage.goToRegisterOption();
        Allure.step("Enter the menú Register and fill Out Form");
        Log.info("Enter the menú Register and fill Out Form");
        registerPage.fillFormToValidPasswordSecure(faker.name().firstName(), faker.name().lastName(), email, company, pass);
        Allure.step("Click on the button Register to Complete the register Test");
        registerPage.completeRegister();
        /* test = extent.createTest("Secure password", "Validates that it meets criteria (length, combination of characters, etc.) and rejects weak passwords.");
        test.assignAuthor("Jaime");
        test.assignCategory("Regression");
        test.assignDevice("Win 8");  */
        Allure.step("Assert to validate the register...");
        Log.debug("Assertion to validate password null");

        WebElement labelError = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("Password-error")));
        Assert.assertTrue(labelError.isDisplayed());
        System.out.println("The password used is: " + pass + " and isn't a secure password, change password");
    }

    @Test(testName = "TC-Register-05 Confirmation password", priority = 5, groups = {"Functional", "Integration"})
    @Description("Test Description: Confirmation password during registration and matching passwords to complete the process.")
    @Severity(SeverityLevel.TRIVIAL)
    @Owner("Jaime Torres")
    @Story("User story 476543, Sprint 65")
    @Name("TC-Register-05 Confirmation password")
    public void ValidateConfirmationPassword(Method method){
        Allure.step("Go To Register Option");
        homePage.goToRegisterOption();
        Allure.step("Enter the menú Register and fill Out Form");
        Log.info("Enter the menú Register and fill Out Form");
        registerPage.fillFormToValidConfirmationPassword(faker.name().firstName(), faker.name().lastName(), email, company, pass, pass2, pass3);
        Allure.step("Click on the button Register to Complete the register Test");
        registerPage.completeRegister();
        /* test = extent.createTest("Confirmation password", "Confirmation password during registration and matching passwords to complete the process.");
        test.assignAuthor("Jaime");
        test.assignCategory("Functional", "Integration");
        test.assignDevice("Win Xp");  */
        Allure.step("Assert to validate the register...");
        Log.debug("Assertion to validate password null");

        WebElement labelError = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("ConfirmPassword-error")));
        Assert.assertTrue(labelError.isDisplayed());
        System.out.println("The password used is: " + pass + " and confirmation password is: " + pass2 + " null, Password is required.");
        Allure.step("Assert to validate the register...");

        Log.debug("Assertion to validate Valid Confirmation password");
        WebElement labelError2 = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("ConfirmPassword-error")));
        Assert.assertTrue(labelError2.isDisplayed());
        System.out.println("The password used is: " + pass + " and confirmation password is: " + pass3 + " the passwords do not match.");
    }

    @Test(testName = "TC-Register-06 Unique Email", priority = 6, groups = "Integration")
    @Description("Test Description: validate that the e-mail address address provided during registration is not already associated to another customer in the database, avoiding duplicate database, avoiding duplicate registrations.")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Jaime Torres")
    @Story("User story 399987, Sprint 65")
    @Name("TC-Register-06 Unique Email")
    public void ValidateRegisterWhitUniqueEmail(Method method){
        Allure.step("Go To Register Option");
        homePage.goToRegisterOption();
        Allure.step("Enter the menú Register and fill Out Form");
        Log.info("Enter the menú Register and fill Out Form");
        registerPage.fillOutFormToRegisterWhitUniqueEmail(faker.name().firstName(), faker.name().lastName(), email6, company, password);
        Allure.step("Click on the button Register to Complete the register Test");
        registerPage.completeRegister();
        /* test = extent.createTest("Unique Email", "validate that the e-mail address address provided during registration is not already associated to another customer in the database, avoiding duplicate database, avoiding duplicate registrations.");
        test.assignAuthor("Jaime");
        test.assignCategory("Integration");
        test.assignDevice("Win 98");  */
        Allure.step("Assert to validate the register...");

        Log.debug("Assertion to validate register Whit unique email");
        WebElement labelError = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='message-error validation-summary-errors']")));
        Assert.assertTrue(labelError.isDisplayed());
        System.out.println("The email used is: " + email6 + ", email associated with another customer.");
    }

    @Test(testName = "TC-Register-07 Register new customer with data provider Excel", priority = 7, groups = "Integration",
            dataProvider = "dataProviderRegister")
    @Description("Test Description: validate new register customer with data provider Excel")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Jaime Torres")
    @Story("User story 399990, Sprint 66")
    @Name("TC-Register-07 Register new customer with data provider Excel")
    public void doRegisterE(String fname, String lname, String email, String company, String pass, String rpass, String msg) {
        Allure.step("Go To Register Option");
        homePage.goToRegisterOption();
        Allure.step("Fill Out Form To Register Whit Data Provider Excel");
        registerPage.fillOutFormToRegisterWhitDataProvider(fname, lname, email, company, pass, rpass);
        Allure.step("Click on the button Register to Complete the register Test");
        registerPage.completeRegister();
        Allure.step("Assert to validate the register...");

        WebElement header = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                    //   .until(ExpectedConditions.presenceOfElementLocated(registerPage.labelregistersucces));
                         .until(ExpectedConditions.presenceOfElementLocated(registerPage.labelregisterWrong));
        Assert.assertEquals(header.getText(), msg);
    }

    @DataProvider(name = "dataProviderRegister")
    public Object[][] dataProviderRegisterFromExcel() {
        Object[][] arrayData = GetDataProviderReg.getDataFromExcel(System.getProperty("user.dir")+"/src/test/resources/data/data.xlsx", "register");
        return arrayData;
    }

    @Test(testName = "TC-Login-08 Register with data provider Json", priority = 8, groups = "Integration",
            dataProvider = "dataProviderRegisterJ")
    @Description("Test Description: validate Register with data provider Json")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Jaime Torres")
    @Story("User story 323999, Sprint 66")
    @Name("TC-Login-06 Register with data provider Json")
    public void doRegisterJ(String data) {
        String register[] = data.split(",");
        Allure.step("Go To Register Option");
        homePage.goToRegisterOption();
        Allure.step("Fill Out Form To Register Whit Data Provider Json");
        registerPage.fillOutFormToRegisterWhitDataProvider(register[0], register[1], register[2], register[3], register[4], register[5]);
        Allure.step("Click on the button Register to Complete the register Test");
        registerPage.completeRegister();
        Allure.step("Assert to validate the register...");


        // assertion
        WebElement header = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                //      .until(ExpectedConditions.presenceOfElementLocated(registerPage.labelregistersucces));
                //      .until(ExpectedConditions.presenceOfElementLocated(registerPage.labelregisterWrong));
                        .until(ExpectedConditions.presenceOfElementLocated(registerPage.labelregisterWrongPass));

        AssertJUnit.assertEquals(header.getText(), register[6]);
    }

    @DataProvider(name = "dataProviderRegisterJ")
    public String[] dataProviderRegisterFromJson() throws IOException, ParseException {
        String[] arrayData = GetDataProviderReg.getDataFromJson();
        return arrayData;
    }

}