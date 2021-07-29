package datafiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExcel {

	public void getValue() throws IOException {

		File excelFilePath = new File("src/test/resources/DataFiles/TestData.xlsx");
		FileInputStream inputStream = new FileInputStream(excelFilePath);

		XSSFWorkbook wb = new XSSFWorkbook(inputStream);

		XSSFSheet sheet = wb.getSheet("Plan1");

		// get all rows in the sheet
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		// iterate over all the row to print the data present in each cell.
		for (int i = 0; i <= rowCount; i++) {

			// get cell count in a row
			int cellcount = sheet.getRow(i).getLastCellNum();

			// iterate over each cell to print its value
			System.out.println("Row" + i + " data is :");

			for (int j = 0; j < cellcount; j++) {
				System.out.print(sheet.getRow(i).getCell(j).getStringCellValue() + ",");
			}
			System.out.println();
		}

	}

	public void getValues(String ct, String value) throws IOException {

		File excelFilePath = new File("src/test/resources/DataFiles/TestData.xlsx");
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheet("Plan1");
		String address = "";

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		for (int i = 0; i <= rowCount; i++) {
			XSSFRow row2 = sheet.getRow(i);
			if (row2.getCell(i).equals(ct)) {
				for (int j = 0; j <= rowCount; j++) {
					XSSFCell cell = row2.getCell(j);
					if (cell.getRow().equals(value)) {
						address = cell.getStringCellValue();
					}
				}
			}

		}

		// Printing the address
		System.out.println("Text :" + address);

	}

}
