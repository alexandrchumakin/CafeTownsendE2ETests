package driver;

import common.ExtendedString;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverHelper {
    public static WebElement findElement(By by, int timeoutInSeconds){
        WebDriverWait waiter = new WebDriverWait(Driver.CurrentDriver, timeoutInSeconds);
        WebElement element = null;
        try{
            waiter.until( ExpectedConditions.presenceOfElementLocated(by) );
            element = Driver.CurrentDriver.findElement(by);
            scrollToElement(element);    // try to make focus
        } catch(Exception ex){
            System.out.println(String.format("\r\nDEBUG: Cannot set focus to element with '%1$s' locator, error: '%2$s'", by, ExtendedString.formatMessage(ex)));
        }
        return element;
    }

    public static void scrollToElement(WebElement element){
        ((JavascriptExecutor) Driver.CurrentDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static Alert getAlert(int timeoutInSeconds){
        WebDriverWait waiter = new WebDriverWait(Driver.CurrentDriver, timeoutInSeconds);
        waiter.until(ExpectedConditions.alertIsPresent());
        return Driver.CurrentDriver.switchTo().alert();
    }
}
