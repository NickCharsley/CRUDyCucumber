/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.view.api;

import java.util.List;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Utilities;
import org.openide.util.datatransfer.NewType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.crudycucumber.api.CustomerCollection;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.ReloadableViewCapability;

/**
 *
 * @author nick
 */
public class RootNode extends AbstractNode {
    private CustomerCollection query;
    private InstanceContent instanceContent;
    private NewCustomerType newCustomer;   
    
    public RootNode(CustomerCollection query) {
        this(query, new InstanceContent());
    }
    private RootNode(CustomerCollection query, InstanceContent ic) {
        super(Children.create(new RootNodeChildFactory(query), true), new AbstractLookup(ic));
        this.query = query;
        this.instanceContent = ic;
        // Add a new ability for this node to be reloaded
        this.instanceContent.add(new ReloadableViewCapability() {
            @Override
            public void reloadChildren() throws Exception {
                // To reload this node just set a new set of children
                // using a RootNodeChildFactory object, that retrieves
                // children asynchronously                
                setChildren(Children.create(new RootNodeChildFactory(RootNode.this.query), false));                         
            }
        });
        newCustomer=new NewCustomerType(query,this,true);
        instanceContent.add(newCustomer);
    }
 
    @Override
    public String getDisplayName() {
        return "Customers";
    }
    
    @Override
    public Action[] getActions(boolean context) {
        List customerActions = Utilities.actionsForPath("Actions/Customer");
        return (Action[]) customerActions.toArray(new Action[customerActions.size()]);    
    }
    
    @Override
    public NewType[] getNewTypes() {
        return new NewType[]{newCustomer};
    }
    
}
