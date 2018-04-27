*** Settings ***
Library    SeleniumLibrary

*** Test Cases ***

UI1.1
  1Open index page
  1Search All
  1Search by Criteria
  1Choose Toy
UI1.2
  2Add to Cart
#UI1.3
#  3Shopping Cart Check
#UI1.4
#  4Fill Info
#UI1.5
#  5Show Thank You Page

*** Keywords ***
1Open index page
  Open Browser   http://128.199.66.198:8080/toy.html  browser=gc

1Search All
  Wait Until Element Is Enabled   genderCombo
  Wait Until Element Is Enabled   ageCombo
  Click Button   searchButton
  #Wait Until Element Contains   tableToyResult_info  Showing 1 to 30 of 30 entries   1.0 seconds
  #Set Selenium Speed   0.3 seconds

1Search by Criteria
  Wait Until Element Contains   genderCombo   Neutral   1.0 seconds
  Wait Until Element Contains   ageCombo   Baby   1.0 seconds
  Select From List By Label   genderCombo   Neutral
  Select From List By Label   ageCombo   Baby
  Click Button   searchButton
  Wait Until Element Contains   tableToyResult   Dancing Alligator   10.0 seconds
  #Table Cell Should Contain   tableToyResult   2   1   Dancing Alligator
  #Wait Until Element Contains   tableToyResult_info  Showing 1 to 4 of 4 entries   1.0 seconds
  #Set Selenium Speed   0.3 seconds

1Choose Toy
  Wait Until Element Contains   tableToyResult   Dancing Alligator   10.0 seconds
  Click Link   Dancing Alligator

2Add to Cart
  Set Selenium Speed   1.0 seconds
  #Wait Until Element Contains   2toyQty   1   10.0 seconds
  Select From List By Label   2toyQty   3
  Set Selenium Speed   1.0 seconds
  #Wait Until Element Contains   2toyQty   3   50.0 seconds
  Select From List By Label   2toyQty   1
  Click Button   AddToCart

#3Shopping Cart Check
#  #Table Cell Should Contain   tableShoppingCart   3   3   100.00
#  Click Button   ProcessCheckOut

#4Fill Info
#  Open Browser   http://128.199.66.198:8080/ShippingAddress.html  browser=gc
#  Input Text   4FullName   Sadathit Issamo สดาทิตย์ อิสโม
#  Input Text   4Address1   111/222 Ladprow Soi.3 ลาดพร้าว ซ.3
#  Input Text   4Address2   Prompran3 Building อาคารพร้อมพันธ์3
#  Input Text   4City   Ladprow ลาดพร้าว
#  Input Text   4Province   Bangkok กรุงเทพ
#  Input Text   4Postcode   10540
#  #Click Button   4Confirm

#5Show Thank You Page
#  Table Should Contain   locator  Thank You

