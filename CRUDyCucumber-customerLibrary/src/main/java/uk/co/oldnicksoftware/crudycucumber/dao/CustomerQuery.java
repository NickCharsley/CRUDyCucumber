/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.dao;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.crudycucumber.api.CreatableEntityCapability;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;
import uk.co.oldnicksoftware.crudycucumber.api.ReloadableEntityCapability;
import uk.co.oldnicksoftware.crudycucumber.api.SaveableEntityCapability;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import uk.co.oldnicksoftware.crudycucumber.api.ReloadableViewCapability;

/**
 *
 * @author nick
 */
public final class CustomerQuery implements Lookup.Provider {

    private final List<Customer> customers;
    private final Lookup lookup;
    private final InstanceContent instanceContent;
    private CustomerSearchDAO dao;
    
    public CustomerQuery() {
        customers = new ArrayList<>();
        // Create an InstanceContent to hold abilities...
        instanceContent = new InstanceContent();
        // Create an AbstractLookup to expose InstanceContent contents...
        lookup = new AbstractLookup(instanceContent);
        dao = new CustomerSearchDAO();        
        // Add a "Reloadable" ability to this entity
        instanceContent.add(new ReloadableEntityCapability() {
            @Override
            public void reload() throws Exception {
                ProgressHandle handle = ProgressHandleFactory.createHandle("Loading...");
                handle.start();
                //getCustomers().clear();
                List<Customer> result = dao.search();
                getCustomers().clear();
                for (Customer customer : result) {
                    if (!getCustomers().contains(customer)) {
                        getCustomers().add(customer);
                    }
                }
                handle.finish();
            }
        });
        // Add a "Savable" ability to this entity
        instanceContent.add(new SaveableEntityCapability() {
            @Override
            public void save(Customer customer) throws Exception {
                dao.save(customer);
            }
        });
        // ...and a "Creatable" ability to this entity:
        instanceContent.add(new CreatableEntityCapability() {
            @Override
            public void create(Customer customer) throws Exception {
                dao.create(customer);
            }
        });

    }
    
    @Override
    public Lookup getLookup() {
        return lookup;
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }
    
    public void reload(Node rootNode){
        try {
            //Refresh the list of customers via the implementation of the reload capability:
            ReloadableEntityCapability r = this.getLookup().lookup(ReloadableEntityCapability.class);
            r.reload();
            ReloadableViewCapability rvc = rootNode.getLookup().lookup(ReloadableViewCapability.class);    
            rvc.reloadChildren();
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
