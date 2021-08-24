package Hooks;

import datafiles.TestDataReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static Core.DriverFactory.getDriver;
import static Core.DriverFactory.killDriver;


public class Hooks {

    private static TestDataReader data = new TestDataReader();

    @Before()
    public static void setup(Scenario scenario) {
        getDriver().get("http://automationpractice.com/index.php?");
        System.out.println("========================\nFeature name: " + scenario.getName() + "\n========================");

        Object[] arrayTags = scenario.getSourceTagNames().toArray();
        String ct = "";

        for (Object tag : arrayTags) {
            ct = tag.toString().replace("@", "");
            data.setCtKey(ct);
        }

        System.out.println("========================\nCT: " + data.getCtKey() + "\n========================");
    }

    @After
    public static void quitDriver(Scenario scenario) {
        killDriver();
    }

}
