package datafiles;

import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestReadExcel {

	public static void main(String[] args) throws Exception {

		FileInputStream fis = new FileInputStream("src/test/resources/DataFiles/TestData.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workBook.getSheet("Plan1");
		XSSFRow row = sheet.getRow(0);		
		XSSFCell cell = null;
		
		int colNum = -1;
		
		for(int i = 0; i < row.getLastCellNum(); i++) {
			if(row.getCell(i).getStringCellValue().equals("CT")) {
				colNum = i; // o indice se torna o valor da coluna
			}
		}
		
		row = sheet.getRow(1);
		cell = row.getCell(colNum);	
		
		String value = cell.getStringCellValue();
		System.out.println("CT: " + value);			
		
	}

}
