package Hooks;

import static Core.DriverFactory.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import datafiles.ReadExcelData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {
		

	@Before()
	public static void setup(Scenario scenario) {
//		getDriver().get("http://automationpractice.com/index.php?");

        ReadExcelData red = new ReadExcelData();
        System.out.println("Feature name: " + scenario.getName());
	}
		
	@After
	public static void quitDriver(Scenario scenario) {
		killDriver();
	}

}
