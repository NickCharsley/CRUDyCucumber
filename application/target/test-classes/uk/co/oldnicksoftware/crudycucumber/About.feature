Feature: About Box
   As a User
   I want to be able to show and hide the about box
   In order that I can get useful information

Scenario: Show and Hide the About Box
   Given I have a "Show Manager" Application
   When I click the "Help|About" menu
   Then the "About" Dialog is displayed
   When I click the "About" close button
   Then the "About" Dialog is hidden    
    

   