package Page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static Core.DriverFactory.getDriver;

public class BasePage {

    // ========== MOUSE ACTIONS ========== //

    public void mouseOverOnElement(WebElement element){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
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


}
