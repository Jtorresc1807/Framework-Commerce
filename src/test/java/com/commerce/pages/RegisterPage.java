package com.commerce.pages;

import com.commerce.logs.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

//#4
public class RegisterPage extends BasePage{

    WebDriver driver;

    //Elements
    By inpGenderM = By.id("gender-male");
    By inpGenderF = By.id("gender-female");
    By inpFname = By.id("FirstName");
    By inpLname = By.id("LastName");
    By selectDay = By.name("DateOfBirthDay");
    By selectMonth = By.name("DateOfBirthMonth");
    By selectYear = By.name("DateOfBirthYear");
    By inpEmail = By.id("Email");
    By inpCompany = By.id("Company");
    By inpNewsletter = By.id("Newsletter");
    By inpPassword = By.id("Password");
    By inpConfPassword = By.id("ConfirmPassword");
    By btnRegister = By.id("register-button");
    public By labelregistersucces = By.xpath("//div[@class='result']");
    public By labelregisterWrong  = By.xpath("//span[@id='Email-error']");
    public By labelregisterWrongPass  = By.xpath("//span[@id='Password-error']");

    public By labellogin = By.xpath("//div[@class='topic-block-title']");  //PARA EL LOGIN

    //Builder
    public RegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //Methods
    public void fillOutFormWithFakeDataMale(String fname, String lname, String email, String company, String pass){
        //click(inpGenderM);

        Log.debug("Fill out registration form success");
        WebElement content_gender = driver.findElement(By.xpath("//div[@id='gender']"));
        content_gender.findElement(By.xpath("//input[@value='F']")).click();
        type(inpFname, fname);
        type(inpLname, lname);
        WebElement selectDay = driver.findElement(By.name("DateOfBirthDay"));
        Select cbDay = new Select(selectDay);
        cbDay.selectByValue("24");
        WebElement selectMonth = driver.findElement(By.name("DateOfBirthMonth"));
        Select cbMonth = new Select(selectMonth);
        cbMonth.selectByIndex(10);
        WebElement selectYear = driver.findElement(By.name("DateOfBirthYear"));
        Select cbYear = new Select(selectYear);
        cbYear.selectByValue("1987");
        type(inpEmail, email);
        type(inpCompany, company);
        //click(inpNewsletter);
        WebElement contentNewsletter = driver.findElement(By.id("Newsletter"));
        contentNewsletter.findElement(By.xpath("//input[@value='true']")).click();
        type(inpPassword, pass);
        type(inpConfPassword, pass);
    }

    public void fillFormWithoutMandatoryFields(String company){
        Log.debug("Fill out registration form Without Mandatory Fields");
        WebElement content_gender = driver.findElement(By.xpath("//div[@id='gender']"));
        content_gender.findElement(By.xpath("//input[@value='M']")).click();
        WebElement selectDay = driver.findElement(By.name("DateOfBirthDay"));
        Select cbDay = new Select(selectDay);
        cbDay.selectByValue("18");
        WebElement selectMonth = driver.findElement(By.name("DateOfBirthMonth"));
        Select cbMonth = new Select(selectMonth);
        cbMonth.selectByIndex(3);
        WebElement selectYear = driver.findElement(By.name("DateOfBirthYear"));
        Select cbYear = new Select(selectYear);
        cbYear.selectByValue("1995");
        type(inpCompany, company);
    }

    public void fillFormToValidEmailFormat(String fname, String lname, String email, String company, String pass){
        Log.debug("Fill out registration form with email no valid");
        WebElement content_gender = driver.findElement(By.xpath("//div[@id='gender']"));
        content_gender.findElement(By.xpath("//input[@value='F']")).click();
        type(inpFname, fname);
        type(inpLname, lname);
        WebElement selectDay = driver.findElement(By.name("DateOfBirthDay"));
        Select cbDay = new Select(selectDay);
        cbDay.selectByValue("3");
        WebElement selectMonth = driver.findElement(By.name("DateOfBirthMonth"));
        Select cbMonth = new Select(selectMonth);
        cbMonth.selectByIndex(5);
        WebElement selectYear = driver.findElement(By.name("DateOfBirthYear"));
        Select cbYear = new Select(selectYear);
        cbYear.selectByValue("2000");
        type(inpEmail, email);
        type(inpCompany, company);
        WebElement contentNewsletter = driver.findElement(By.id("Newsletter"));
        contentNewsletter.findElement(By.xpath("//input[@value='true']")).click();
        type(inpPassword, pass);
        type(inpConfPassword, pass);
    }

