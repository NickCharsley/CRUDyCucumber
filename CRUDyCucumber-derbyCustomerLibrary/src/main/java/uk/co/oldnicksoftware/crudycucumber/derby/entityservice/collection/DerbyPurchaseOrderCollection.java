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
import uk.co.oldnicksoftware.crudycucumber.api.PurchaseOrderCollection;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.*;
import uk.co.oldnicksoftware.crudycucumber.derby.entityservice.dao.DerbyPurchaseOrderDAO;
import uk.co.oldnicksoftware.crudycucumber.domain.*;

/**
 *
 * @author nick
 */
@ServiceProvider(service = PurchaseOrderCollection.class)
public class DerbyPurchaseOrderCollection implements PurchaseOrderCollection {
    private final List<PurchaseOrder> purchaseOrders;
    private final Lookup lookup;
    private final InstanceContent instanceContent;
    private final DerbyPurchaseOrderDAO dao;

    public DerbyPurchaseOrderCollection(){
        purchaseOrders = new ArrayList<>();
        // Create an InstanceContent to hold abilities...
        instanceContent = new InstanceContent();
        // Create an AbstractLookup to expose InstanceContent contents...
        lookup = new AbstractLookup(instanceContent);
        dao = new DerbyPurchaseOrderDAO();        
        // Add a "Reloadable" ability to this entity:
        // ... and a "Savable" ability:
        // ...and a "Creatable" ability:
        instanceContent.add(new CreatableEntityCapability<PurchaseOrder>(){
            @Override
            public void create(PurchaseOrder entity) throws Exception {
                dao.create(entity);
            }
            
        });
        // ... and a "Removeable" ability:
    }

    @Override
    public List getPurchaseOrders() {
        return purchaseOrders;
    }

    @Override
    public PurchaseOrder getPurchaseOrder(PurchaseOrder search) {
        return dao.getPurchaseOrder(search);
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