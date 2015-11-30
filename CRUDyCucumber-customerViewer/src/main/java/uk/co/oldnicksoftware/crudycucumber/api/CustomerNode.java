/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.api;

import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.datatransfer.NewType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.crudycucumber.dao.CustomerQuery;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;

/**
 *
 * @author nick
 */
public class CustomerNode extends BeanNode {
    private NewCustomerType newCustomer;
    
    public CustomerNode(Customer customer, CustomerQuery query)  throws IntrospectionException {
        this(customer, query, new InstanceContent());
    }
    
    private CustomerNode(final Customer customer, final CustomerQuery query, InstanceContent instanceContent)  throws IntrospectionException {
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
}
