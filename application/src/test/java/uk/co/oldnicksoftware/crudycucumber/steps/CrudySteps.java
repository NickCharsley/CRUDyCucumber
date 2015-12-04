package uk.co.oldnicksoftware.crudycucumber.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import cucumber.runtime.PendingException;
import java.util.Map;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.*;
import org.netbeans.jellytools.actions.ActionNoBlock;
import org.netbeans.jemmy.operators.*;
import org.openide.explorer.ExplorerManager;
import uk.co.oldnicksoftware.crudycucumber.view.api.RootNode;

/**
 *
 * @author nick
 */
public class CrudySteps {
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }
   
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
    
    @Then("^(?:T|t)he \"([^\"]*)\" Panel contains (an empty|a) tree (of|with an?) \"([^\"]*)\"$")
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
         
    @Given("^I have the following panels:$")
    public void havePanels(DataTable dtPanels) throws Throwable {
        for (Map<String, String> map : dtPanels.asMaps()) {
            haveAPanel(map.get("panel"));
        }
    }   
}