package Hooks;

import Core.Configuration;
import Evidence.EvidenceGenerator;
import Page.BasePage;
import com.itextpdf.text.DocumentException;
import datafiles.TestDataReader;
import io.cucumber.java.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static Core.DriverFactory.getDriver;
import static Core.DriverFactory.killDriver;


public class Hooks {
    private static TestDataReader data = new TestDataReader();
    public static Logger LOG = LogManager.getLogger(Hooks.class);
    private static EvidenceGenerator evidenceGenerator = new EvidenceGenerator();

    private static int scenarioPassed = 0;

    @BeforeAll
    public static void beforeAll() {
        LOG.info("===== BEFORE ALL =====");
    }

    @Before
    public void setup(Scenario scenario) {
        Object[] arrayTags = scenario.getSourceTagNames().toArray();
        String ct = "";

        for (Object tag : arrayTags) {
            ct = tag.toString().replace("@", "");
            data.setCtKey(ct);
            System.out.println("******************************************");
            System.out.println("CT - " + data.getCtKey());
        }
        data.setCtName(scenario.getName());
        LOG.info("\n========================\nFeature name: " + scenario.getName() + "\n========================");
        LOG.info("\n========================\nCT: " + data.getCtKey() + "\n========================");
        LOG.info("SET UP AUTOMATION");

        getDriver().get(Configuration.getInstance().getProperty("baseUrl"));
    }

    @After
    public void quitDriver(Scenario scenario) throws DocumentException, IOException {
        evidenceGenerator.setStatus(scenario.getStatus().toString());
        evidenceGenerator.takeScreenshot("TEAR DOWN");
        evidenceGenerator.saveEvidence();

        if (scenario.getStatus().toString().equals("PASSED"))
            scenarioPassed++;

        killDriver();
    }

    @AfterAll
    public static void afterAll() {
        LOG.info("===== AFTER ALL =====");
        LOG.info("SCENARIO PASSED = " + scenarioPassed);
    }

}
