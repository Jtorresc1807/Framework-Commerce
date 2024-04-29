package com.commerce.pages;

import com.commerce.logs.Log;
import com.commerce.tests.BaseTest;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

//#9
public class OrderPage extends BasePage {

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

    By btnCategoriesApparel = By.xpath("(//a[@href='/apparel'])[1]");
    By btnShoes = By.xpath("(//img[@alt='Picture for category Shoes'])[1]");
    By imgShoesNike = By.xpath("//img[@src='https://demo.nopcommerce.com/images/thumbs/0000056_nike-sb-zoom-stefan-janoski-medium-mint_415.jpg']");
    By btnAddToShoesNike = By.id("add-to-cart-button-26");

    By assertNotificationAdded = By.xpath("//p[@class='content']");
    By btnClose= By.xpath("//span[@class='close']");
    By btnShoppingCart= By.xpath("//a[contains(text(),'Shopping cart')]");
    By btnShoppingCartRemove = By.xpath("//table[@class='cart']//td[@class='remove-from-cart']//button[@class='remove-btn']");
    By inpQuantity = By.xpath("//input[@class='qty-input']");
    By inpAddQuantity = By.xpath("//input[@aria-label='Qty.']");
    By btnUpdateShopCart = By.id("updatecart");
    By assertUpdateAdd = By.xpath("//input[@value='5']");
    By assertUpdateRed = By.xpath("//input[@value='2']");
    By assertEmpty = By.cssSelector("div[class='no-data']");

