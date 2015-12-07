/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import cucumber.runtime.PendingException;
import java.util.Map;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.operators.JButtonOperator;

/**
 *
 * @author nick
 */
public class CrudyToolbars {
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }

    @Then("^I have a \"([^\"]*)\" toolbar Item \"([^\"]*)\"$")
    public JButtonOperator toolbarItem(String toolbar,String item) throws Throwable {
        MainWindowOperator mwo = MainWindowOperator.getDefault();
        ContainerOperator tbo=mwo.getToolbar(toolbar);        
        assertThat("Found Toolbar "+toolbar,tbo,is(notNullValue()));
        /*Enable to print all ToolBarNames...* /
        for (int i=0;i<mwo.getToolbarCount();i++){
            System.out.println(mwo.getToolbarName(i));
        }
        /**/
        JButtonOperator ibo=mwo.getToolbarButton(tbo, item);
        assertThat("Found "+item+" on toolbar "+toolbar,ibo,is(notNullValue()));
        return ibo;
    }
    
    @Then("^(?:T|t)he \"([^\"]*)\" toolbar Item \"([^\"]*)\" is (enabled|disabled)$")
    public void toolbarItemState(String toolbar,String item,String state) throws Throwable {
        JButtonOperator ibo=toolbarItem(toolbar,item);
        assertThat("Found "+item+" on toolbar "+toolbar,ibo,is(notNullValue()));
        assertThat(item+" is "+state,ibo.isEnabled(),is(state.equalsIgnoreCase("enabled")));
     }
   
    @When("^I click the \"([^\"]*)\" toolbar Item \"([^\"]*)\"$")
    public void clickToolbarItem(String toolbar,String item) throws Throwable {
        JButtonOperator ibo=toolbarItem(toolbar,item);
        assertThat("Found "+item+" on toolbar "+toolbar,ibo,is(notNullValue()));
        ibo.push();
    }
    
    @Then("^the toolbars are correctly enabled:$")
    public void toolbarsCorrectlyEnabled(DataTable dtToolbar) throws Throwable {
        for (Map<String, String> map : dtToolbar.asMaps()) {
            toolbarItemState(map.get("toolbar"),map.get("item"),map.get("state"));
        }
    }
}
