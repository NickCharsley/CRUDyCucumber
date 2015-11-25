/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.view.list;

import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.util.lookup.Lookups;
import uk.co.oldnicksoftware.crudycucumber.api.ReloadableViewCapability;
import uk.co.oldnicksoftware.crudycucumber.dao.CustomerQuery;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;

/**
 *
 * @author nick
 */
public class CustomerNode extends BeanNode {
    public CustomerNode(Customer customer, CustomerQuery query)  throws IntrospectionException {
        this(customer, query, new InstanceContent());
    }
    private CustomerNode(final Customer customer, final CustomerQuery query, InstanceContent ic)  throws IntrospectionException {
        super(customer, Children.LEAF, new AbstractLookup(ic)); 
        final String oldName = customer.getName();
        ic.add(customer);
        ic.add(query);
        ic.add(new ReloadableViewCapability() {
            @Override
            public void reloadChildren() throws Exception {
                String newName = customer.getName();
                //fireDisplayNameChange(oldName, newName);
                setDisplayName(newName);
            }
        });
    }
}
