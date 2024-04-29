package com.commerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DependenceOrderPage extends BasePage {

    public WebDriver driver;

    //Elements
    By btnCategoriesCompu = By.xpath("(//a[@href='/computers'])[1]");
    By btnNotebooks = By.xpath("(//a[@href='/notebooks'])[4]");
    By imgHPEnvy = By.xpath("//img[@src='https://demo.nopcommerce.com/images/thumbs/0000030_hp-envy-6-1180ca-156-inch-sleekbook_415.jpeg']");
    By btnAddToCartHPEnvy = By.id("add-to-cart-button-8");
    By btnCategoriesElectro = By.xpath("(//a[@href='/electronics'])[1]");
    By btnCellphones = By.xpath("(//a[@href='/cell-phones'])[4]");
    By imgCellNokia = By.xpath("//img[@src='https://demo.nopcommerce.com/images/thumbs/0000044_nokia-lumia-1020_415.jpeg']");
    By btnAddToCarCellNokia = By.id("add-to-cart-button-20");
    By btnClose= By.xpath("//span[@class='close']");
    By btnShoppingCart= By.xpath("//a[contains(text(),'Shopping cart')]");

    //Builder
    public DependenceOrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    //Methods
    public void addProductsToCart(){
        click(btnCategoriesCompu);
        click(btnNotebooks);
        click(imgHPEnvy);
        click(btnAddToCartHPEnvy);
        click(btnCategoriesElectro);
        click(btnCellphones);
        click(imgCellNokia);
        click(btnAddToCarCellNokia);
        click(btnClose);
        click(btnShoppingCart);
    }
}