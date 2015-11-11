Feature: Relaodable Customer List
As a User
In order to Track Customers
I want to be able to Refresh The Customers List


Scenario: The Root Node is Correct
    The Root Node's Name is Correct
Given I have a "CRUDyCucumber" Application
  And I have the test Database
  And I have a "CustomerList Window" Panel
 Then The "CustomerList Window" Panel's ROOT NODE is "Query: SELECT c FROM Customer c"

Scenario: Refresh Popup Available
    The Root Node of the Customer List has a Reload Menu
Given I have a "CRUDyCucumber" Application
  And I have the test Database
  And I have a "CustomerList Window" Panel
 Then The "CustomerList Window" Panel's ROOT NODE has a popup menu item "Reload"

   