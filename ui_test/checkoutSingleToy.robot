*** Setting ***
Library    SeleniumLibrary

*** Variables ***
${BROWSER}    chrome
${GOOGLE URL}    http://www.google.com
${SEARCHTOY URL}    http://128.199.66.198:8080/toy.html

*** Test Cases ***

searchByAgeAndGender
    openSearchToyPage
    searchUsingAgeAndGender
selectToy
    checkName
    checkImage
    checkQty
    checkStatus
    addToCart
CheckoutSingleItem
    proceedCheckout

*** Keywords ***
openSearchToyPage
  Open Browser    ${SEARCHTOY URL}    ${BROWSER}
searchUsingAgeAndGender
  Wait Until Element Is Enabled   ageCombo
  Select From List By Label   ageCombo   Toddler
  Wait Until Element Is Enabled   genderCombo
  Select From List By Label   genderCombo   Male
  Click Button   searchButton
  Wait Until Element Contains    tableToyResult_info    Showing 1 to 1 of 1 entries
checkName
  Click Link    Melody Express Musical Train
  Wait Until Element Contains    2toyName    Melody Express Musical Train
checkImage
  Element Should Be Visible    2toyImg
  Page Should Contain Image    2toyImg    /img/28.jpg
checkQty
  Wait Until Element Contains    2toyStockQty    20
checkStatus
  Wait Until Element Contains    2toyStock    In Stock
addToCart
  Wait Until Element Is Visible    AddToCart
  Click Button    AddToCart
proceedCheckout
  Wait Until Element Is Visible    ProcessCheckOut
  Click Button    ProcessCheckOut
  Wait Until Element Is Visible    4FullName
  Input Text    4FullName    ฐิติพงษ์ กาญจนาภา
  Input Text    4Address1    99 หมู่ 5 ตำบลบ้านระกาศ
  Input Text    4City    บางบ่อ
  Input Text    4Province    สมุทรปราการ
  Input Text    4Postcode    10560
  Click Button    4Confirm
  Close All Browsers