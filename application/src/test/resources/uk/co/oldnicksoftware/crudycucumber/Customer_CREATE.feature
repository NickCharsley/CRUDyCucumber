Feature: Customer CREATE
As a User
In order to Track Customers
I want to be able to Create New Customers

Background: Customer List and Editor Displayed
   The Customer List and Editor are both visable
Given I have a "CRUDyCucumber" Application
  And I have the test Database
  And I have the following panels:
    | panel                 |
    | CustomerList Window   |
    | CustomerEditor Window |

Scenario: Toolbar Icon disabled
    If we haven't clicked anything New is disabled
Given I have a "&File" toolbar Item "New"
 Then the "&File" toolbar Item "New" is disabled

Scenario: Toolbar Icon enabled for Root Node
    If we select the Customer List Root Node, New is enabled
Given "Customers" is selected in the "CustomerList Window" Panel's Node List 
 Then the "&File" toolbar Item "New" is enabled

Scenario: Toolbar Icon enabled for Child Node
    If we select any Customer List Child Node, New is enabled
Given "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List 
 Then the "&File" toolbar Item "New" is enabled

Scenario: Toolbar Icon enabled for Customer Editor Window
    If we select any field in the Customer Editor Window, New is enabled
Given "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List
  And I select the "CustomerEditor Window" Panel's "Name:" 
 Then the "&File" toolbar Item "New" is enabled

