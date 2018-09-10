package controls;

import driver.Driver;
import driver.WebDriverHelper;
import interfaces.IReadOnly;
import org.openqa.selenium.Alert;

public class ErrorAlert implements IReadOnly{
    private Alert alert;

    public ErrorAlert(){
        alert = WebDriverHelper.getAlert(10);
    }

    public String getValue() {
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public boolean isEnabled() {
        return !alert.getText().equals("");
    }
}
