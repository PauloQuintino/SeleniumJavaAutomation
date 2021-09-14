package Hooks;

import Core.Configuration;
import datafiles.TestDataReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.HashMap;
import java.util.Map;

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


        //creating a map to insert the environment variables
        Map<String, String> configVars = new HashMap<String, String>(){
            {
                //add to map URL_PROJECT with same name of OS environment variable
                put("baseUrl", "URL_PROJECT");
            }
        };

        Configuration config = Configuration.getInstance();

        //iterating the Map to insert System Environment Variables into config baseUrl
        for(Map.Entry<String, String> entry : configVars.entrySet()){
            if(System.getenv(entry.getValue()) != null){
                config.setProperty(entry.getKey(), System.getenv(entry.getValue()));
            }
        }

        if(config.hasProperty("baseUrl")){
//            getDriver().get("http://automationpractice.com/index.php?");
            getDriver().get(Configuration.getInstance().getProperty("baseUrl"));
        }


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
