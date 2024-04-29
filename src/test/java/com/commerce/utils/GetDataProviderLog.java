package com.commerce.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetDataProviderLog {

    public Object[][] getDataFromCSV() {

        return null;
    }

    public static Object[][] getDataFromExcel(String pathFile, String sheetName) {

        String[][] data = null;
        XSSFCell cell;

        try {
            FileInputStream fis = new FileInputStream(pathFile); // paso el path del archivo
            XSSFWorkbook workBook = new XSSFWorkbook(fis); // cargo el archivo
            XSSFSheet workSheet = workBook.getSheet(sheetName); // accedemos a la hoja del excel
            XSSFRow row = workSheet.getRow(0); // leemos primera fila

            int noOfRows = workSheet.getPhysicalNumberOfRows(); // numero de filas incluidas el titulo; (5)
            int noOfCols = row.getLastCellNum(); // obtenemos el numero de columnas (3)

            data = new String[noOfRows - 1][noOfCols]; // definimos el numero el tamaño del array a retornar; (4,3);

            for (int i = 1; i < noOfRows; i++) { // recorremos desde la fila 1 hasta la 4
                for (int j = 0; j < noOfCols; j++) { // recorremos las columnas

                    row = workSheet.getRow(i);
                    cell = row.getCell(j);

                    data[i - 1][j] = cell.getStringCellValue(); // llenar el array con la data del excel
                }
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return data;
    }

    // Método estático que devuelve un arreglo de cadenas con datos obtenidos de un archivo JSON
    public static String[] getDataFromJson() throws IOException, ParseException {
        // Crea una instancia de JSONParser para analizar el contenido JSON
        JSONParser jsonParser = new JSONParser();
        // Obtiene la ruta del archivo JSON a partir del directorio de usuario actual
        FileReader fileReader  = new FileReader(System.getProperty("user.dir") +"/src/test/resources/data/users.json");
        Object object = new JSONParser().parse(fileReader);     // Analiza el contenido del archivo JSON y lo convierte en un objeto

        JSONObject jsonObject = (JSONObject) object;            // Convierte el objeto en un JSONObject
        JSONArray jsonArray = (JSONArray) jsonObject.get("users");      // Obtiene el JSONArray llamado "users" del JSONObject


        String  data[] = new String[jsonArray.size()];      // Crea un nuevo arreglo de cadenas con el mismo tamaño que el JSONArray

        for (int i = 0; i < jsonArray.size(); i++) {        // Itera sobre cada elemento del JSONArray, (JSONArray = 2)

            JSONObject users = (JSONObject) jsonArray.get(i);       // Obtiene el JSONObject en la posición i del JSONArray
            // Obtiene los valores de las claves "username", "password" y "message" del JSONObject
            String user = (String) users.get("username");
            String pass = (String) users.get("password");
            String msg = (String) users.get("message");

            data[i] = user+","+ pass+","+ msg;      // Construye una cadena con los valores obtenidos y la asigna al arreglo de datos
        }

        return data;            // Devuelve el arreglo de cadenas con los datos obtenidos del archivo JSON
    }
}
