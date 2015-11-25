Feature: Customer READ
As a User
In order to Track Customers
I want to be able to Maintain Customers

Scenario: Customer Tree Displayed
    The List of Defined Customers is displayed when we start the Application
Given I have a "CRUDyCucumber" Application
  And I have the test Database
 Then I have a "CustomerList Window" Panel
  And The "CustomerList Window" Panel contains a tree of "Customer"

Scenario: The First Node is Correct
    The First Node's Name is 'Jumbo Eagle Corp II'
Given I have a "CRUDyCucumber" Application
  And I have the test Database
  And I have a "CustomerList Window" Panel
 Then The "CustomerList Window" Panel's FIRST NODE is "Jumbo Eagle Corp II"

Scenario: The Last Node is Correct
    The First Node's Name is 'Yankee Computer Repair Ltd'
Given I have a "CRUDyCucumber" Application
  And I have the test Database
  And I have a "CustomerList Window" Panel
 Then The "CustomerList Window" Panel's LAST NODE is "Yankee Computer Repair Ltd"