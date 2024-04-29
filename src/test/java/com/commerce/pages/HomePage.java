package com.commerce.pages;

import com.commerce.logs.Log;
import com.commerce.utils.Variables;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//#3
public class HomePage extends BasePage {

    WebDriver driver ;           //driver instanciado por el driver que llega al constructor

    //Elements
    By linkLogIn = By.linkText("Log in");
    By linkRegister = By.linkText("Register");

    //Builder
    public HomePage(WebDriver driver) {     //Constructor se llama igual que la clasey es unico por cada clase
        super(driver);                      //La herencia que esta pasando de la clase padre BasePage llega de manera implicita
        Log.info("Opening Nop Commerce Web.");
        Allure.step("Opening Nop Commerce Web.");
        openPage(Variables.BASE_URL);       //Metodo openPage buscado por LoginTest, RegisterTest y HomeTest
    }

    //Methods
    public void goToRegisterOption(){
        Log.info("Going to Register menu option");
        Allure.step("Going to Register menu option");
        click(linkRegister);
    }

    public void goToSignIn(){
        Log.info("Going to SingIn menu option");
        Allure.step("Going to SingIn menu option");
        click(linkLogIn);
    }
}