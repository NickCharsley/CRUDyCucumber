/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.api;

import org.openide.nodes.Node;
import org.openide.util.Lookup;

/**
 *
 * @author nick
 */
public interface EntityCollection {
    public Lookup getLookup();
    public void reload(Node rootNode);    
}
