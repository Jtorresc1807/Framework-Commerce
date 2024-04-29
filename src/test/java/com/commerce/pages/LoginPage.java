package com.commerce.pages;

import com.commerce.logs.Log;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//#7
public class LoginPage extends BasePage{

    WebDriver driver;

    //Elements
    By inpEmail = By.id("Email");
    By inpPass = By.id("Password");
    By inpRememberMe = By.id("RememberMe");
    By btnForgotPass = By.cssSelector("span[class='forgot-password']");
    By btnLogIn = By.xpath("//div[@class='buttons']//button[@type='submit']");
    By btnRecover = By.name("send-email");
    public By labelloginsucces = By.xpath("//div[@class='topic-block-title']");
    public By labelloginWrong  = By.xpath("//div[@class='message-error validation-summary-errors']");

    //Builder
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    //Methods
    public void signInWithCredentialsValids(){
        Log.info("Login on Nop Commerce Web with Email :" + inpEmail + " Password :" + inpPass);
        Allure.step("Login on Nop Commerce Web with Email :" + inpEmail + " Password :" + inpPass);
        type(inpEmail, "jaimebrs8@gmail.com");
        type(inpPass, "B@$u7ztyM*O4o8");
        click(inpRememberMe);
    }

    public void signInWithCredentialsIncorrects(){
        Log.info("Login on Nop Commerce Web with Email :" + inpEmail + " Password :" + inpPass);
        Allure.step("Login on Nop Commerce Web with Email :" + inpEmail + " Password :" + inpPass);
        type(inpEmail, "manuela.xyz@hotmail.com");
        type(inpPass, "PassIncorrect123");
        click(inpRememberMe);
    }

    public void signInWithNullCredentials(){
        Log.info("Login on Nop Commerce Web with Email :" + inpEmail + " Password :" + inpPass);
        Allure.step("Login on Nop Commerce Web with Email :" + inpEmail + " Password :" + inpPass);
        type(inpEmail, "");
        type(inpPass, "");
    }

    public void signInWithUnregisteredEmail(){
        Log.info("Login on Nop Commerce Web with Email :" + inpEmail + " Password :" + inpPass);
        Allure.step("Login on Nop Commerce Web with Email :" + inpEmail + " Password :" + inpPass);
        type(inpEmail, "emailunregistered@notexist.com");
        type(inpPass, "123");
    }

    public void signInToForgottenPassword(){
        Log.info("Password recovery on Nop Commerce Web with Email :" + inpEmail);
        Allure.step("Password recovery on Nop Commerce Web with Email :" + inpEmail );
        click(btnForgotPass);
        type(inpEmail, "thi.upton@yahoo.com");
        click(btnRecover);
    }

    public void fillOutFormToLoginWhitDataProvider(String email, String pass) {
        Log.info("Login on Nop Commerce Web with Data Provider");
        Allure.step("Login on Nop Commerce Web with Data Provider");
        driver.findElement(inpEmail).sendKeys(email);
        driver.findElement(inpPass).sendKeys(pass);
    }

    public void completeLogin(){
        click(btnLogIn);
        Log.info("Entering your profile");
        Allure.step("Entering your profile");
    }


}