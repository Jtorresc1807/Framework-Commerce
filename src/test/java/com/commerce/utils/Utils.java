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

    static String ScreenName2 = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

    public static String getScreenshotForReport(WebDriver driver, String ScreenName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot)driver;

        File srcImage = ts.getScreenshotAs(OutputType.FILE);
        String temp = System.getProperty("user.dir")+"/screenshot/fails/"+ScreenName+"_"+ScreenName2+".png";
        File destination = new File(temp);
        FileUtils.copyFile(srcImage, destination);

        return temp;
    }
}