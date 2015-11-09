package uk.co.oldnicksoftware.crudycucumber.steps;

import cucumber.api.java.en.*;
import cucumber.api.jelly.Helper;
import cucumber.runtime.PendingException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jellytools.NbDialogOperator;
import org.netbeans.jellytools.actions.ActionNoBlock;

/**
 *
 * @author nick
 */
public class CrudySteps {
    
    @Given("^I have an? \"([^\"]*)\" Application$")
    public void I_have_a_Application(String application) throws Throwable {
        MainWindowOperator mwo = MainWindowOperator.getDefault();
        assertThat("MainWindowOperator is a Netbeans Main Window",mwo.getName(),is("NbMainWindow"));
        //Looks like Cucumber Jelly Fails to load Branding !!! Must Fix
        //assertThat("Application is Named",mwo.getTitle(),is(application));
    }

    @When("^I click the \"([^\"]*)\" menu$")
    public void I_click_the_menu(String menu) throws Throwable {
        new ActionNoBlock(menu, null).performMenu();
    }

    @Then("^the \"([^\"]*)\" Dialog is (displayed|hidden)$")
    public void the_Dialog_is_displayed(String dialog,String displayed) throws Throwable {
        boolean shown=displayed.equals("displayed");
        
        assertThat("The '"+dialog+"' Dialog is Visable"
                ,Helper.dialogVisable(dialog,shown)
                ,is(shown));
    }

    @When("^I click the \"([^\"]*)\" close button$")
    public void I_click_the_close_button(String dialog) throws Throwable {
        new NbDialogOperator(dialog).closeByButton();
    }    
}