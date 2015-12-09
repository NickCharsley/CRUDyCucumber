/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.derby.entityservice.collection;

import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.crudycucumber.api.DiscountCodeCollection;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.CreatableEntityCapability;
import uk.co.oldnicksoftware.crudycucumber.derby.entityservice.dao.DerbyDiscountCodeDAO;
import uk.co.oldnicksoftware.crudycucumber.derby.entityservice.dao.DerbyProductDAO;
import uk.co.oldnicksoftware.crudycucumber.domain.*;

/**
 *
 * @author nick
 */
@ServiceProvider(service = DiscountCodeCollection.class)
public class DerbyDiscountCodeCollection implements DiscountCodeCollection {
    private final List<DiscountCode> discountCodes;
    private final Lookup lookup;
    private final InstanceContent instanceContent;
    private final DerbyDiscountCodeDAO dao;

    public DerbyDiscountCodeCollection(){
        discountCodes = new ArrayList<>();
        // Create an InstanceContent to hold abilities...
        instanceContent = new InstanceContent();
        // Create an AbstractLookup to expose InstanceContent contents...
        lookup = new AbstractLookup(instanceContent);
        dao = new DerbyDiscountCodeDAO();        
        // Add a "Reloadable" ability to this entity:
        // ... and a "Savable" ability:
        // ...and a "Creatable" ability:
        instanceContent.add(new CreatableEntityCapability<DiscountCode>(){
            @Override
            public void create(DiscountCode entity) throws Exception {
                dao.create(entity);
            }            
        });
        // ... and a "Removeable" ability:        
    }
        
    @Override
    public List getDiscountCodes() {
        return discountCodes;
    }

    @Override
    public DiscountCode getDiscountCode(DiscountCode search) {
        return dao.getDiscountCode(search);
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }

    @Override
    public void reload(Node rootNode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
