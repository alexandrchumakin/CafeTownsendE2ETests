package controls;

import driver.Driver;
import driver.WebDriverHelper;
import interfaces.IClickable;

public class SubmitButton extends BaseControl implements IClickable {
    private static final String template1 = "//button[@type='submit' and text()='%1$s']";
    private static final String template2 = "//p[@class='main-button' and text()='%1$s']";

    public SubmitButton(String controlLabel) {
        super(new String[]{template1, template2}, controlLabel);
    }

    public void click() {
        WebDriverHelper.findElement(byLocator, 5).click();
        Driver.waitPageSource();
    }
}
