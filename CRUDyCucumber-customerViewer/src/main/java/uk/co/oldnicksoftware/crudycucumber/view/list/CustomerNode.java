/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.view.list;

import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;

/**
 *
 * @author nick
 */
public class CustomerNode extends BeanNode {

    public CustomerNode(Customer bean) throws IntrospectionException { 
        super(bean, Children.LEAF, Lookups.singleton(bean)); 
    }    
}
