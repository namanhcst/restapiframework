package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

    private String filePath;

    public ExcelUtility(String filePath) {
        this.filePath = filePath;
    }

    public int getRowCount(String sheetName) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet selectedSheet = workbook.getSheet(sheetName);
            return (selectedSheet == null) ? 0 : selectedSheet.getPhysicalNumberOfRows();
        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getCellCount(String sheetName, int rowNum) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet selectedSheet = workbook.getSheet(sheetName);
            Row row = selectedSheet.getRow(rowNum);
            return (row == null) ? 0 : row.getPhysicalNumberOfCells();
        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getCellData(String sheetName, int rowNum, int cellNum) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet selectedSheet = workbook.getSheet(sheetName);
            Row row = selectedSheet.getRow(rowNum);
            Cell cell = row.getCell(cellNum);
            return (cell == null) ? "" : cell.toString();
        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setCellData(String sheetName, int rowNum, int cellNum, String data) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet selectedSheet = workbook.getSheet(sheetName);
            Row row = selectedSheet.getRow(rowNum);
            if (row == null) {
                row = selectedSheet.createRow(rowNum);
            }
            Cell cell = row.createCell(cellNum, CellType.STRING);
            cell.setCellValue(data);

            try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }
        } catch (IOException | EncryptedDocumentException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir")+"/src/test/resources/testdata.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(filePath);

        // Example usage of the methods:
        System.out.println("Row count: " + excelUtility.getRowCount("UserData"));
        System.out.println("Cell count: " + excelUtility.getCellCount("UserData", 0));
        System.out.println("Cell data: " + excelUtility.getCellData("UserData", 0, 0));

        // Set cell data example:
        excelUtility.setCellData("UserData", 0, 1, "NewData");
    }
}
