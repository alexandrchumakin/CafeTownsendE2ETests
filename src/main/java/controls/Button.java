package controls;

import driver.Driver;
import driver.WebDriverHelper;
import interfaces.IClickable;

public class Button extends BaseControl implements IClickable {
    private static final String template = "//a[contains(@class, 'subButton') and text()='%1$s']";

    public Button(String controlLabel) {
        super(new String[]{template}, controlLabel);
    }

    public void click() {
        WebDriverHelper.findElement(byLocator, 5).click();
        Driver.waitPageSource();
    }
}
