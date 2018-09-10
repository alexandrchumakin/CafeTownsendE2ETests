import common.ExtendedString;
import controls.EmployeeList;
import controls.Message;
import controls.SubmitButton;
import driver.Steps;
import org.junit.*;

public class TestLogin {
    @ClassRule
    public static final TestResources res = new TestResources();

    @After
    public void tearDown(){
        Steps.logout();
    }

    @Test
    public void testCorrectLogin(){
        Steps.login();
        Assert.assertTrue(new EmployeeList().isEnabled());
    }

    @Test
    public void testIncorrectLogin(){
        ExtendedString str = new ExtendedString();
        Steps.login(str.generateString(), str.generateString());
        Assert.assertTrue(new Message("Invalid username or password!").isEnabled());
    }

    @Test
    public void testCorrectLoginAfterFail(){
        ExtendedString str = new ExtendedString();
        Steps.login(str.generateString(), str.generateString());
        Steps.login();
        Assert.assertTrue(new EmployeeList().isEnabled());
    }


    @Test
    public void testLoginAfterLogout(){
        Steps.login();
        new SubmitButton("Logout").click();
        Steps.login();
        Assert.assertTrue(new EmployeeList().isEnabled());
    }
}
