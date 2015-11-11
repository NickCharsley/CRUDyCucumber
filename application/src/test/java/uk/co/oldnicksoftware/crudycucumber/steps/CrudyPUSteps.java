/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.steps;

import cucumber.api.java.en.*;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author nick
 */
public class CrudyPUSteps {
    private final EntityManager entityManager;

    public CrudyPUSteps(){
        entityManager = Persistence
                .createEntityManagerFactory("CustomerPU")
                .createEntityManager();
    }    
    
    @Given("^I have (an empty|the test) Database$")
    public void haveAnEmptyDatabase(String databaseType) throws Throwable {
        //Need to perform in correct order...
        entityManager.getTransaction().begin(); 
        if (databaseType.equals("an empty")){
            entityManager.createNamedQuery("Customer.deleteAll").executeUpdate();
        }
        entityManager.getTransaction().commit();
    }        
}
