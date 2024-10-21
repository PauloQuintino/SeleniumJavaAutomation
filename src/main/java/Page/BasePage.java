package Page;

import datafiles.TestDataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static Core.DriverFactory.getDriver;

public class BasePage {

    public static Logger LOG = LogManager.getLogger(BasePage.class);
    public static TestDataReader dataReader = new TestDataReader();

    // ========== MOUSE ACTIONS ========== //

    public void mouseOverOnElement(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element, 50, 50).perform();
    }


    // ========== SCROLL ========== //

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,285)");
    }

    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0,-885)");
    }

    public void scrollToText(String texto) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true)", getDriver().findElement(By.partialLinkText(texto)));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.documentElement.clientHeight));", element);

    }


}
