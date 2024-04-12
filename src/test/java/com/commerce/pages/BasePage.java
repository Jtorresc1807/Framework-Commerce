package com.commerce.pages;

import com.commerce.logs.Log;
import com.commerce.utils.Variables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
//#1
public class BasePage {     //Se crean los metodos para reutilizar en todo el proyecto

    WebDriver driver;       //Este driver no tiene la instancia creada del BaseTest linea 20 pasa el driver a RegisterPage
    public WebDriverWait wait;     //Utilizado en el metodo isDisplay

    int timeOutSec = 10;

    public int TIME_OUT = 10;

    //Builder                                   //Constructor definido
    public BasePage(WebDriver driver){          //Driver para pasar por el constructor
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutSec));
    }

    //Methods
    public void openPage(String url){               //Metodo para cargar la pagina
        Log.info("Maximize the Page");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Variables.TIME_OUT));
        driver.get(url);
    }

    public WebElement find(By element){         //Funcion para buscar que retorna el driver.findElement
        return driver.findElement(element);
    }

    public void type(By element, String text){          //Metodo que tiene la accion de escribir
        find(element).sendKeys(text);
    }

    public void click(By element){                  //Metodo para hacer click
        find(element).click();
    }

    public void submit(By element){                 //Metodo para hacer click en formularios
        find(element).submit();
    }

    public void clear(By element){                 //Metodo para
        find(element).clear();
    }

    public boolean isDisplay(By locator){           //Espera hasta que un elemento sea visible (ExpectedConditions tiene varias opciones para usar...)
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));     //El metodo visibilityOfElementLocated resive un locator
        }catch (Exception e){
            return false;
        }
        return true;
    }
}