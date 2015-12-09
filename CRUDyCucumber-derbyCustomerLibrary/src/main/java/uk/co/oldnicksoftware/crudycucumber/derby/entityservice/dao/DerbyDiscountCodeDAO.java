/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.derby.entityservice.dao;

import javax.persistence.NoResultException;
import uk.co.oldnicksoftware.crudycucumber.domain.DiscountCode;

/**
 *
 * @author nick
 */
public class DerbyDiscountCodeDAO  extends DerbyDAO {
    public DiscountCode getDiscountCode(DiscountCode search){
        DiscountCode po;
        createTransactionalEntityManager("DiscountCode.findAll");
        try {
            po= (DiscountCode)entityManager
                            .createNamedQuery("DiscountCode.findByDiscountCode")
                            .setParameter("discountCode",search.getDiscountCode())
                            .getSingleResult();      
        } catch (NoResultException e) {
            po= search;
        }     
        closeTransactionalEntityManager();
        return po;
    }    
    
    public void create(DiscountCode discountCode){
        createTransactionalEntityManager("DiscountCode.findAll");
        entityManager.persist(discountCode);
        closeTransactionalEntityManager();        
    }
}
    
