/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.derby.entityservice.dao;

import javax.persistence.NoResultException;
import uk.co.oldnicksoftware.crudycucumber.domain.Product;

/**
 *
 * @author nick
 */
public class DerbyProductDAO extends DerbyDAO {
    public Product getProduct(Product search){
        Product po;
        createTransactionalEntityManager("Product.findAll");
        try {
            po= (Product)entityManager
                            .createNamedQuery("Product.findByProductId")
                            .setParameter("productId",search.getProductId())
                            .getSingleResult();      
        } catch (NoResultException e) {
            po= search;
        }     
        closeTransactionalEntityManager();
        return po;
    }    
    
    public void create(Product product){
        createTransactionalEntityManager("Product.findAll");
        entityManager.persist(product);
        closeTransactionalEntityManager();        
    }
    
}
