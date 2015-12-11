/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.text.entityservice.collection;

import java.util.List;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.crudycucumber.api.DiscountCodeCollection;
import uk.co.oldnicksoftware.crudycucumber.domain.DiscountCode;

/**
 *
 * @author nick
 */
@ServiceProvider(service = DiscountCodeCollection.class)
public class TextDiscountCodeCollection extends TextCollection implements DiscountCodeCollection {
    public TextDiscountCodeCollection(){
        // Add a "Reloadable" ability to this entity:
        // ... and a "Savable" ability:
        // ...and a "Creatable" ability:
        // ... and a "Removeable" ability:
    }
    
    @Override
    public List getDiscountCodes() {
        return collection;
    }

    @Override
    public DiscountCode getDiscountCode(DiscountCode search) {
        return search;
    }
}
