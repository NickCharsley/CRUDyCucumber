/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.view.api;

/**
 *
 * @author nick
 */
import java.io.IOException;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.util.datatransfer.NewType;
import uk.co.oldnicksoftware.crudycucumber.api.CreatableEntityCapability;
import uk.co.oldnicksoftware.crudycucumber.api.ReloadableEntityCapability;
import uk.co.oldnicksoftware.crudycucumber.api.ReloadableViewCapability;
import static uk.co.oldnicksoftware.crudycucumber.view.api.Bundle.*;
import uk.co.oldnicksoftware.crudycucumber.dao.CustomerQuery;
import uk.co.oldnicksoftware.crudycucumber.domain.Customer;

@Messages({
    "LBL_NewName_dialog=Customer Name:",
    "LBL_NewCity_dialog=Customer City:",
    "TITLE_NewCustomer_dialog=New Customer"})
public class NewCustomerType extends NewType {
    private final CustomerQuery query;
    private final Node node;
    private final boolean root;
    
    public NewCustomerType(CustomerQuery query, Node node, boolean root) {
        this.query = query;
        this.node = node;
        this.root = root;
    }
    @Override
    public String getName() {
        return TITLE_NewCustomer_dialog();
    }
    @Override
    public void create() throws IOException {
/**/        
        NotifyDescriptor.InputLine msg = new NotifyDescriptor.InputLine(LBL_NewName_dialog(), TITLE_NewCustomer_dialog());
        Object result = DialogDisplayer.getDefault().notify(msg);
        if (!NotifyDescriptor.YES_OPTION.equals(result)) {
            return;
        }
        String fieldName = msg.getInputText();
        
        msg = new NotifyDescriptor.InputLine(LBL_NewCity_dialog(), TITLE_NewCustomer_dialog());        
        result = DialogDisplayer.getDefault().notify(msg);
        String fieldCity = msg.getInputText();
        
        if (NotifyDescriptor.YES_OPTION.equals(result)) {
            try {
                //Create a new Customer object:
                Customer customer = new Customer();
                //This is Fake so we just generate a good random number..
                customer.setCustomerId(fieldName.length()*fieldCity.length());
                customer.setName(fieldName);
                customer.setCity(fieldCity);
                //Pass the customer to the query's implementation of the create capability: 
                CreatableEntityCapability cec = query.getLookup().lookup(CreatableEntityCapability.class);
                cec.create(customer);
                               
                //If the Node passed in is the root node, refresh the root node,
                //else refresh the child node only:
                if (!root) {
                    query.reload(node.getParentNode());
                } else {
                    query.reload(node);
                }
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
/**/
    }
    
}