/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.oldnicksoftware.crudycucumber.steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.*;
import cucumber.runtime.PendingException;
import java.awt.event.KeyEvent;
import java.util.Map;
import javax.swing.JTextField;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

/**
 *
 * @author nick
 */
public class CrudyFields {
    private void z_nop() throws Throwable {
        // Just exists to keep PendingException as an import
        throw new PendingException();
    }
        
    private JTextFieldOperator lastSelectedField;
    
    @Then("^(?:T|t)he \"([^\"]*)\" Panel has an? ([^ ]*) field \"([^\"]*)\"$")
    public JTextFieldOperator hasField(String panel, String field, String name) throws Throwable {
        TopComponentOperator tco=new TopComponentOperator(panel);  
        JLabelOperator labelOper = new JLabelOperator(tco,name);
        assertThat("Found the "+field+" field "+name,labelOper,is(notNullValue()));
        JTextFieldOperator txtOper = new JTextFieldOperator((JTextField)labelOper.getLabelFor());
        assertThat("Found the "+field+" field "+name,txtOper,is(notNullValue()));
        return txtOper;
    }    

    @Then("^(?:T|t)he \"([^\"]*)\" Panel's ([^ ]*) field \"([^\"]*)\" is \"([^\"]*)\"$")
    public void panelFieldIs(String panel, String field,String name, String value) throws Throwable {        
        JTextFieldOperator txtOper = hasField(panel,field,name);
        assertThat("Found the "+field+" field "+name,txtOper,is(notNullValue()));
        assertThat("Value of "+name+" is "+value,txtOper.getText(),is(value));
    }    
    
    @When("^I select the \"([^\"]*)\" Panel's \"([^\"]*)\"$")
    public void selectField(String panel, String field) throws Throwable {
        lastSelectedField=hasField(panel,"text",field);
        assertThat("Field Selected",lastSelectedField,is(notNullValue()));
        lastSelectedField.clickMouse();
    }

    @When("^I (append) \"([^\"]*)\"$")
    public void typeLetters(String location,String text) throws Throwable {
        assertThat("Field Selected",lastSelectedField,is(notNullValue()));
        lastSelectedField.clickMouse();//Yes I know this re-selects it but lets take the risk...
        if (location.equals("append")) lastSelectedField.pushKey(KeyEvent.VK_END);
        for(char c: text.toCharArray()){
            lastSelectedField.typeKey(c);
        }
    }    

    @Given("^I remove the (last|first) (\\d+) characters?$")
    public void removeCharacters(String location, int delete) throws Throwable {
        assertThat("Field Selected",lastSelectedField,is(notNullValue()));
        lastSelectedField.clickMouse();//Yes I know this re-selects it but lets take the risk...
        if (location.equals("last")){
            lastSelectedField.pushKey(KeyEvent.VK_END);
            for (int i=0;i<delete;i++){
                lastSelectedField.pushKey(KeyEvent.VK_BACK_SPACE);
            }
        } else
        throw new PendingException();
    }    
    
    @Then("^(?:T|t)he \"([^\"]*)\" Panel has the following ([^ ]*) fields:$")
    public void panelHasFields(String panel,String field, DataTable dtFields) throws Throwable {
        for (Map<String, String> map : dtFields.asMaps()) {
            panelFieldIs(panel,field,map.get("name"),map.get("value"));
        }
    }

    @When("^I select the \"([^\"]*)\" Panel's \"([^\"]*)\" and (append) \"([^\"]*)\"$")
    public void selectFieldAndType(String panel, String field, String location, String text) throws Throwable {
        selectField(panel,field);
        typeLetters(location,text);
    }
    
}
