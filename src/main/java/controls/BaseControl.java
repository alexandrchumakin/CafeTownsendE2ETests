package controls;

import driver.WebDriverHelper;
import interfaces.IControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BaseControl implements IControl {
    protected By byLocator;
    protected String controlLabel;
    protected String[] xpathPatterns;

    public BaseControl(String[] xpathPatterns, String controlLabel) {
        this.xpathPatterns = xpathPatterns;
        this.controlLabel = controlLabel;
        generateLocator();
    }

    protected void generateLocator(){
        for (String xpath: this.xpathPatterns){
            this.byLocator = By.xpath(String.format(xpath, this.controlLabel));
            WebElement element = WebDriverHelper.findElement(byLocator, 3);
            if (element != null && element.isEnabled()){ return; }
        }

    }

    public boolean isEnabled() {
        return WebDriverHelper.findElement(byLocator, 10).isEnabled();
    }
}
