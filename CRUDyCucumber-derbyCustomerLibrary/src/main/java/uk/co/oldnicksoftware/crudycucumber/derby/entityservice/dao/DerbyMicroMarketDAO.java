/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.derby.entityservice.dao;

import javax.persistence.NoResultException;
import uk.co.oldnicksoftware.crudycucumber.domain.MicroMarket;

/**
 *
 * @author nick
 */
public class DerbyMicroMarketDAO extends DerbyDAO {
    public MicroMarket getMicroMarket(MicroMarket search){
        MicroMarket mm;
        createTransactionalEntityManager("MicroMarket.findAll");
        try {
            mm= (MicroMarket)entityManager
                            .createNamedQuery("MicroMarket.findByZipCode")
                            .setParameter("zipCode",search.getZipCode())
                            .getSingleResult();      
        } catch (NoResultException e) {
            mm= search;
        }     
        closeTransactionalEntityManager();
        return mm;
    }    
    
    public void create(MicroMarket microMarket){
        createTransactionalEntityManager("MicroMarket.findAll");
        entityManager.persist(microMarket);
        closeTransactionalEntityManager();        
    }    
}

