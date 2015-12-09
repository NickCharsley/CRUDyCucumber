/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.derby.entityservice.collection;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.CreatableEntityCapability;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.ReloadableEntityCapability;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.SaveableEntityCapability;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.crudycucumber.api.CustomerCollection;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.ReloadableViewCapability;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.RemovableEntityCapability;
import uk.co.oldnicksoftware.crudycucumber.derby.entityservice.dao.DerbyCustomerDAO;

/**
 *
 * @author nick
 */
@ServiceProvider(service = CustomerCollection.class)
public final class DerbyCustomerCollection implements CustomerCollection {

    private final List<Customer> customers;
    private final Lookup lookup;
    private final InstanceContent instanceContent;
    private DerbyCustomerDAO dao;
    
    public DerbyCustomerCollection() {
        customers = new ArrayList<>();
        // Create an InstanceContent to hold abilities...
        instanceContent = new InstanceContent();
        // Create an AbstractLookup to expose InstanceContent contents...
        lookup = new AbstractLookup(instanceContent);
        dao = new DerbyCustomerDAO();        
        // Add a "Reloadable" ability to this entity:
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
        // ... and a "Savable" ability:
        instanceContent.add(new SaveableEntityCapability() {
            @Override
            public void save(Customer customer) throws Exception {
                dao.save(customer);
            }
        });
        // ...and a "Creatable" ability:
        instanceContent.add(new CreatableEntityCapability<Customer>() {
            @Override
            public void create(Customer customer) throws Exception {
                dao.create(customer);
            }
        });
        // ... and a "Removeable" ability:
        instanceContent.add(new RemovableEntityCapability(){
            @Override
            public void remove(Customer customer) throws Exception {
                dao.remove(customer);
                getCustomers().remove(customer);
            }            

            @Override
            public void removeAll() throws Exception {
                dao.removeAll();
                getCustomers().clear();
            }
        });

    }
    
    @Override
    public Lookup getLookup() {
        return lookup;
    }
    
    @Override
    public List<Customer> getCustomers() {
        return customers;
    }
    
    @Override
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

    @Override
    public Customer getCustomer(Customer search) {
        return dao.getCustomer(search);
    }
}
