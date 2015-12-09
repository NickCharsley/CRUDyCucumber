/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.derby.entityservice.dao;

import javax.persistence.NoResultException;
import uk.co.oldnicksoftware.crudycucumber.domain.PurchaseOrder;

/**
 *
 * @author nick
 */
public class DerbyPurchaseOrderDAO extends DerbyDAO {
        
    public PurchaseOrder getPurchaseOrder(PurchaseOrder search){
        PurchaseOrder po;
        createTransactionalEntityManager("PurchaseOrder.findAll");
        try {
            po= (PurchaseOrder)entityManager
                            .createNamedQuery("PurchaseOrder.findByOrderNum")
                            .setParameter("orderNum",search.getOrderNum())
                            .getSingleResult();      
        } catch (NoResultException e) {
            po= search;
        }     
        closeTransactionalEntityManager();
        return po;
    }    
    
    public void create(PurchaseOrder purchaseOrder){
        createTransactionalEntityManager("PurchaseOrder.findAll");
        entityManager.persist(purchaseOrder);
        closeTransactionalEntityManager();        
    }
}