    public void fillFormToValidPasswordSecure(String fname, String lname, String email, String company, String pass){
        Log.debug("Fill out registration form whith password no secure");
        WebElement content_gender = driver.findElement(By.xpath("//div[@id='gender']"));
        content_gender.findElement(By.xpath("//input[@value='F']")).click();
        type(inpFname, fname);
        type(inpLname, lname);
        WebElement selectDay = driver.findElement(By.name("DateOfBirthDay"));
        Select cbDay = new Select(selectDay);
        cbDay.selectByValue("15");
        WebElement selectMonth = driver.findElement(By.name("DateOfBirthMonth"));
        Select cbMonth = new Select(selectMonth);
        cbMonth.selectByIndex(8);
        WebElement selectYear = driver.findElement(By.name("DateOfBirthYear"));
        Select cbYear = new Select(selectYear);
        cbYear.selectByValue("1980");
        type(inpEmail, email);
        type(inpCompany, company);
        WebElement contentNewsletter = driver.findElement(By.id("Newsletter"));
        contentNewsletter.findElement(By.xpath("//input[@value='true']")).click();
        type(inpPassword, pass);
        type(inpConfPassword, pass);
    }

    public void fillFormToValidConfirmationPassword(String fname, String lname, String email, String company, String pass, String pass2, String pass3){
        Log.debug("Fill out registration form to confirmation password");
        WebElement content_gender = driver.findElement(By.xpath("//div[@id='gender']"));
        content_gender.findElement(By.xpath("//input[@value='F']")).click();
        type(inpFname, fname);
        type(inpLname, lname);
        WebElement selectDay = driver.findElement(By.name("DateOfBirthDay"));
        Select cbDay = new Select(selectDay);
        cbDay.selectByValue("27");
        WebElement selectMonth = driver.findElement(By.name("DateOfBirthMonth"));
        Select cbMonth = new Select(selectMonth);
        cbMonth.selectByIndex(1);
        WebElement selectYear = driver.findElement(By.name("DateOfBirthYear"));
        Select cbYear = new Select(selectYear);
        cbYear.selectByValue("1985");
        type(inpEmail, email);
        type(inpCompany, company);
        type(inpPassword, pass);
        type(inpConfPassword, pass2);
        type(inpConfPassword, pass3);
    }

    public void fillOutFormToRegisterWhitUniqueEmail(String fname, String lname, String email, String company, String pass) {
        Log.debug("Fill out registration form whit unique email");
        WebElement content_gender = driver.findElement(By.xpath("//div[@id='gender']"));
        content_gender.findElement(By.xpath("//input[@value='M']")).click();
        type(inpFname, fname);
        type(inpLname, lname);
        WebElement selectDay = driver.findElement(By.name("DateOfBirthDay"));
        Select cbDay = new Select(selectDay);
        cbDay.selectByValue("12");
        WebElement selectMonth = driver.findElement(By.name("DateOfBirthMonth"));
        Select cbMonth = new Select(selectMonth);
        cbMonth.selectByIndex(7);
        WebElement selectYear = driver.findElement(By.name("DateOfBirthYear"));
        Select cbYear = new Select(selectYear);
        cbYear.selectByValue("1995");
        type(inpEmail, email);
        type(inpCompany, company);
        type(inpPassword, pass);
        type(inpConfPassword, pass);
    }

    public void completeRegister(){
        submit(btnRegister);
    }

    public void fillOutFormToRegisterWhitDataProvider(String fname, String lname, String email, String company, String pass, String rpass) {
        Log.debug("Fill out registration form success");
        driver.findElement(inpFname).sendKeys(fname);
        driver.findElement(inpLname).sendKeys(lname);
        WebElement selectDay = driver.findElement(By.name("DateOfBirthDay"));
        Select cbDay = new Select(selectDay);
        cbDay.selectByValue("24");
        WebElement selectMonth = driver.findElement(By.name("DateOfBirthMonth"));
        Select cbMonth = new Select(selectMonth);
        cbMonth.selectByIndex(10);
        WebElement selectYear = driver.findElement(By.name("DateOfBirthYear"));
        Select cbYear = new Select(selectYear);
        cbYear.selectByValue("1987");
        driver.findElement(inpEmail).sendKeys(email);
        driver.findElement(inpCompany).sendKeys(company);
        driver.findElement(inpPassword).sendKeys(pass);
        driver.findElement(inpConfPassword).sendKeys(rpass);
    }
}