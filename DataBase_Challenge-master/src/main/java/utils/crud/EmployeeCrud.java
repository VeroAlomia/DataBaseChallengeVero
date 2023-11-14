package utils.crud;

import entities.Company;
import entities.Employee;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import queries.EmployeeQuery;

import java.sql.Date;
import java.util.List;

public class EmployeeCrud {

    EmployeeQuery employeeQuery = new EmployeeQuery();

    List<Employee> employeeList;

    Employee employee;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeCrud.class);
    public List<Employee> getAllEmployees(){
        logger.info("Getting all employees from database Personas");
        employeeList = employeeQuery.getAllEmployee();

        if(!employeeList.isEmpty()){
            for (Employee emp: employeeList){
                logEmployeeInfo(emp);
            }
        }else{
            System.out.println("No employees found");
        }
        return employeeList;
    }

    public List<Employee> getEmployeesByLastname(String lastname){
        logger.info("Getting all employees from database Personas with the lastname " + lastname);
        employeeList = employeeQuery.getEmployeesByLastName(lastname);

        if(!employeeList.isEmpty()){
            for(Employee emp: employeeList){
                logEmployeeInfo(emp);
            }
        }else{
            System.out.println("The employee with the last name"+lastname+"was not found");
        }
        return employeeList;
    }

    public void insertNewEmployee(int id, String firstName, String lastName, String email, String phoneNumber, double salary, Date birthdate, int idCompany){
        logger.info("Creating new employee in the database persona with id " +id);

        employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setSalary(salary);
        employee.setBirthDate(birthdate);
        employee.setIdCompany(idCompany);

        logger.info("new employee with "+id+" created");

        int employeeId= employeeQuery.insertEmployee(employee);

        if(employeeId != -1){
            logEmployeeInfo(employeeQuery.getEmployeeByID(id));
        }else {
            System.out.println("No employee created");
        }
    }

    public int updateEmployee(int id, String firstname, String lastName, String email, String phoneNumber, double salary, Date birthDate, int idCompany ){
        logger.info("Updating employee with lastname Lopez and id "+id);

        int employeeID = employeeQuery.updateEmployeeInfo(id, firstname, lastName, email, phoneNumber, salary, birthDate, idCompany);

        if(employeeID != -1){

            logger.info("employee with lastname "+lastName+" and id"+id+" updated");
        }else{
            System.out.println("no employee was updated");
        }
        return employeeID;
    }


    public void deleteEmployee(int id){
        logger.info("Deleting employee with id "+id);
        int employeeID = employeeQuery.deleteEmployee(id);

        if(employeeID != -1){
            logger.info("the employee with id "+id+" was deleted");
        }else{
            System.out.println("No employee was deleted");
        }
    }

    public Employee getEmployeeByID(int id){
        employee = employeeQuery.getEmployeeByID(id);

        logEmployeeInfo(employee);

        return employee;
    }
    public boolean getEmployeeByIdBool(int id){

        return employeeQuery.getEmployeeByID(id) != null;
    }


    private void logEmployeeInfo(Employee employee) {
        logger.info("Employee ID: {}", employee.getId());
        logger.info("Name: {}", employee.getFirstName());
        logger.info("Last Name: {}", employee.getLastName());
        logger.info("Email: {}", employee.getEmail());
        logger.info("Phone number: {}", employee.getPhoneNumber());
        logger.info("Salary: {}", employee.getSalary());
        logger.info("Birthdate: {}", employee.getBirthDate());
        logger.info("Company ID: {}", employee.getIdCompany());
        logger.info(" ");
    }
}