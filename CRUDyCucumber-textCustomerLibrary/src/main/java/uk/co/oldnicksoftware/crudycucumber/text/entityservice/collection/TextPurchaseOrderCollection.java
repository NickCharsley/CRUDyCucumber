/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.text.entityservice.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.awt.StatusDisplayer;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.crudycucumber.api.PurchaseOrderCollection;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.*;
import uk.co.oldnicksoftware.crudycucumber.domain.PurchaseOrder;

/**
 *
 * @author nick
 */
@ServiceProvider(service = PurchaseOrderCollection.class)
public class TextPurchaseOrderCollection extends TextCollection implements PurchaseOrderCollection {
    private Map<Integer,PurchaseOrder> searchPurchaseOrders;

    public TextPurchaseOrderCollection(){
        searchPurchaseOrders=new HashMap();

        // Add a "Reloadable" ability to this entity:
        instanceContent.add(new ReloadableEntityCapability() {
            @Override
            public void reload() throws Exception {
            }
        });
        // ...and a "Creatable" ability:
        instanceContent.add(new CreatableEntityCapability<PurchaseOrder>() {
            @Override
            public void create(PurchaseOrder purchaseOrder) throws Exception {
                if (!searchPurchaseOrders.containsKey(purchaseOrder.getOrderNum())){
                    getPurchaseOrders().add(purchaseOrder);
                    searchPurchaseOrders.put(purchaseOrder.getOrderNum(),purchaseOrder);
                }
            }
        });
        // ...and a "Removeable" ability:                
        instanceContent.add(new RemovableEntityCapability<PurchaseOrder>() {
            @Override
            public void remove(PurchaseOrder purchaseOrder) throws Exception {
                if (searchPurchaseOrders.containsKey(purchaseOrder.getOrderNum())){
                    getPurchaseOrders().remove(purchaseOrder);
                    searchPurchaseOrders.remove(purchaseOrder.getOrderNum());
                }
            }            

            @Override
            public void removeAll() throws Exception {
                getPurchaseOrders().clear();
                searchPurchaseOrders.clear();
            }
        });
        // ... and a "Savable" ability:
        instanceContent.add(new SaveableEntityCapability<PurchaseOrder>() {
            @Override
            public void save(PurchaseOrder purchaseOrder) throws Exception {
                StatusDisplayer.getDefault().setStatusText("Saved...");
            }
        });        
    }    
    
    @Override
    public List getPurchaseOrders() {
        return collection;
    }

    @Override
    public PurchaseOrder getPurchaseOrder(PurchaseOrder search) {
        return search;
    }
}
