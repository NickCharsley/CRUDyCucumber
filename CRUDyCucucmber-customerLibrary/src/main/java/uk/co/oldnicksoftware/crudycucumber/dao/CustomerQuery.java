/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.dao;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import uk.co.oldnicksoftware.crudycucucmber.model.Customer;
import uk.co.oldnicksoftware.crudycucumber.api.ReloadableQueryCapability;

/**
 *
 * @author nick
 */
public final class CustomerQuery implements Lookup.Provider {

    private List<Customer> customers;
    private Lookup lookup;
    private InstanceContent instanceContent;
    private String sqlstring;
    public CustomerQuery() {
        customers = new ArrayList<Customer>();
        // Create an InstanceContent to hold abilities...
        instanceContent = new InstanceContent();
        // Create an AbstractLookup to expose InstanceContent contents...
        lookup = new AbstractLookup(instanceContent);
        // Add a "Reloadable" ability to this entity
        instanceContent.add(new ReloadableQueryCapability() {
            @Override
            public void reload() throws Exception {
                ProgressHandle handle = ProgressHandleFactory.createHandle("Loading...");
                handle.start();
                CustomerSearchDAO dao = new CustomerSearchDAO();
                List<Customer> result = dao.search(sqlstring);
                for (Customer customer : result) {
                    if (!getCustomers().contains(customer)) {
                        getCustomers().add(customer);
                    }
                }
                handle.finish();
            }
        });
    }
    public String getSqlstring() {
        return sqlstring;
    }
    public void setSqlstring(String sqlstring) {
        this.sqlstring = sqlstring;
    }
    @Override
    public String toString() {
        return sqlstring;
    }
    @Override
    public Lookup getLookup() {
        return lookup;
    }
    public List<Customer> getCustomers() {
        return customers;
    }
}
