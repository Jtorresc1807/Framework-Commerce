package com.commerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    //Builder
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    //Methods
    public void signInWithCredentialsValids(){
        type(inpEmail, "jaimebrs8@gmail.com");
        type(inpPass, "B@$u7ztyM*O4o8");
        click(inpRememberMe);
    }

    public void signInWithCredentialsIncorrects(){
        type(inpEmail, "manuela.xyz@hotmail.com");
        type(inpPass, "PassIncorrect123");
        click(inpRememberMe);
    }

    public void signInWithNullCredentials(){
        type(inpEmail, "");
        type(inpPass, "");
    }

    public void signInWithUnregisteredEmail(){
        type(inpEmail, "emailunregistered@notexist.com");
        type(inpPass, "123");
    }

    public void signInToForgottenPassword(){
        click(btnForgotPass);
        type(inpEmail, "thi.upton@yahoo.com");
        click(btnRecover);
    }

    public void completeLogin(){
        click(btnLogIn);
    }
}