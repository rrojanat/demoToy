*** Setting ***
Library    SeleniumLibrary

*** Variables ***
${BROWSER}    chrome
${GOOGLE URL}    http://www.google.com
${SEARCHTOY URL}    http://128.199.66.198:8080/toy.html

*** Test Cases ***

searchAll
    openSearchToyPage
    searchUsingAll
selectToy
    checkName
    checkImage
    checkQty
    checkStatus
    addToCart

*** Keywords ***
openSearchToyPage
  Open Browser    ${SEARCHTOY URL}    ${BROWSER}
searchUsingAll
  Wait Until Element Is Enabled   ageCombo
  Select From List By Label   ageCombo   All
  Wait Until Element Is Enabled   genderCombo
  Select From List By Label   genderCombo   All
  Click Button   searchButton
  Capture Page Screenshot
checkName
  Click Link    Jacques the Peacock Play and Grow
  Wait Until Element Contains    2toyName    Jacques the Peacock Play and Grow
checkImage
  Element Should Be Visible    2toyImg
checkQty
  Wait Until Element Contains    2toyStockQty    20
checkStatus
  Wait Until Element Contains    2toyStock    In Stock
addToCart
  Click Button    AddToCart
  Capture Page Screenshot
  Close All Browsers