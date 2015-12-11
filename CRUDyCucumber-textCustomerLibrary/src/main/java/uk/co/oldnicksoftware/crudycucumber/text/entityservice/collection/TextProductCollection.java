/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.text.entityservice.collection;

import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.crudycucumber.api.ProductCollection;
import uk.co.oldnicksoftware.crudycucumber.domain.Product;

/**
 *
 * @author nick
 */
@ServiceProvider(service = ProductCollection.class)
public class TextProductCollection extends TextCollection implements ProductCollection {
    public TextProductCollection(){
        // Add a "Reloadable" ability to this entity:
        // ... and a "Savable" ability:
        // ...and a "Creatable" ability:
        // ... and a "Removeable" ability:                
    }
    
    @Override
    public List getProducts() {
        return collection;
    }

    @Override
    public Product getProduct(Product search) {
        return search;
    }    
}
