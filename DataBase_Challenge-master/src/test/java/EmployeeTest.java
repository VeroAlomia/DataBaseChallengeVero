import entities.Employee;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import queries.EmployeeQuery;

import java.sql.Date;
import java.util.List;

public class EmployeeTest {

    @Test
    @Description("Getting all employees from the database")
    public void getAllEmployees() {
        List<Employee> employees = new EmployeeQuery().getAllEmployee();

        if (!employees.isEmpty()) {
            System.out.println("Employees retrieved from the database:");
            for (Employee employee : employees) {
                printEmployeeInfo(employee);
                System.out.println(" ");
            }
        } else {
            System.out.println("No employees found in the database.");
        }
    }

    @Test
    @Description("Getting employees by last name")
    public void getEmployeesByLastname() {
        String lastname = "Lopez";
        List<Employee> employees = new EmployeeQuery().getEmployeesByLastName(lastname);
    }

    @Test
    @Description("Inserting a new employee into the database")
    public void insertNewEmployee() {
        int id = 184; //Change ID for run
        String name = "Ana";
        String lastName = "Leandro";
        String email = "anal@gmail.com";
        String phoneNumber = "78909426";
        double salary = 4200;
        Date birthDate = java.sql.Date.valueOf("1982-09-16");
        int companyID = 2;
        EmployeeQuery employeeQuery = new EmployeeQuery();
        int employeeId = employeeQuery.insertEmployee(new Employee(id, name, lastName, email, phoneNumber, null, salary, birthDate, companyID));
        Assert.assertTrue(employeeId > 0);
    }

    @Test
    @Description("Updating an existing employee's information in the database")
    public void updateEmployee() {
        int id = 104;
        String name = "Julian";
        String lastName = "UpdatedLastName";
        String email = "updatedemail@gmail.com";
        String phoneNumber = "3005745684";
        double salary = 9000;
        Date birthDate = java.sql.Date.valueOf("2001-03-15");
        int companyID = 1;

        EmployeeQuery employeeQuery = new EmployeeQuery();
        int updatedEmployeeID = employeeQuery.updateEmployeeInfo(id, name, lastName, email, phoneNumber, salary, birthDate, companyID);
        Assert.assertFalse(updatedEmployeeID > 0);
    }


    @Test
    @Description("Deleting an employee from the database")
    public void deleteEmployee() {
        int id = 64; //Use the ID of an existing user in DB

        EmployeeQuery employeeQuery = new EmployeeQuery();
        int deletedEmployeeID = employeeQuery.deleteEmployee(id);
        Assert.assertFalse(deletedEmployeeID > 0);

        Employee deletedEmployee = employeeQuery.getEmployeeByID(id);
        Assert.assertNull(deletedEmployee);
    }

    public void printEmployeeInfo(Employee employee) {
        System.out.println("Employee ID: " + employee.getId());
        System.out.println("First Name: " + employee.getFirstName());
        System.out.println("Last Name: " + employee.getLastName());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Phone number: " + employee.getPhoneNumber());
        System.out.println("Salary: " + employee.getSalary());
        System.out.println("Birthdate: " + employee.getBirthDate());
        System.out.println("Company ID: " + employee.getIdCompany());
    }
}
