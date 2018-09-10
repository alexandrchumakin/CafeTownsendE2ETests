package controls;

import driver.WebDriverHelper;
import interfaces.IValuable;
import org.openqa.selenium.WebElement;

public class TextBox extends BaseControl implements IValuable {
    private static final String template = "//span[contains(text(), '%1$s')]/../input";

    public TextBox(String controlLabel) {
        super(new String[]{template}, controlLabel);
    }

    public void setValue(String value) {
        WebElement field = WebDriverHelper.findElement(byLocator, 5);
        field.clear();
        field.sendKeys(value);
    }

    public String getValue() {
        return WebDriverHelper.findElement(byLocator, 5).getAttribute("value").trim();
    }
}
