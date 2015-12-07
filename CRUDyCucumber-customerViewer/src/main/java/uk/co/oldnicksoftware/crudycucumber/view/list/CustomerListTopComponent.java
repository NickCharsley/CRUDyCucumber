/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.view.list;

import uk.co.oldnicksoftware.crudycucumber.view.api.RootNode;
import java.beans.PropertyChangeEvent;
import javax.swing.ActionMap;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.NodeEvent;
import org.openide.nodes.NodeListener;
import org.openide.nodes.NodeMemberEvent;
import org.openide.nodes.NodeReorderEvent;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import uk.co.oldnicksoftware.crudycucumber.dao.CustomerQuery;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//uk.co.oldnicksoftware.crudycucumber.view//CustomerList//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "CustomerListTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "uk.co.oldnicksoftware.crudycucumber.view.CustomerListTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_CustomerListAction",
        preferredID = "CustomerListTopComponent"
)
@Messages({
    "CTL_CustomerListAction=CustomerList",
    "CTL_CustomerListTopComponent=CustomerList Window",
    "HINT_CustomerListTopComponent=This is a CustomerList window"
})
public final class CustomerListTopComponent extends TopComponent implements ExplorerManager.Provider {

    ExplorerManager explorerManager = new ExplorerManager();
    
    public CustomerListTopComponent() {
        initComponents();
        setName(Bundle.CTL_CustomerListTopComponent());
        setToolTipText(Bundle.HINT_CustomerListTopComponent());
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_DRAGGING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_SLIDING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);

        CustomerQuery query = new CustomerQuery();
        RootNode node = new RootNode(query);
        explorerManager.setRootContext(node);
        
        ActionMap map = getActionMap();
        map.put("delete", ExplorerUtils.actionDelete(explorerManager, true)); // NOI18N
        
        associateLookup(ExplorerUtils.createLookup(explorerManager, map));
        
        node.addNodeListener(new NodeListener(){ 

            @Override
            public void childrenAdded(NodeMemberEvent ev) {
                //Keeps Root Node it open on 'Reload'                
                beanTreeView1.expandNode(ev.getNode());
            }

            @Override
            public void childrenRemoved(NodeMemberEvent ev) {
            }

            @Override
            public void childrenReordered(NodeReorderEvent ev) {
            }

            @Override
            public void nodeDestroyed(NodeEvent ev) {
            }

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        beanTreeView1 = new org.openide.explorer.view.BeanTreeView();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(beanTreeView1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(beanTreeView1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openide.explorer.view.BeanTreeView beanTreeView1;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return explorerManager;
    }
}
