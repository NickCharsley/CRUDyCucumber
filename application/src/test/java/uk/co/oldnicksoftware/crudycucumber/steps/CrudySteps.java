package uk.co.oldnicksoftware.crudycucumber.steps;

import cucumber.api.java.en.*;
import cucumber.api.jelly.Helper;
import cucumber.runtime.PendingException;
import java.awt.event.KeyEvent;
import javax.swing.MenuElement;
import javax.swing.tree.TreePath;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jellytools.NbDialogOperator;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.ActionNoBlock;
import org.netbeans.jellytools.nodes.Node;
import org.netbeans.jemmy.operators.JPopupMenuOperator;
//import org.netbeans.jellytools.nodes.Node;

import org.netbeans.jemmy.operators.JTreeOperator;
import org.openide.explorer.ExplorerManager;
import uk.co.oldnicksoftware.crudycucumber.view.list.RootNode;

/**
 *
 * @author nick
 */
public class CrudySteps {
    
    @Given("^I have an? \"([^\"]*)\" Application$")
    public void haveApplication(String application) throws Throwable {
        MainWindowOperator mwo = MainWindowOperator.getDefault();
        assertThat("MainWindowOperator is a Netbeans Main Window",mwo.getName(),is("NbMainWindow"));
        //Looks like Cucumber Jelly Fails to load Branding !!! Must Fix
        //assertThat("Application is Named",mwo.getTitle(),is(application));
    }

    @When("^I click the \"([^\"]*)\" menu$")
    public void clickMenu(String menu) throws Throwable {
        new ActionNoBlock(menu, null).performMenu();
    }

    @Then("^the \"([^\"]*)\" Dialog is (displayed|hidden)$")
    public void dialogVisability(String dialog,String displayed) throws Throwable {
        boolean shown=displayed.equals("displayed");
        
        assertThat("The '"+dialog+"' Dialog is Visable"
                ,Helper.dialogVisable(dialog,shown)
                ,is(shown));
    }

    @When("^I click the \"([^\"]*)\" close button$")
    public void clickCloseButton(String dialog) throws Throwable {
        new NbDialogOperator(dialog).closeByButton();
    }    
    
    @Then("^I have (?:a|an) \"([^\"]*)\" Panel$")
    public void haveAPanel(String panel) throws Throwable {
        assertThat(new TopComponentOperator(panel),is(notNullValue()));        
    }
    
    private void refresh(ExplorerManager.Provider emp){
        ExplorerManager em=emp.getExplorerManager();
        RootNode an=(RootNode)em.getRootContext();
        for (javax.swing.Action a:an.getActions(true)){
            try {
                //ReloadAction re=(ReloadAction)a;
                //re.actionPerformed(null);
                //an.reloadNow();
            } catch (Exception e) {
                //just ignore
            }
        }
    }
    
    @Then("^The \"([^\"]*)\" Panel contains (an empty|a) tree (of|with an?) \"([^\"]*)\"$")
    public void panelContainsTree(String panel, String empty, String ofAn, String entityType) throws Throwable {
        TopComponentOperator tco=  new TopComponentOperator(panel);        
        JTreeOperator tree = new JTreeOperator(tco);        
        
        //((TopComponent) getSource()).getLookup().lookup(DataObject.class);
        refresh((ExplorerManager.Provider)tco.getSource());
        
        int children=tree.getChildCount(tree.getRoot());
        
        int num = (empty.equals("a"))?1:0;
        if (ofAn.equals("of"))
            assertThat("Tree is "+empty+" tree "+ofAn+" "+entityType,children,is(not(0)));
        else
            assertThat("Tree is "+empty+" tree "+ofAn+" "+entityType,children,is(num));
    }    
    
    @Then("^The \"([^\"]*)\" Panel's (ROOT NODE) has a (popup menu) item \"([^\"]*)\"$")
    public void nodeHasMenuItem(String panel,String node, String menu, String item) throws Throwable {
        TopComponentOperator tco=  new TopComponentOperator(panel);        
        JTreeOperator tree = new JTreeOperator(tco);        
        TreePath root=tree.findPath("");
        boolean found=false;
        
        assertThat("Found the "+node,root,is(notNullValue()));

        JPopupMenuOperator popup = new JPopupMenuOperator(tree.callPopupOnPath(root));
        for (MenuElement me: popup.getSubElements()){
            String val=me.toString();
            if (val.contains(item)) {
                found=true;
                break;
            }
        }        
        popup.pushKey(KeyEvent.VK_ESCAPE);
        assertThat("Found the Menu Item "+item,found,is(true));
    }
    
    
    
    @Then("^The \"([^\"]*)\" Panel's (ROOT NODE) is \"([^\"]*)\"$")
    @SuppressWarnings("null")
    public void nodeIs(String panel, String node,String name) throws Throwable {
        TopComponentOperator tco=  new TopComponentOperator(panel);        
        JTreeOperator tree = new JTreeOperator(tco);        
        TreePath root=tree.findPath("");
        
        Node opNode=new Node(tree,root);
        
        assertThat("Found the "+node,opNode,is(notNullValue()));
        assertThat("Node is "+name,opNode.getText(),is(name));
    }
}