package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
	
	    private static Workbook workbook;
	    private static Sheet sheet;

	    public static void setExcelFile(String filePath, String sheetName) throws IOException {
	        FileInputStream fis = new FileInputStream(filePath);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheet(sheetName);
	    }

	    public static String getCellData(int rowNum, int colNum) {
	        Row row = sheet.getRow(rowNum);
	        Cell cell = row.getCell(colNum);
	        return cell.toString();
	    }

	    public static int getRowCount() {
	        return sheet.getLastRowNum() + 1; // +1 because rows start from 0
	    }

	    public static int getColumnCount() {
	        return sheet.getRow(0).getLastCellNum();
	    }
}


