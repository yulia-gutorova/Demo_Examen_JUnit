package base;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static base.SetUp.driver;
import static base.SetUp.writer;

public class CommonMethods {

    public static Boolean status;

    /*---------------------------------------------------------------------
     * Method sleep() to take a pause in execution
     *---------------------------------------------------------------------*/
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /*---------------------------------------------------------------------
     * Method printToLogFile() to print information in the log file
     *---------------------------------------------------------------------*/
    public static void printToLogFile(String str1, String srt2)
    {
        writer.println(str1 + srt2);
    }


    /*---------------------------------------------------------------------
     * Method openHomePage() to land on a particular home page
     *---------------------------------------------------------------------*/
    public static boolean openHomePage(String url)
    {
        driver.get(url);
        printToLogFile("\tGiven URL is: ", url);
        if (url.equals(driver.getCurrentUrl()))
        {
            status = true;
            printToLogFile("\tURL is right: ", status.toString());
        }
        else status= false;
        return status;
    }

    /*---------------------------------------------------------------------
     * Method currentURL() to get current url
     *---------------------------------------------------------------------*/
    public  static String currentURL()
    {
        String currentURL = driver.getCurrentUrl();
        //printToLogFile("\tCurrent URL is: ", currentURL);
        return currentURL;
    }

    /*---------------------------------------------------------------------
     * Method getTextFromExcel() to read text information from Excel file
     *---------------------------------------------------------------------*/
    public static String getTextFromExcel(String fileName, String sheetName, int row, int column) throws IOException {

        FileInputStream inputStream = new FileInputStream(fileName);
        XSSFWorkbook wb=new XSSFWorkbook(inputStream);

        XSSFSheet sheet=wb.getSheet(sheetName);
        XSSFRow row2=sheet.getRow(row);
        XSSFCell cell=row2.getCell(column);
        String value= cell.getStringCellValue();
        return value;
    }

    /*---------------------------------------------------------------------
     * Method getNumberFromExcel() to read numeric information from Excel file
     *---------------------------------------------------------------------*/
    public static int getNumberFromExcel(String fileName, String sheetName, int row, int column) throws IOException {

        FileInputStream inputStream = new FileInputStream(fileName);
        XSSFWorkbook wb=new XSSFWorkbook(inputStream);

        XSSFSheet sheet=wb.getSheet(sheetName);
        XSSFRow row2=sheet.getRow(row);
        XSSFCell cell=row2.getCell(column);

        double value = cell.getNumericCellValue();
        int resultValue = (int) Math.round(value);
        return resultValue;
    }

    /*---------------------------------------------------------------------
     * Method getURLFromProperties() to read url from properties by key
     *---------------------------------------------------------------------*/
    public static String getURLFromProperties(String key) throws IOException {

        Properties urlProps = new Properties();
        urlProps.load(new FileInputStream("src\\test\\java\\pageURLs.properties"));

        String url = urlProps.getProperty(key);
        //System.out.println("URL is: " + url);
        return url;
    }

    /*---------------------------------------------------------------------
     * Method getDataSourcesFromProperties() to read data sources from properties by key
     *---------------------------------------------------------------------*/
    public static String getDataSourcesFromProperties(String key) throws IOException {

        Properties dataSourceProps = new Properties();
        dataSourceProps.load(new FileInputStream("src\\test\\java\\dataSource.properties"));

        String dataSourse = dataSourceProps.getProperty(key);
        //System.out.println("URL is: " + url);
        return dataSourse;
    }

}
