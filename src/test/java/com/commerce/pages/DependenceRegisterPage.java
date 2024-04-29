package com.commerce.pages;

import com.commerce.logs.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//#4
public class DependenceRegisterPage extends BasePage{

    WebDriver driver;

    //Elements
    By inpFname = By.id("FirstName");
    By inpLname = By.id("LastName");
    By inpEmail = By.id("Email");
    By inpCompany = By.id("Company");
    By inpPassword = By.id("Password");
    By inpConfPassword = By.id("ConfirmPassword");
    By btnRegister = By.id("register-button");

    //Builder
    public DependenceRegisterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //Methods
    public void fillOutFormToNewCustomer(String fname, String lname, String email, String company, String pass){
        //click(inpGenderM);
        Log.debug("Fill out registration form success");
        WebElement content_gender = driver.findElement(By.xpath("//div[@id='gender']"));
        content_gender.findElement(By.xpath("//input[@value='M']")).click();
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

    public void completeRegister(){
        submit(btnRegister);
    }
}