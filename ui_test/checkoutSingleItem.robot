*** Setting ***
Library    Selenium2Library

*** Variables ***
${BROWSER}    chrome
${GOOGLE URL}    http://www.google.com
${SEARCHTOY URL}    http://128.199.66.198:8080/toy.html

*** Test Cases ***
searchByAgeAndGender
    openSearchToyPage
    searchUsingAgeAndGender

searchByAge
    openSearchToyPage
    searchUsingAgeOnly

 searchByGender
    openSearchToyPage
    searchUsingGenderOnly

 searchAll
    openSearchToyPage
    searchUsingAll

*** Keywords ***
openSearchToyPage
    Open Browser    ${SEARCHTOY URL}    ${BROWSER}
searchUsingAgeAndGender
  Wait Until Element Is Enabled   ageCombo
  Select From List By Label   ageCombo   Baby
  Wait Until Element Is Enabled   genderCombo
  Select From List By Label   genderCombo   Neutral
  Click Button   searchButton
  #${count}=   Get Element size   tableToyResult
  #Should Be True  ${count}==4 
searchUsingAgeOnly
  Wait Until Element Is Enabled   ageCombo
  Select From List By Label   ageCombo   Baby
  Click Button   searchButton
searchUsingGenderOnly
  Wait Until Element Is Enabled   genderCombo
  Select From List By Label   genderCombo   Neutral
  Click Button   searchButton
searchUsingAll
  Wait Until Element Is Enabled   ageCombo
  Select From List By Label   ageCombo   All
  Wait Until Element Is Enabled   genderCombo
  Select From List By Label   genderCombo   All
  Click Button   searchButton
  Close All Browsers
