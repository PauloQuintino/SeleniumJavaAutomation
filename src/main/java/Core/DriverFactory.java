package Core;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {

    private static WebDriver driver = null;

    public static WebDriver getDriver() {

        if (driver == null) {
            switch (BrowserSettings.browser) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//                    WebDriverManager.chromedriver().setup();
//                    driver = new ChromeDriver();
                    driver = Serenity.getWebdriverManager().getWebdriver();
                    break;

                case HEADLESS:
                    ChromeOptions options = new ChromeOptions();
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
            }
        }
//        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    public static void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
