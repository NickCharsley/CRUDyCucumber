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
import uk.co.oldnicksoftware.crudycucumber.api.CustomerCollection;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.*;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;

/**
 *
 * @author nick
 */
@ServiceProvider(service = CustomerCollection.class)
public class TextCustomerCollection extends TextCollection implements CustomerCollection {
    private Map<Integer,Customer> searchCustomers;

    public TextCustomerCollection(){
        searchCustomers=new HashMap();
        // Add a "Reloadable" ability to this entity:
        instanceContent.add(new ReloadableEntityCapability() {
            @Override
            public void reload() throws Exception {
            }
        });
        // ...and a "Creatable" ability:
        instanceContent.add(new CreatableEntityCapability<Customer>() {
            @Override
            public void create(Customer customer) throws Exception {
                if (!searchCustomers.containsKey(customer.getCustomerId())){
                    getCustomers().add(customer);
                    searchCustomers.put(customer.getCustomerId(),customer);
                }
            }
        });
        // ...and a "Removeable" ability:                
        instanceContent.add(new RemovableEntityCapability<Customer>() {
            @Override
            public void remove(Customer customer) throws Exception {
                if (searchCustomers.containsKey(customer.getCustomerId())){
                    getCustomers().remove(customer);
                    searchCustomers.remove(customer.getCustomerId());
                }
            }            

            @Override
            public void removeAll() throws Exception {
                getCustomers().clear();
                searchCustomers.clear();
            }
        });
        // ... and a "Savable" ability:
        instanceContent.add(new SaveableEntityCapability<Customer>() {
            @Override
            public void save(Customer customer) throws Exception {
                StatusDisplayer.getDefault().setStatusText("Saved...");
            }
        });        
    }
    
    @Override
    public List getCustomers() {
        return collection;
    }

    @Override
    public Customer getCustomer(Customer search) {
        if (searchCustomers.containsKey(search.getCustomerId())){
            return searchCustomers.get(search.getCustomerId());
        }
        return search;
    }
}
