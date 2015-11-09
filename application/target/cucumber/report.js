$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("\u0027uk/co/oldnicksoftware/crudycucumber/About.feature\u0027");
formatter.feature({
  "line": 1,
  "name": "About Box",
  "description": " As a User\r\n I want to be able to show and hide the about box\r\n In order that I can get useful information",
  "id": "about-box",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 6,
  "name": "Show and Hide the About Box",
  "description": "",
  "id": "about-box;show-and-hide-the-about-box",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "I have a \"Show Manager\" Application",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I click the \"Help|About\" menu",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "the \"About\" Dialog is displayed",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "I click the \"About\" close button",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "the \"About\" Dialog is hidden",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Show Manager",
      "offset": 10
    }
  ],
  "location": "CrudySteps.I_have_a_Application(String)"
});
formatter.result({
  "duration": 171113116,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Help|About",
      "offset": 13
    }
  ],
  "location": "CrudySteps.I_click_the_menu(String)"
});
formatter.result({
  "duration": 546193562,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "About",
      "offset": 5
    },
    {
      "val": "displayed",
      "offset": 22
    }
  ],
  "location": "CrudySteps.the_Dialog_is_displayed(String,String)"
});
formatter.result({
  "duration": 415584827,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "About",
      "offset": 13
    }
  ],
  "location": "CrudySteps.I_click_the_close_button(String)"
});
formatter.result({
  "duration": 21010531,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "About",
      "offset": 5
    },
    {
      "val": "hidden",
      "offset": 22
    }
  ],
  "location": "CrudySteps.the_Dialog_is_displayed(String,String)"
});
formatter.result({
  "duration": 66546,
  "status": "passed"
});
});