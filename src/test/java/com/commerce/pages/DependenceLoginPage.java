package com.commerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//#7
public class DependenceLoginPage extends BasePage{

    WebDriver driver;

    //Elements
    By inpEmail = By.id("Email");
    By inpPass = By.id("Password");
    By inpRememberMe = By.id("RememberMe");
    By btnLogIn = By.xpath("//div[@class='buttons']//button[@type='submit']");

    //Builder
    public DependenceLoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    //Methods
    public void signInWithCredentials(){
        type(inpEmail, "jaimebrs4@gmail.com");
        type(inpPass, "A2wns0#04*#j");
        click(inpRememberMe);
    }

    public void completeLog(){
        click(btnLogIn);
    }
}