package driver;

import common.ExtendedString;
import controls.*;

public class Steps {
    public static void login(){
        ExtendedString str = new ExtendedString();
        login(str.getUser(), str.getPassword());
    }

    public static void logout(){
        if (!Driver.CurrentDriver.getCurrentUrl().endsWith("login")) {
            new SubmitButton("Logout").click();
        }
    }

    public static void login(String userName, String password) {
        new TextBox("Username").setValue(userName);
        new TextBox("Password").setValue(password);
        new SubmitButton("Login").click();
    }

    public static String createEmployee(String firstName, String lastName, String startDate, String email){
        new Button("Create").click();
        new TextBox("First name").setValue(firstName);
        new TextBox("Last name").setValue(lastName);
        new TextBox("Start date").setValue(startDate);
        new TextBox("Email").setValue(email);
        new SubmitButton("Add").click();
        return String.format("%1$s %2$s", firstName, lastName);
    }

    public static String createEmployee(String startDate) {
        ExtendedString str = new ExtendedString();
        return createEmployee(str.generateString(), str.generateString(), startDate, String.format("%1$s@test.com", str.generateString()));
    }

    public static String createEmployee(){
        return createEmployee(new ExtendedString().generateDate());
    }

    public static String editEmployee(String employee){
        ExtendedString str = new ExtendedString();
        new EmployeeList().selectItem(employee);
        new Button("Edit").click();
        String newFirstName = str.generateString();
        String newLasttName = str.generateString();
        new TextBox("First name").setValue(newFirstName);
        new TextBox("Last name").setValue(newLasttName);
        new SubmitButton("Update").click();
        return String.format("%1$s %2$s", newFirstName, newLasttName);
    }

    public static void deleteEmployee(String employee){
        new EmployeeList().selectItem(employee);
        new Button("Delete").click();
        new ErrorAlert().getValue();
        Driver.waitPageSource();
    }

    public static boolean verifyEmployee(String firstName, String lastName, String startDate, String email){
        String err = "";
        new EmployeeList().selectItem(String.format("%1$s %2$s", firstName, lastName));
        new Button("Edit").click();
        if (!new TextBox("First name").getValue().equals(firstName)) {err += "first name, ";}
        if (!new TextBox("Last name").getValue().equals(lastName)) {err += "last name, ";}
        if (!new TextBox("Start date").getValue().equals(startDate)) {err += "start date, ";}
        if (!new TextBox("Email").getValue().equals(email)) {err += "email, ";}

        if (!err.equals("")){
            System.out.println(String.format("Expected results are different with actual for %1$s", err.trim()));
            return false;
        }

        return true;
    }
}
