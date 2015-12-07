/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.view.api;

import java.beans.IntrospectionException;
import java.io.IOException;
import javax.swing.Action;
import org.openide.actions.DeleteAction;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.crudycucumber.api.CustomerCollection;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.ReloadableViewCapability;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.RemovableEntityCapability;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;

/**
 *
 * @author nick
 */
public class CustomerNode extends BeanNode {
    private NewCustomerType newCustomer;
    
    public CustomerNode(Customer customer, CustomerCollection query)  throws IntrospectionException {
        this(customer, query, new InstanceContent());
    }
    
    private CustomerNode(final Customer customer, final CustomerCollection query, InstanceContent instanceContent)  throws IntrospectionException {
        super(customer, Children.LEAF, new AbstractLookup(instanceContent)); 
        instanceContent.add(customer);
        instanceContent.add(query);
        instanceContent.add(new ReloadableViewCapability() {
            @Override
            public void reloadChildren() throws Exception {
                String newName = customer.getName();
                setDisplayName(newName);
            }
        });
        newCustomer=new NewCustomerType(query,this,false);
        instanceContent.add(newCustomer);
    }

    @Override
    public NewType[] getNewTypes() {
        return new NewType[]{newCustomer};
    }
    @Override
    public Action[] getActions(boolean context) {
        return new Action[]{(SystemAction.get(DeleteAction.class))};
    }
    @Override
    public boolean canDestroy() {
        return true;
    }
    @Override
    public void destroy() throws IOException {
        Customer customer = getLookup().lookup(Customer.class);
        CustomerCollection query = getLookup().lookup(CustomerCollection.class);
        RemovableEntityCapability cec = query.getLookup().lookup(RemovableEntityCapability.class);
        try {
            cec.remove(customer);
        } catch (Exception e) {
        }
        //Notify the NodeListener in the RootNodeChildFactory,
        //where nodeDestroyed will call refresh on the ChildFactory:
        fireNodeDestroyed();
    }
}
