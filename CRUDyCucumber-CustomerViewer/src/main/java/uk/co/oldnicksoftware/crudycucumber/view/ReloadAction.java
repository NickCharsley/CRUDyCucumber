/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import uk.co.oldnicksoftware.crudycucumber.api.ReloadableViewCapability;

/**
 *
 * @author nick
 */
public final class ReloadAction extends AbstractAction {
    private ReloadableViewCapability reloadableViewCapability;
    public ReloadAction(Lookup lookup) {
        reloadableViewCapability = lookup.lookup(ReloadableViewCapability.class);
        putValue(AbstractAction.NAME, "Reload");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (reloadableViewCapability != null) {
            try {
                reloadableViewCapability.reloadChildren();
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
}
