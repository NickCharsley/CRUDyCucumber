/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.text.entityservice.collection;

import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.crudycucumber.api.EntityCollection;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.ReloadableEntityCapability;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.ReloadableViewCapability;

/**
 *
 * @author nick
 */
public class TextCollection implements EntityCollection {
    protected final List collection;
    protected final Lookup lookup;
    protected final InstanceContent instanceContent;

    public TextCollection(){
        collection = new ArrayList<>();
        // Create an InstanceContent to hold abilities...
        instanceContent = new InstanceContent();
        // Create an AbstractLookup to expose InstanceContent contents...
        lookup = new AbstractLookup(instanceContent);
    }    

    @Override
    public Lookup getLookup() {
        return lookup;
    }

    @Override
    public void reload(Node rootNode){
        try {
            //Refresh the list of collection via the implementation of the reload capability:
            ReloadableEntityCapability r = this.getLookup().lookup(ReloadableEntityCapability.class);
            if (r!=null) {
                r.reload();
            }
            ReloadableViewCapability rvc = rootNode.getLookup().lookup(ReloadableViewCapability.class);    
            if (rvc!=null) {
                rvc.reloadChildren();
            }
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }    
}
