Feature: Customer
As a User
In order to Track Customers
I want to be able to Maintain Customers

Scenario: Customer Tree Displayed
    The List of Defined Customers is displayed when we start the Application
Given I have a "CRUDyCucumber" Application
  And I have the test Database
 Then I have a "CustomerList Window" Panel
  And The "CustomerList Window" Panel contains a tree of "Customer"

   