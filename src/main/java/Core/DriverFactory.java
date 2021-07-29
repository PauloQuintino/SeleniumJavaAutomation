package Core;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {
            switch (BrowserSettings.browser) {
                case CHROME:
//				System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                    break;

                case HEADLESS:
                    ChromeOptions options = new ChromeOptions();
                    System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
            }
        }
        return driver;
    }

    public static void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
