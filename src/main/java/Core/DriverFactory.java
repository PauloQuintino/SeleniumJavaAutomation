package Core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {
    private static WebDriver driver = null;

    public static WebDriver getDriver() {

        if (driver == null) {
            switch (BrowserSettings.browser) {
                case CHROME:
                    driver = WebDriverManager.chromedriver().create();
                    break;
                case HEADLESS:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    break;
                case OPERA:
                    driver = WebDriverManager.operadriver().create();
                    break;
                case EDGE:
                    driver = WebDriverManager.edgedriver().create();
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(Configuration.getInstance().getProperty("waitDuration"))));
        return driver;
    }

    public static void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
