Feature: Customer Update
   As a User
   In order to Maintain Customers
   I want to beable to Update Customer Names and Cities

Background: Customer List and Editor Displayed
   The Customer List and Editor are both visable
Given I have a "CRUDyCucumber" Application
  And I have the test Database
  And I have the following panels:
    | panel                 |
    | CustomerList Window   |
    | CustomerEditor Window |

Scenario: The Nodes List is Correct
    The Nodes List Contains a correct entry
Given The "CustomerList Window" Panel's Node List contains "Jumbo Eagle Corp II"
  And The "CustomerList Window" Panel's Node List does not contain "Jumbo Eagle Corp III"

Scenario: Nodes are Selectable
    Selecting Nodes updates the Editor Windows
Given the "CustomerList Window" Panel's Node List contains "Jumbo Eagle Corp II"
 When "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List
 Then the "CustomerEditor Window" Panel's text field "Name:" is "Jumbo Eagle Corp II"
  And the "CustomerEditor Window" Panel's text field "City:" is "Fort Lauderdale"   

Scenario: Toolbar Icons disabled
    If we haven't changed anything Save, Undo and Redo are disabled
Given I have a "&File" toolbar Item "Save"
  And I have a "&Undo/Redo" toolbar Item "Redo"
  And I have a "&Undo/Redo" toolbar Item "Undo"
 Then the "&File" toolbar Item "Save" is disabled
  And the "&Undo/Redo" toolbar Item "Redo" is disabled
  And the "&Undo/Redo" toolbar Item "Undo" is disabled
  
Scenario: Save Icon enabled
    If we change anything only Save and Undo are enabled
Given "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List
 Then the "CustomerEditor Window" Panel has the following text fields:
    | name  | value               |
    | Name: | Jumbo Eagle Corp II |
    | City: | Fort Lauderdale     |
 When I select the "CustomerEditor Window" Panel's "Name:" and append "I"
 Then the "CustomerEditor Window" Panel's text field "Name:" is "Jumbo Eagle Corp III"
  And the "&File" toolbar Item "Save" is enabled
  And the "&Undo/Redo" toolbar Item "Undo" is enabled
  And the "&Undo/Redo" toolbar Item "Redo" is disabled

Scenario: Re-do Icon enabled
    If we undo something then Redo is enabled
 When "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List
  And I select the "CustomerEditor Window" Panel's "Name:" 
  And I append "I"
 Then the "&Undo/Redo" toolbar Item "Undo" is enabled
 When I click the "&Undo/Redo" toolbar Item "Undo"
 Then the "CustomerEditor Window" Panel's text field "Name:" is "Jumbo Eagle Corp II"
  And the "&Undo/Redo" toolbar Item "Redo" is enabled

Scenario: Save and Undo Icon disabled when reverted
    If we revert all changes Save and Undo are disabled
 When "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List
  And I select the "CustomerEditor Window" Panel's "Name:" 
  And I append "I"
 Then the "&Undo/Redo" toolbar Item "Undo" is enabled
 When I click the "&Undo/Redo" toolbar Item "Undo"
 Then the "CustomerEditor Window" Panel's text field "Name:" is "Jumbo Eagle Corp II"
  And the toolbars are correctly enabled:
    | toolbar    | item | state    |
    | &File      | Save | disabled |
    | &Undo/Redo | Undo | disabled |
    | &Undo/Redo | Redo | enabled  |

Scenario: Redo Icon disabled
    If we redo all undos then Redo is disabled
    If we undo something then Redo is enabled
 When "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List
  And I select the "CustomerEditor Window" Panel's "Name:" 
  And I append "I"
 Then the "&Undo/Redo" toolbar Item "Undo" is enabled
  And the "CustomerEditor Window" Panel's text field "Name:" is "Jumbo Eagle Corp III"
 When I click the "&Undo/Redo" toolbar Item "Undo"
 Then the "&Undo/Redo" toolbar Item "Redo" is enabled
  And the "CustomerEditor Window" Panel's text field "Name:" is "Jumbo Eagle Corp II"
 When I click the "&Undo/Redo" toolbar Item "Redo"
 Then the "&Undo/Redo" toolbar Item "Redo" is disabled
  And the "CustomerEditor Window" Panel's text field "Name:" is "Jumbo Eagle Corp III"
 
Scenario: Persist Changes
    If we make changes and save them the Save, Undo and Redo are disabled and
    the Customer List contains the new data.
Given "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List
  And I select the "CustomerEditor Window" Panel's "Name:" 
  And I remove the last 1 character
 When I click the "&File" toolbar Item "Save"
 Then the "Question" Dialog is displayed
 When I click the "Question" ok button
 Then the toolbars are correctly enabled:
    | toolbar    | item | state    |
    | &File      | Save | disabled |
    | &Undo/Redo | Undo | enabled  |
    | &Undo/Redo | Redo | disabled |
  And the "CustomerList Window" Panel's Node List contains "Jumbo Eagle Corp I"
 When I append "I"
  And I click the "&File" toolbar Item "Save"
  And I click the "Question" ok button
 Then the "CustomerList Window" Panel's Node List contains "Jumbo Eagle Corp II" 