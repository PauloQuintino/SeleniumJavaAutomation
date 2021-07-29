package datafiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class ExcelApiTest {

	public FileInputStream fis = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;
	public XSSFRow row = null;
	public XSSFCell cell = null;

	public ExcelApiTest() throws Exception {
		String xFilePath = "src/test/resources/DataFiles/TestData.xlsx";	
		fis = new FileInputStream(xFilePath);
		workbook = new XSSFWorkbook(fis);
		fis.close();
	}

	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);

			// alterado metodo depreciado
			if (cell.getCellType() == CellType.STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				String cellValue = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
					Date date = cell.getDateCellValue();
					cellValue = dt.format(date);
				}
				return cellValue;
			} else if (cell.getCellType() == CellType.BLANK) {
				return "";
			} else {
				return String.valueOf(cell.getBooleanCellValue());
			}
		} catch (Exception e) {
			return "Não encontrado";
		}

	}

	@SuppressWarnings("deprecation")
	public String getCellData(String sheetName, String colName, int rowNum) {
		int col_Num = -1;
		try {

			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}

			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(col_Num);

			if (cell.getCellType() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				String cellValue = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					DateFormat df = new SimpleDateFormat("dd/MM/yy");
					Date date = cell.getDateCellValue();
					cellValue = df.format(date);
				}
				return cellValue;
			} else if (cell.getCellTypeEnum() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			return "row " + rowNum + " or column " + col_Num + " does not exist  in Excel";
		}
	}

	public String getDtByCT(String ct, String colName) {
		try {
			sheet = workbook.getSheet("Plan1");
			String valor = "";

			for (Row row : sheet) {
				cell = (XSSFCell) row.getCell(0);
				if (cell.getStringCellValue().equalsIgnoreCase(ct)) {
					System.out.println("CT capturado: " + cell.getStringCellValue());
					for (int i = 0; i <= cell.getRow().getLastCellNum() - 1; i++) {
						System.out.println("valor capturado: " + cell.getRow().getCell(i).getStringCellValue());
						valor = cell.getRow().getCell(i).getStringCellValue();
						System.out.println("valor: " + valor);
					}
				}
			}
			return "VALUE: " + valor;

		} catch (Exception e) {
			return "NOT FOUND";
		}
	}

	public int findRow(String cellContent) {
		sheet = workbook.getSheet("Plan1");
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getRichStringCellValue().getString().equals(cellContent)) {
					return row.getRowNum();
				}
			}
		}
		return 0;
	}

	public void findRowValue(String ct, String colName) {
		sheet = workbook.getSheet("Plan1");
		row = sheet.getRow(0);
		cell = row.getCell(0);

		int col = 0;
		int line = 0;

		List<String> values = new ArrayList<String>();

		for (int i = 0; i <= row.getLastCellNum() - 1; i++) {
			if (cell.getRow().getCell(i).getStringCellValue().equals(ct)) {
				values.add(cell.getStringCellValue());
				line = cell.getRow().getRowNum();
				System.out.println("Row capturada:" + line);
			}

			System.out.println(values.toString());
		}
	}


	public XSSFRow test(String colName, String textToFind) {
		sheet = workbook.getSheet("Plan1");
		int colIndex = 0;
		for (int colNum = 0; colNum <= sheet.getRow(0).getLastCellNum(); colNum++) {
			if (sheet.getRow(0).getCell(colNum).toString().equalsIgnoreCase(colName)) {
				System.out.println("colName capturado:" + sheet.getRow(0).getCell(colNum).toString());
				colIndex = colNum;
				break;
			}
		}
		for (int RowNum = 0; RowNum < sheet.getLastRowNum(); RowNum++) {
			if (sheet.getRow(RowNum).getCell(colIndex).toString().equalsIgnoreCase(textToFind)) {
				System.out.println("textToFind capturado:" + sheet.getRow(RowNum).getCell(colIndex).toString());
				return sheet.getRow(RowNum);
			}
		}
		System.out.println("No any row found that contains " + textToFind);
		return null;
	}

	public static Map<String, Map<String, String>> getCSVData(char seperator, String FileName, int primaryKeyIndex)
			throws IOException {

		//Create one object of Map type which hold another String (Key-Column as Key) and Map as as value.( for each row corresponding key and values).
		Map<String, Map<String, String>> CSVData = new TreeMap<String, Map<String, String>>();
		Map<String, String> keyVals = null;
		
		//Load CSV file in Reader class to parse CSV Data // arquivo CSV
		Reader reader = new FileReader("src/test/resources/DataFiles/TestData.csv"); 

		//Identify CSV separator (comma delimitor, pipe separated etc.) Create Iterator to iterate each CSV row over column and row.
		Iterator<Map<String, String>> iterator = new CsvMapper().readerFor(Map.class)
				.with(CsvSchema.emptySchema().withHeader().withColumnSeparator(seperator).withoutQuoteChar())
				.readValues(reader);

		//Iterate iterator up to each row and put values in step 4 Map i.e CSVData.
		while (iterator.hasNext()) {
			keyVals = iterator.next();
			Object[] keys = keyVals.keySet().toArray();
			CSVData.put(keyVals.get(keys[primaryKeyIndex]), keyVals);
		}
		//Return the CSVData Map as parse Data.
		return CSVData;
	}

	
	public String getDt(String ct, String colName) {
		
		//map which holds data in Key (String) and values(Map- Column Name as Key and Value).
		Map<String, Map<String, String>> CSV;
		
		try {
			
			//calling the getCSVData method passing the parse CSV Data
			CSV = getCSVData(';', "ParseCsv", 0);
			
			//Iterate above collections over each Key
			Iterator<String> keys = CSV.keySet().iterator();

			while (keys.hasNext()) {
				String primaryKey = keys.next();

				
				//Get value using CT name and column Name
				if (primaryKey.equals(ct)) {
					
					//if value were found, then return column value
					return CSV.get(primaryKey).get(colName).toString();
				}
			}

		} catch (IOException e) {

		}
		return "NOT FOUND";
	}

}
