import common.ExtendedString;
import controls.*;
import driver.Steps;
import org.junit.*;

public class TestEmployees {
    @ClassRule
    public static final TestResources res = new TestResources();

    @BeforeClass
    public static void setUp(){
        Steps.login();
    }

    @Test
    public void testInvalidDate(){
        ExtendedString str = new ExtendedString();
        Steps.createEmployee(str.generateString());
        Assert.assertEquals("Error trying to create a new employee: {\"start_date\":[\"can't be blank\"]})", new ErrorAlert().getValue());
        new Button("Cancel").click();
    }

    @Test
    public void testCreateEmployee(){
        String employee = Steps.createEmployee();
        Assert.assertTrue(new EmployeeList().itemExists(employee));
    }

    @Test
    public void testEditEmployee(){
        String employee = Steps.createEmployee();
        String newEmployee = Steps.editEmployee(employee);
        Assert.assertTrue(new EmployeeList().itemExists(newEmployee));
    }

    @Test
    public void testDeleteEmployee(){
        String employee = Steps.createEmployee();
        Steps.deleteEmployee(employee);
        Assert.assertTrue(new EmployeeList().noItem(employee));
    }

    @Test
    public void testEmployeeDetails(){
        ExtendedString str = new ExtendedString();
        String firstName = str.generateString();
        String lastName = str.generateString();
        String startDate = str.generateDate();
        String email = String.format("%1$s@test.com", str.generateString());
        Steps.createEmployee(firstName, lastName, startDate, email);
        Assert.assertTrue(Steps.verifyEmployee(firstName, lastName, startDate, email));
        new Button("Back").click();
    }
}
