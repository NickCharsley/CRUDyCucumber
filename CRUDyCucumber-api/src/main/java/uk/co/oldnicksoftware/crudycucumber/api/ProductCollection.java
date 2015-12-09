/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.api;

import java.util.List;
import uk.co.oldnicksoftware.crudycucumber.domain.Product;

/**
 *
 * @author nick
 */
public interface ProductCollection extends EntityCollection {
    public List getProducts();
    public Product getProduct(Product search);
}
