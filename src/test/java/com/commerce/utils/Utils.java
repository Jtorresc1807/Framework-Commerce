package com.commerce.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    static String ScreenName2 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());          // Crea una cadena llamada ScreenName2 en formato yyyyMMddhhmmss

    public static String getScreenshotForReport(WebDriver driver, String ScreenName) throws IOException {       //Metodo con dos parametros Un objeto WebDriver y ScreenName: Un string parte del nombre y una excepción IOException si hay un problema al guardar la captura de pantalla.

        TakesScreenshot ts = (TakesScreenshot)driver;       // Convierte el objeto WebDriver a TakesScreenshot para poder tomar capturas de pantalla

        File srcImage = ts.getScreenshotAs(OutputType.FILE);        // Toma una captura de pantalla y la guarda en un archivo temporal
        String temp = System.getProperty("user.dir")+"/screenshot/fails/"+ScreenName+"_"+ScreenName2+".png";        // Construye la ruta completa donde se guardará la captura de pantalla
        File destination = new File(temp);      // Crea un objeto File con la ruta construida
        FileUtils.copyFile(srcImage, destination);      // Copia el archivo temporal de la captura de pantalla al destino especificado

        return temp;        // Devuelve la ruta completa donde se guardó la captura de pantalla
    }
}