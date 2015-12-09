/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.derby.entityservice.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author nick
 */
public class DerbyDAO {
    protected EntityManager entityManager ;
    protected Query query;

    protected void createTransactionalEntityManager(String sql) {
        entityManager = Persistence.createEntityManagerFactory("CustomerPU").createEntityManager();
        query = entityManager.createNamedQuery(sql);
        entityManager.getTransaction().begin();
    }
    
    protected void closeTransactionalEntityManager() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
}
