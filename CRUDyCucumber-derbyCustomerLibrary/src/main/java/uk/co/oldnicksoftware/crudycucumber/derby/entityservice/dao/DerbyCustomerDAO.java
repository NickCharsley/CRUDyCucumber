/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.derby.entityservice.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import uk.co.oldnicksoftware.crudycucumber.domain.*;

/**
 *
 * @author nick
 */
public class DerbyCustomerDAO extends DerbyDAO {
    
    
    public List<Customer> search() {
        createTransactionalEntityManager("Customer.findAll");
        
        List<Customer> customers = new ArrayList<>();
        List<Customer> resultList = query.getResultList();
        for (Customer c : resultList) {
            customers.add(c);
        }
        return customers;
    }    

    public void save(Customer customer) {
        createTransactionalEntityManager("Customer.findAll");
        entityManager.merge(customer);
        closeTransactionalEntityManager();
    }

    public void create(Customer customer) {
        createTransactionalEntityManager("Customer.findAll");
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
        createTransactionalEntityManager("Customer.findAll");
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

    public void removeAll() {
        createTransactionalEntityManager("Customer.findAll");
        entityManager
                .createNamedQuery("PurchaseOrder.deleteAll")
                .executeUpdate();
        entityManager
                .createNamedQuery("Customer.deleteAll")
                .executeUpdate();        
        closeTransactionalEntityManager();
    }

    public Customer getCustomer(Customer search){
        Customer c;
        createTransactionalEntityManager("Customer.findAll");
        try {
            c= (Customer)entityManager
                            .createNamedQuery("Customer.findByCustomerId")
                            .setParameter("customerId",search.getCustomerId())
                            .getSingleResult();      
        } catch (NoResultException e) {
            c= search;
        }     
        closeTransactionalEntityManager();
        return c;
    }
}
