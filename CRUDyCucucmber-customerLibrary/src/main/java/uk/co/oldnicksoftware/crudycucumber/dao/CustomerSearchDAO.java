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
import uk.co.oldnicksoftware.crudycucucmber.model.Customer;

/**
 *
 * @author nick
 */
public class CustomerSearchDAO {
    
    public List<Customer> search(String search) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("CustomerPU").createEntityManager();
        javax.persistence.Query query = entityManager.createQuery(search);
        List<Customer> customers = new ArrayList<>();
        List<Customer> resultList = query.getResultList();
        for (Customer c : resultList) {
            customers.add(c);
        }
        return customers;
    }    
}
