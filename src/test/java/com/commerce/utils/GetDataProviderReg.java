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

public class GetDataProviderReg {

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
            int noOfCols = row.getLastCellNum(); // obtenemos el numero de columnas (7)

            data = new String[noOfRows - 1][noOfCols]; // definimos el numero el tamaño del array a retornar; (4,7);

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

    public static String[] getDataFromJson() throws IOException, ParseException {

        JSONParser jsonParser = new JSONParser();
        FileReader fileReader  = new FileReader(System.getProperty("user.dir") +"/src/test/resources/data/register.json");
        Object object = new JSONParser().parse(fileReader);

        JSONObject jsonObject = (JSONObject) object;
        JSONArray jsonArray = (JSONArray) jsonObject.get("register");


        String  data[] = new String[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject register = (JSONObject) jsonArray.get(i);

            String fname = (String) register.get("firstname");
            String lname = (String) register.get("lastname");
            String email = (String) register.get("email");
            String company = (String) register.get("company");
            String pass = (String) register.get("password");
            String rpass = (String) register.get("confirmpassword");
            String msg = (String) register.get("message");

            data[i] = fname+","+ lname+","+ email+","+ company+","+ pass+","+ rpass+","+ msg;
        }

        return data;
    }
}
