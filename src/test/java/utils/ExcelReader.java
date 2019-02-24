package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {

    private static FileInputStream excelFile;
    private static XSSFWorkbook excelWBook;
    private static XSSFSheet excelWSheet;
    private static XSSFCell cell;
    private static XSSFRow row;
    public static int rowNumber;
    public static int columnNumber;

    public static int getRowNumber() {
        return rowNumber;
    }

    public static int getColumnNumber() {
        return columnNumber;
    }

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static XSSFRow getRowData(int RowNum) throws Exception {
        try {
            excelFile = new FileInputStream("d:/git/selendroidtestapp/src/test/java/resources/testData.xlsx");
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet("register");
            row = excelWSheet.getRow(RowNum);

            return row;
        } catch (Exception e) {
            throw (e);
        }
    }
}