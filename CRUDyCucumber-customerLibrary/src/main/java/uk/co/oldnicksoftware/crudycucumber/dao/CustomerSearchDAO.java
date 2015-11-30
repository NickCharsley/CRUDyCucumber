/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;

/**
 *
 * @author nick
 */
public class CustomerSearchDAO {
    EntityManager entityManager ;
    private Query query;
    
    private void createTransactionalEntityManager() {
        entityManager = Persistence.createEntityManagerFactory("CustomerPU").createEntityManager();
        query = entityManager.createNamedQuery("Customer.findAll");
        entityManager.getTransaction().begin();
    }
    private void closeTransactionalEntityManager() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    public List<Customer> search() {
        createTransactionalEntityManager();
        
        List<Customer> customers = new ArrayList<>();
        List<Customer> resultList = query.getResultList();
        for (Customer c : resultList) {
            customers.add(c);
        }
        return customers;
    }    

    public void save(Customer customer) {
        createTransactionalEntityManager();
        entityManager.merge(customer);
        closeTransactionalEntityManager();
    }

    public void create(Customer customer) {
        createTransactionalEntityManager();
        customer.buildDefault();
        entityManager.persist(customer);
        closeTransactionalEntityManager();
    }
}
