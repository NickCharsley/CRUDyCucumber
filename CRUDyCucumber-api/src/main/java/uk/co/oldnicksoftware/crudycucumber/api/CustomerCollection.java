/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.api;

import java.util.List;
import org.openide.util.Lookup;
import org.openide.nodes.Node;

/**
 *
 * @author nick
 */
public interface CustomerCollection {
    public Lookup getLookup();
    public List getCustomers();
    public void reload(Node rootNode);
}
