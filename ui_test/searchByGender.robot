name=2toyName --Jacques the Peacock Play and Grow
toyImg=2toyImg --/img/21.jpg
qty=2toyStockQty --20
qty>0=2toystock  --in stock


*** Setting ***
Library    SeleniumLibrary

*** Variables ***
${BROWSER}    chrome
${GOOGLE URL}    http://www.google.com
${SEARCHTOY URL}    http://128.199.66.198:8080/toy.html

*** Test Cases ***
searchByGender
    openSearchToyPage
    searchUsingGenderOnly
    selectPeacockDetail

*** Keywords ***
openSearchToyPage
  Open Browser    ${SEARCHTOY URL}    ${BROWSER}

searchUsingGenderOnly
  Wait Until Element Is Enabled   genderCombo
  Select From List By Label   genderCombo   Neutral
  Click Button   searchButton
  Wait Until Element Is Enabled  //*[@id="tableToyResult"]/tbody
  

selectPeacockDetail
  Wait Until Page Contains     Jacques the Peacock Play and Grow
  Click Link     Jacques the Peacock Play and Grow
  Wait Until Element Contains   2toyName     Jacques the Peacock Play and Grow
  Page Should Contain Image    2toyImg    /img/21.jpg
  Element Should Contain     2toyStockQty   20
  Element Should Contain     2toyStock   In Stock
  
