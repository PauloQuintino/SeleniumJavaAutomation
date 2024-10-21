package datafiles;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TestDataReader {

    public FileInputStream fis = null;
    public XSSFWorkbook workbook = null;
    private static String ctKey;
    private static String ctName;

    public TestDataReader() {
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        TestDataReader.ctName = ctName;
    }

    public String getCtKey() {
        return ctKey;
    }

    public void setCtKey(String ctKey) {
        TestDataReader.ctKey = ctKey;
    }


    public static Map<String, Map<String, String>> getCSVData(char seperator, String FileName, int primaryKeyIndex)
            throws IOException {

        //Cria um objeto do tipo Map que contém outra String (Key-Column como Key) e Map como value.(para cada linha, chave e valores correspondentes).
        Map<String, Map<String, String>> CSVData = new TreeMap<String, Map<String, String>>();
        Map<String, String> keyVals = null;

        //Carrega o arquivo CSV na classe Reader para analisar os dados CSV // arquivo CSV
        Reader reader = new FileReader("src/test/resources/DataFiles/TestData.csv");

        //Identifica o separador CSV (delimitador por vírgula, separado por barra vertical, etc.) Cria Iterator para iterar cada linha CSV baseado em coluna e linha.
        Iterator<Map<String, String>> iterator = new CsvMapper().readerFor(Map.class)
                .with(CsvSchema.emptySchema().withHeader().withColumnSeparator(seperator).withoutQuoteChar())
                .readValues(reader);

        //Itera o iterador a cada linha e coloca valores no Map, ou seja, CSVData.
        while (iterator.hasNext()) {
            keyVals = iterator.next();
            Object[] keys = keyVals.keySet().toArray();
            CSVData.put(keyVals.get(keys[primaryKeyIndex]), keyVals);
        }

        //Retorna os dados do Map.
        return CSVData;
    }


    public String getDt(String ct, String colName) {
        //map que contém dados em Key (String) e valores(Map-Nome da Colunaq como Key e Value)
        Map<String, Map<String, String>> CSV;

        try {

            //chamando o método getCSVData passando o parse CSV Data
            CSV = getCSVData(';', "ParseCsv", 0);

            //Itera as coleções acima de cada chave
            Iterator<String> keys = CSV.keySet().iterator();

            while (keys.hasNext()) {
                String primaryKey = keys.next();

                // Obtém o valor usando o nome do CT e o nome da coluna
                if (primaryKey.equals(ct)) {

                    //se o valor foi encontrado, então retorna o valor da coluna
                    return CSV.get(primaryKey).get(colName).toString();
                }
            }

        } catch (IOException e) {

        }
        return "NOT FOUND";
    }

    public String getDt(String colName) {

        //map que contém dados em Key (String) e valores(Map-Nome da Coluna como Key e Value)
        Map<String, Map<String, String>> CSV;

        try {

            //chamando o método getCSVData passando o parse CSVData para informar qual é o delimitador de dados no CSV
            CSV = getCSVData(';', "ParseCsv", 0);

            //Itera as coleções acima de cada chave
            Iterator<String> keys = CSV.keySet().iterator();

            while (keys.hasNext()) {
                String primaryKey = keys.next();


                // Obtém o valor usando o nome do CT e o nome da coluna
                if (primaryKey.equals(getCtKey())) {

                    //se o valor foi encontrado, então retorna o valor da coluna
                    return CSV.get(primaryKey).get(colName).toString();
                }
            }

        } catch (IOException e) {

        }
        return "NOT FOUND";
    }

}
