/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.text.entityservice.collection;
import java.util.ArrayList;
import java.util.List;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.ServiceProvider;
import uk.co.oldnicksoftware.crudycucumber.api.CustomerCollection;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.*;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;

/**
 *
 * @author nick
 */
@ServiceProvider(service = CustomerCollection.class)
public class TextCustomerCollection  implements CustomerCollection {
    private List customers;
    private Lookup lookup;
    private InstanceContent instanceContent;
    
    
    @Override
    public Lookup getLookup() {
        customers = new ArrayList();
        // Create an InstanceContent to hold abilities...
        instanceContent = new InstanceContent();
        // Create an AbstractLookup to expose InstanceContent contents...
        lookup = new AbstractLookup(instanceContent);
        instanceContent.add(new ReloadableEntityCapability() {
            @Override
            public void reload() throws Exception {
                getCustomers().clear();
                Customer cu=new Customer();
                cu.setName("Jumbo Eagle Corp II");
                cu.setCity("Fort Lauderdale");                                
                getCustomers().add(cu);
                
                Customer cu1=new Customer();
                cu1.setName("Yankee Computer Repair Ltd");
                getCustomers().add(cu1);                                
            }
        });
        instanceContent.add(new CreatableEntityCapability<Customer>() {
            @Override
            public void create(Customer customer) throws Exception {
                Customer cu=new Customer();
                cu.setName("Old Nick Software");
                cu.setCity("London");                                
                getCustomers().add(cu);
            }
        });
        instanceContent.add(new RemovableEntityCapability() {
            @Override
            public void remove(Customer customer) throws Exception {
                getCustomers().remove(customer);
            }            

            @Override
            public void removeAll() throws Exception {
                getCustomers().clear();
            }
        });
        instanceContent.add(new SaveableEntityCapability() {
            @Override
            public void save(Customer customer) throws Exception {
                StatusDisplayer.getDefault().setStatusText("Saved...");
            }
        });

    
        return lookup;
    }

    @Override
    public List getCustomers() {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
