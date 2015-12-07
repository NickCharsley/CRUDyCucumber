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
import uk.co.oldnicksoftware.crudycucumber.domain.DiscountCode;
import uk.co.oldnicksoftware.crudycucumber.domain.MicroMarket;
import uk.co.oldnicksoftware.crudycucumber.domain.PurchaseOrder;

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
        //This is Fake so we just need the first MicroMarket and Discount Code
        if (customer.getDiscountCode()==null){
            List<DiscountCode> dcList = entityManager
                                            .createNamedQuery("DiscountCode.findAll")
                                            .getResultList();
            if (!dcList.isEmpty()){
                customer.setDiscountCode(dcList.get(0));
            }
        }
        if (customer.getZip()==null){
            List<MicroMarket> mmList = entityManager
                                            .createNamedQuery("MicroMarket.findAll")
                                            .getResultList();
            if (!mmList.isEmpty()){
                customer.setZip(mmList.get(0));
            }
        }
        
        entityManager.persist(customer);
        closeTransactionalEntityManager();
    }
    
    public void remove(Customer customer) {
        createTransactionalEntityManager();
        //N.B. we need to remove any Orders First!!!
        entityManager
                .createNamedQuery("PurchaseOrder.deleteByCustomerId")
                .setParameter("customerId", customer)
                .executeUpdate();
        entityManager
                .createNamedQuery("Customer.deleteByCustomerId")
                .setParameter("customerId", customer.getCustomerId())
                .executeUpdate();
        
        closeTransactionalEntityManager();
    }
}
