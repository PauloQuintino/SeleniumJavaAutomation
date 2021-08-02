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

	public ExcelApiTest() throws Exception {
		String xFilePath = "src/test/resources/DataFiles/TestData.xlsx";	
		fis = new FileInputStream(xFilePath);
		workbook = new XSSFWorkbook(fis);
		fis.close();
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
