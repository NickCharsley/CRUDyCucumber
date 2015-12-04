/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.jelly.Helper;
import cucumber.runtime.PendingException;
import javax.swing.JTextField;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.NbDialogOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

/**
 *
 * @author nick
 */
public class CrudyDialogs {
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }

    @Then("^(?:T|t)he \"([^\"]*)\" Dialog is (displayed|hidden)$")
    public void dialogVisability(String dialog,String displayed) throws Throwable {
        boolean shown=displayed.equals("displayed");
        
        assertThat("The '"+dialog+"' Dialog is Visable"
                ,Helper.dialogVisable(dialog,shown)
                ,is(shown));
    }
    
    @When("^I click the \"([^\"]*)\" (ok|close|cancel) button$")
    public void clickTheButton(String dialog,String button) throws Throwable {
        NbDialogOperator doD=new NbDialogOperator(dialog); 
        switch (button) {
            case "close":
                doD.closeByButton();
                break;
            case "ok":               
                doD.ok();
                break;
            case "cancel":
                doD.cancel();                
                break;
        }
    }    
    
}
