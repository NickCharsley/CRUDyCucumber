/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.view.list;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import uk.co.oldnicksoftware.crudycucumber.api.capabilities.ReloadableViewCapability;

/**
 *
 * @author nick
 */

@ActionID(category = "Customer", id = "uk.co.oldnicksoftware.crudycucumber.view.list.ReloadableViewAction")
@ActionRegistration(displayName = "#CTL_ReloadableViewAction")
@Messages("CTL_ReloadableViewAction=Reload")
public final class ReloadAction implements ActionListener {
    private final ReloadableViewCapability context;
    public ReloadAction(ReloadableViewCapability context) {
        this.context = context;
    }
    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            context.reloadChildren();
            System.out.println(ev.getSource().toString());
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}