package queries;

import entities.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import utils.JpaManager;

import java.util.List;

public class CompanyQuery {

    private JpaManager jpaManager = new JpaManager();
    private EntityManager manager;
    private Company company;
    private List<Company> listCompany;
    private EntityTransaction transaction;

    public CompanyQuery(){
        manager = jpaManager.getManager();
        transaction = manager.getTransaction();
    }

    public List<Company> getAllCompanies() {
        listCompany = manager.createQuery("FROM Company").getResultList();
        return listCompany;
    }

    public Company getCompanyById(int id) {
        company = manager.find(Company.class, id);
        return company;
    }

    public List<Company> getCompanyByName(String name) {
        listCompany = manager.createQuery("SELECT cm FROM Company cm WHERE cm.name = :name")
                .setParameter("name", name)
                .getResultList();
        return listCompany;
    }

    public int insertCompany(Company company){
        transaction.begin();
        manager.persist(company);
        try{
            transaction.commit();
            return company.getId();
        } catch(Exception e){
            transaction.rollback();
            System.out.println("Database wasn't updated");
            return 0;
        }
    }

    public int updateCompanyName(int id, String name, String phoneNumber, String email, String address){
        company = getCompanyById(id);

        if(company != null){
            company.setName(name);
            company.setPhoneNumber(phoneNumber);
            company.setEmail(email);
            company.setAddress(address);

            transaction.begin();
            manager.merge(company);

            try{
                transaction.commit();
                return company.getId();
            } catch(Exception e) {
                transaction.rollback();
                System.out.println("Database wasn't updated");
                return -1;
            }
        } else {
            System.out.println("Company wasn't found");
            return -1;
        }
    }

    public int deleteCompany(int id){
        company = getCompanyById(id);

        if(company != null){
            transaction.begin();
            manager.remove(company);

            try{
                transaction.commit();
                return company.getId();
            } catch(Exception e) {
                transaction.rollback();
                System.out.println("Database wasn't updated");
                return -1;
            }
        } else {
            System.out.println("Company wasn't found");
            return -1;
        }
    }
}