    By inpAgreeTerms = By.id("termsofservice");
    By btnCheckout = By.id("checkout");
    By selCountry = By.xpath("//select[@data-trigger='country-select']");       //Para utilizar en el OrderTest
    By inpCity = By.id("BillingNewAddress_City");
    By inpAddress1 = By.id("BillingNewAddress_Address1");
    By inpAddress2 = By.id("BillingNewAddress_Address2");
    By inpPostalCode = By.id("BillingNewAddress_ZipPostalCode");
    By inpPhoneNumber = By.id("BillingNewAddress_PhoneNumber");
    By inpFaxNumber = By.id("BillingNewAddress_FaxNumber");
    By btnContinueBillingAddress = By.name("save");
    By inpShippingMethod = By.xpath("//ul[@class='method-list']");      //Para utilizar en el OrderTest
    By radNextDayAir = By.xpath("//input[@value='Next Day Air___Shipping.FixedByWeightByTotal']");      //Para utilizar en el OrderTest
    By btnContinueShippingMethod = By.xpath("//button[@class='button-1 shipping-method-next-step-button']");
    By inpPaymentMethod = By.id("payment-method-block");
    By radCreditCard = By.xpath("//input[@value='Payments.Manual']");
    By btnContinuePaymentMethod = By.xpath("//button[@class='button-1 payment-method-next-step-button']");
    By selCreditCard = By.id("CreditCardType");
    By inpCardholderName = By.id("CardholderName");
    By inpCardNumber = By.id("CardNumber");         // 123456862234
    By selExpireMonth = By.id("ExpireMonth");
    By selExpireYear = By.id("ExpireYear");
    By inpCardCode = By.id("CardCode");
    By btnContinuePaymentInformation = By.xpath("//button[@class='button-1 payment-info-next-step-button']");
    By btnConfirmOrder = By.xpath("//button[@class='button-1 confirm-order-next-step-button']");
    By assertPurchaseOrder = By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]");

    //Builder
    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    //Methods
    public void addProductsToCart(){
        Log.info("Add Products To Cart on Nop Commerce Web with Data Provider.");
        Allure.step("Add Products To Cart on Nop Commerce Web with Data Provider.");
        click(btnCategoriesCompu);
        click(btnNotebooks);
        click(imgHPEnvy);
        click(btnAddToCartHPEnvy);
        click(btnCategoriesElectro);
        click(btnCellphones);
        click(imgCellNokia);
        click(btnAddToCarCellNokia);
        click(btnCategoriesApparel);
        click(btnShoes);
        click(imgShoesNike);
        click(btnAddToShoesNike);
        click(btnClose);
    }

    public void removeProductsToCart(){
        Log.info("Remove Products To Cart on Nop Commerce Web with Data Provider.");
        Allure.step("Remove Products To Cart on Nop Commerce Web with Data Provider.");
        click(btnShoppingCart);
        click(btnShoppingCartRemove);
        click(btnShoppingCartRemove);
        click(btnShoppingCartRemove);
    }

    public void increaseQuantityProducts(){
        Log.info("Increase Quantity Products To Cart on Nop Commerce Web with Data Provider.");
        Allure.step("Increase Quantity Products To Cart on Nop Commerce Web with Data Provider.");
        click(btnShoppingCart);
        clear(inpQuantity);
        type(inpAddQuantity, "5");
        click(btnUpdateShopCart);
    }

    public void reduceQuantityProducts(){
        //click(btnShoppingCart);
        Log.info("Reduce Quantity Products To Cart on Nop Commerce Web with Data Provider.");
        Allure.step("Reduce Quantity Products To Cart on Nop Commerce Web with Data Provider.");
        clear(inpQuantity);
        type(inpAddQuantity, "2");
        click(btnUpdateShopCart);
    }

    public void orderProductsCreation() {
        Log.info("order Products To Cart on Nop Commerce Web with Data Provider.");
        Allure.step("Order Products To Cart on Nop Commerce Web with Data Provider.");
        click(btnShoppingCart);
        click(inpAgreeTerms);
        click(btnCheckout);
    }

    public void orderCkechoutBillingAddress() {
        Log.info("Fill out form to order Ckechout Billing Address on Nop Commerce Web with Data Provider.");
        Allure.step("Fill out form to order Ckechout Billing Address on Nop Commerce Web with Data Provider.");

        wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT));
        WebElement selcountry =  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("BillingNewAddress_CountryId")));
        assertTrue(selcountry.getAttribute("data-trigger").toString().contains("country"));

        WebElement country = driver.findElement(By.xpath("//select[@data-trigger='country-select']"));
        Select cbCountry = new Select(country);
        cbCountry.selectByValue("173");
        type(inpCity, "Bucaramanga");
        type(inpAddress1, "Calle falsa 123");
        type(inpAddress2, "Avenida siempre viva");
        type(inpPostalCode, "07507");
        type(inpPhoneNumber, "301987654321");
        type(inpFaxNumber, "086-87654");
        click(btnContinueBillingAddress);
    }

    public void orderCkechoutShippinAndPaymentgMethod() {
        Log.info("Fill out form to order Ckechout Shippin And Payment Method on Nop Commerce Web with Data Provider.");
        Allure.step("Fill out form to order Ckechout Shippin And Payment Method on Nop Commerce Web with Data Provider.");
        click(radNextDayAir);
        click(btnContinueShippingMethod);
        click(radCreditCard);
        click(btnContinuePaymentMethod);
    }

    public void orderCkechoutPaymentInformation() {
        Log.info("Fill out form to order Ckechout Payment Information on Nop Commerce Web with Data Provider.");
        Allure.step("Fill out form to order Ckechout Payment Information on Nop Commerce Web with Data Provider.");
        WebElement creditcard = driver.findElement(selCreditCard);
        Select cbcreditcard = new Select(creditcard);
        cbcreditcard.selectByValue("MasterCard");
        type(inpCardholderName, "Yeka");
        type(inpCardNumber, "123456862234");
        WebElement expirationdatemonth = driver.findElement(selExpireMonth);
        Select cbexpirationdatemonth = new Select(expirationdatemonth);
        cbexpirationdatemonth.selectByValue("7");
        WebElement expirationdateyear = driver.findElement(selExpireYear);
        Select cbexpirationdateyear = new Select(expirationdateyear);
        cbexpirationdateyear.selectByValue("2030");
        type(inpCardCode, "1234");
        click(btnContinuePaymentInformation);
        click(btnConfirmOrder);
    }

}