*** Settings ***
Library    SeleniumLibrary

*** Test Cases ***

UI1.1
  1Open index page
  1Search All
  1Search by Criteria
  1Choose Toy

*** Keywords ***
1Open index page
  Open Browser   http://128.199.66.198:8080/toy.html  browser=gc

1Search All
  Wait Until Element Is Enabled   genderCombo
  Wait Until Element Is Enabled   ageCombo
  Click Button   searchButton
  Table Should Contain   tableToyResult_info  Showing 1 to 30 of 30 entries
  Wait Until Page Contains   Showing   3 seconds

1Search by Criteria
  Select From List By Label   genderCombo   Neutral
  Select From List By Label   ageCombo   Baby
  Click Button   searchButton
  Table Should Contain   tableToyResult_info  Showing 1 to 4 of 4 entries
  Wait Until Page Contains   Showing   3 seconds

1Choose Toy
  Click Link   Dancing Alligator



