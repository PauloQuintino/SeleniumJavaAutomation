package Hooks;

import datafiles.TestDataReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static Core.DriverFactory.getDriver;
import static Core.DriverFactory.killDriver;


public class Hooks {

    private static TestDataReader data = new TestDataReader();
    public static Logger LOG = Logger.getLogger(Hooks.class);

    @Before()
    public static void setup(Scenario scenario) {
        BasicConfigurator.configure();
        PropertyConfigurator.configure("Log4j.properties");
        LOG.info("SET UP AUTOMATION");
        getDriver().get("http://automationpractice.com/index.php?");
        LOG.info("\n========================\nFeature name: " + scenario.getName() + "\n========================");

        Object[] arrayTags = scenario.getSourceTagNames().toArray();
        String ct = "";

        for (Object tag : arrayTags) {
            ct = tag.toString().replace("@", "");
            data.setCtKey(ct);
        }

        LOG.info("\n========================\nCT: " + data.getCtKey() + "\n========================");
    }

    @After
    public static void quitDriver(Scenario scenario) {
        killDriver();
    }

}
