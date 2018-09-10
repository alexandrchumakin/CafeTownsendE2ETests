package controls;

import driver.WebDriverHelper;
import interfaces.IGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EmployeeList extends BaseControl implements IGrid {
    private static final String template = "//ul[@id='%1$s']";
    private static final String emplTemplate = "//li[contains(text(), '%1$s')]";

    public EmployeeList() {
        super(new String[]{template}, "employee-list");
    }

    private WebElement employeeItem(String employee){
        WebElement element = WebDriverHelper.findElement(By.xpath(String.format(emplTemplate, employee)), 10);
        WebDriverHelper.scrollToElement(element);
        return element;
    }

    public void selectItem(String employee) {
        employeeItem(employee).click();
    }

    public boolean itemExists(String employee) {
        WebElement element = employeeItem(employee);
        return element.isEnabled();
    }

    public boolean noItem(String employee){
        return !WebDriverHelper.findElement(byLocator, 5).getText().contains(String.format(emplTemplate, employee));
    }
}
