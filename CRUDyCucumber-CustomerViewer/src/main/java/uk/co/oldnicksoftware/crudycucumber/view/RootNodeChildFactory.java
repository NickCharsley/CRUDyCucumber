/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.view;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import uk.co.oldnicksoftware.crudycucucmber.model.Customer;
import uk.co.oldnicksoftware.crudycucumber.api.ReloadableQueryCapability;
import uk.co.oldnicksoftware.crudycucumber.dao.CustomerQuery;

/**
 *
 * @author nick
 */
public class RootNodeChildFactory extends ChildFactory<Customer> {
    private CustomerQuery query;
    public RootNodeChildFactory(CustomerQuery query) {
        this.query = query;
    }
    @Override
    protected boolean createKeys(List<Customer> list) {
        // get this ability from the lookup ...
        ReloadableQueryCapability r = query.getLookup().lookup(ReloadableQueryCapability.class);
        // ... and  use the ability
        if (r != null) {
            try {
                r.reload();
            } catch (Exception e) {
                // Empty
            }
        }
        // Now populate the list of child entities...
        list.addAll(query.getCustomers());
        // And return true since we're all set
        return true;
    }
    @Override
    protected Node createNodeForKey(Customer key) {
        Node customernNode = new AbstractNode(Children.LEAF);
        customernNode.setDisplayName(key.getName());
        return customernNode;
    }

    
}
