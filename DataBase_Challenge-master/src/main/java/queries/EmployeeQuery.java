package queries;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import utils.JpaManager;

import java.util.Date;
import java.util.List;

public class EmployeeQuery {

    private JpaManager jpaManager = new JpaManager();
    private EntityManager manager;
    private Employee employee;
    private List<Employee> listEmployee;
    private EntityTransaction transaction;

    public EmployeeQuery(){
        manager = jpaManager.getManager();
        transaction = manager.getTransaction();
    }

    public List<Employee> getAllEmployee() {

        listEmployee = manager.createQuery("FROM employee").getResultList();

        return listEmployee;
    }

    public List<Employee> getEmployeesByLastName(String Lastname){

        listEmployee = manager.createQuery("SELECT em FROM employee em WHERE em.lastName = :lastname").setParameter("lastname",Lastname).getResultList();

        return listEmployee;
    }

    public int insertEmployee(Employee employee){
        transaction.begin();

        manager.persist(employee);

        try{
            transaction.commit();
            return employee.getId();
        }catch (Exception e){
            transaction.rollback();
            System.out.println("Employee was not uploaded");
            return -1;
        }
    }

    public Employee getEmployeeByID(int id){
        employee = manager.find(Employee.class,id);
        return employee;
    }

    public int updateEmployeeInfo(int id, String firstname, String lastName, String email, String phoneNumber, double salary, Date birthDate, int idCompany) {
        Employee employee = getEmployeeByID(id);

        if (employee != null) {
            try {
                transaction.begin();
                employee.setFirstName(firstname);
                employee.setLastName(lastName);
                employee.setEmail(email);
                employee.setPhoneNumber(phoneNumber);
                employee.setSalary(salary);
                employee.setBirthDate((java.sql.Date) birthDate);
                employee.setIdCompany(idCompany);

                manager.merge(employee);
                transaction.commit();

                return employee.getId();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println("Database was not updated. Error: " + e.getMessage());
                e.printStackTrace();  // Imprime la pila de excepciones para obtener más detalles
                return -1;
            } finally {
                // Cierra el EntityManager
                if (manager != null && manager.isOpen()) {
                    manager.close();
                }
            }
        } else {
            System.out.println("Employee not found");
            return -1;
        }
    }


    public int deleteEmployee(int id){
        employee = getEmployeeByID(id);

        if(employee != null){
            transaction.begin();
            manager.remove(employee);

            try{
                transaction.commit();
                return employee.getId();
            }catch (Exception e){
                transaction.rollback();
                System.out.println("Database was not updated");
                return -1;
            }
        }else{
            System.out.println("Employee was not found");
            return  -1;
        }
    }
}