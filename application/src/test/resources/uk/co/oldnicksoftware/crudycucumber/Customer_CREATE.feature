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

Scenario: New Customer Window(s) appear and can be Canceled
    If we press the New Toolbar button then the correct dialogs are displayed
    they contain the correct fields and can then be canceled.
Given "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List 
  And the "&File" toolbar Item "New" is enabled
 When I click the "&File" toolbar Item "New"
 Then the "New Customer" Dialog is displayed
  And the "New Customer" Dialog has a text field "Customer Name:"
 When I click the "New Customer" ok button
 Then the "New Customer" Dialog is displayed
  And the "New Customer" Dialog has a text field "Customer City:"
 When I click the "New Customer" cancel button
 Then the "New Customer" Dialog is hidden
 
Scenario: New Customer Window appear and can be Canceled
    If we press the New Toolbar button then the correct dialog is displayed
    and it can be cancelled.
Given "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List 
  And the "&File" toolbar Item "New" is enabled
 When I click the "&File" toolbar Item "New"
 Then the "New Customer" Dialog is displayed
 When I click the "New Customer" cancel button
 Then the "New Customer" Dialog is hidden

Scenario: New Customer Window(s) appear and can be Canceled
    If we press the New Toolbar button then the correct dialogs are displayed
    they contain the correct fields and can then be canceled.
Given "Jumbo Eagle Corp II" is selected in the "CustomerList Window" Panel's Node List 
  And the "&File" toolbar Item "New" is enabled
 When I click the "&File" toolbar Item "New"
  And I select the "New Customer" Dialog's "Customer Name:"
  And I type "Old Nick Software"
  And I click the "New Customer" ok button
 Then the "New Customer" Dialog is displayed
 When I select the "New Customer" Dialog's "Customer City:"
  And I type "London"
  And I click the "New Customer" ok button
 Then the "New Customer" Dialog is hidden
  And The "CustomerList Window" Panel's Node List contains "Old Nick Software"
 