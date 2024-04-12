package com.commerce.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import static com.commerce.utils.Variables.TIME_OUT;
//6
public class HomeTest extends BaseTest{

    String expected_label = "Welcome to our store";

    @Test
    public void verifyThatBeInHomePage(){
        //Logs.debug("Assertion para validar que estoy en el Home Page");
        WebElement labelHomePage = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='topic-block-title']//h2")));
        Assert.assertTrue(labelHomePage.isDisplayed());
        Assert.assertEquals(labelHomePage.getText(),expected_label);
    }
}
