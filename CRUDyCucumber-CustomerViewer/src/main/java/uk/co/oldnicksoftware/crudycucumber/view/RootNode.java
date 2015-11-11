/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.view;

import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.crudycucumber.api.ReloadableViewCapability;
import uk.co.oldnicksoftware.crudycucumber.dao.CustomerQuery;

/**
 *
 * @author nick
 */
public class RootNode extends AbstractNode {
    private CustomerQuery query;
    private InstanceContent instanceContent;
    public RootNode(CustomerQuery query) {
        this(query, new InstanceContent());
    }
    private RootNode(CustomerQuery query, InstanceContent ic) {
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
    }
    @Override
    public String getDisplayName() {
        return "Query: " + query.getSqlstring();
    }
    @Override
    public Action[] getActions(boolean context) {
        // Pass the Lookup, which now contains ReloadableViewCapability, to the Action:
        return new Action[]{new ReloadAction(getLookup())};
    }

    
}
