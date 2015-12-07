Feature: Customer CREATE
As a User
In order to Track Customers
I want to be able to Create Delete Customers

Background: Customer List and Editor Displayed
   The Customer List and Editor are both visable
Given I have a "CRUDyCucumber" Application
  And I have the test Database
  And I have the following panels:
    | panel                 |
    | CustomerList Window   |
    | CustomerEditor Window |

Scenario: Toolbar Icon disabled for Root Node
    If we select the Customer List Root Node, Delete is disabled
Given "Customers" is selected in the "CustomerList Window" Panel's Node List 
 Then the "&File" toolbar Item "Delete" is disabled

Scenario: Toolbar Icon enabled for Child Node
    If we select any Customer List Child Node, Delete is enabled
Given "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List 
 Then the "&File" toolbar Item "Delete" is enabled

Scenario: Toolbar Icon disabled for Customer Editor Window
    If we select any field in the Customer Editor Window, Delete is disabled
Given "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List
  And I select the "CustomerEditor Window" Panel's "Name:" 
 Then the "&File" toolbar Item "Delete" is disabled

Scenario: Delete Customer Popup Available
    Only the Customer Nodes of the Customer List have the Delete Menu Item
Given "Customers" is selected in the "CustomerList Window" Panel's Node List 
 Then The "CustomerList Window" Panel's ROOT NODE has no popup menu item "Delete"
  And The "CustomerList Window" Panel's FIRST NODE has a popup menu item "Delete"
  And The "CustomerList Window" Panel's LAST NODE has a popup menu item "Delete"

Scenario: Delete Customer
    Delete First Customer
Given "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List 
  And I click the "&File" toolbar Item "Delete"
  And I click the "Confirm Object Deletion" yes button
 Then The "CustomerList Window" Panel's Node List does not contain "Jumbo Eagle Corp II"
