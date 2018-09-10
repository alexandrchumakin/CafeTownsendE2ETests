package driver;

import common.Configurations;
import common.ExtendedString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {
    public static WebDriver CurrentDriver;

    public static void initDriver() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/lib/geckodriver");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("security.insecure_password.ui.enabled", false);
        firefoxOptions.addPreference("security.insecure_field_warning.contextual.enabled", false);
        CurrentDriver = new FirefoxDriver(firefoxOptions);
        CurrentDriver.manage().window().maximize();
        waitPageSource();
    }

    public static void openPage(String url){
        CurrentDriver.navigate().to(String.format("%1$s/%2$s", Configurations.getValueByKey("host"), url));
    }

    public static void closeDriver(){
        CurrentDriver.close();
    }

    public static void waitPageSource(){
        try {
            for(int i = 0; i < 20; i++) {
                String currentSource = Driver.CurrentDriver.getPageSource();
                Thread.sleep(500);
                String newSource = Driver.CurrentDriver.getPageSource();
                if (currentSource.equals(newSource))
                    break;
                else
                    Thread.sleep(500);
            }
        }
        catch (InterruptedException ex){
            System.out.println(ExtendedString.formatMessage(ex));
        }
    }
}
