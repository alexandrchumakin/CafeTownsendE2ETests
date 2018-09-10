import driver.Driver;
import org.junit.rules.ExternalResource;

public class TestResources extends ExternalResource {
    protected void before() {
        Driver.initDriver();
        Driver.openPage("login");
    }
    protected void after() {
        Driver.closeDriver();
    }
}