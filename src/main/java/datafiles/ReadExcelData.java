package datafiles;

import java.util.Collection;
import java.util.List;

import io.cucumber.java.Scenario;


public class ReadExcelData {

	public static void main(String[] args) throws Exception {

		String excelFilePath = "src/test/resources/DataFiles/TestData.xlsx";		
		ExcelApiTest eat = new ExcelApiTest();
		
		System.out.println(eat.getDt("login-csv", "User"));

	}
	
	
	public String getFeatureFileNameFromScenarioId(Scenario scenario) {
	    String featureName = "Feature ";
	    String rawFeatureName = scenario.getId().split(";")[0].replace("-"," ");
	    featureName = featureName + rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);
	    return featureName;
	}
	
	public String getFeatureTagName(Scenario scenario) {		
		Collection<String> findFirst = scenario.getSourceTagNames();
		String tag = findFirst.iterator().next();
	    return tag;
	}

}
