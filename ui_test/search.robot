*** Setting ***
Library   SeleniumLibrary

*** Test Cases ***
1Navigate
  1.1Open index page
  1.2Search All
  1.3Search by Gender
  1.4Search by Age

2Select Toy
  2.1Add to Cart Toy1

3Shopping List
  3.1Qty

*** Keywords ***
1.1Open index page
  Open Browser   http://128.199.66.198:8080/toy.html  browser=gc

1.2Search All
  Wait Until Element Is Enabled   genderCombo
  Wait Until Element Is Enabled   ageCombo
  Click Button   searchButton

1.3Search by Gender
  Wait Until Element Is Enabled   genderCombo
  Wait Until Element Is Enabled   ageCombo
  Set Selenium Speed   0.3 seconds
  Select From List By Label   genderCombo   Neutral
  Click Button   searchButton
  Set Selenium Speed   0.3 seconds

1.4Search by Age
  Wait Until Element Is Enabled   genderCombo
  Wait Until Element Is Enabled   ageCombo
  Select From List By Label   ageCombo   Toddler
  Click Button   searchButton
  Set Selenium Speed   0.3 seconds
  Input Text   xpath: //*[@id="tableToyResult_filter"]/label/input   Jacques
  Click Link   Jacques the Peacock Play and Grow


2.1Add to Cart Toy1
  Click Button   AddToCart

3.1Qty
  Select From List By Label   3toyQty   2
  Select From List By Label   3toyQty   3
  Select From List By Label   3toyQty   1
  #Table Should Contain   SubTotal   100

#Open Toy Detail
#   Open Browser   http://128.199.66.198:8080/toy.html  browser=gc
#   Select From List By Label   2toyQty   1
#   Click Button   AddToCart
#
#Open Shipping Address
#   Open Browser   http://128.199.66.198:8080/ShippingAddress.html  browser=gc
#   Input Text   4FullName   Sadathit Issamo สดาทิตย์ อิสโม
#   Input Text   4Address1   111/222 Ladprow Soi.3 ลาดพร้าว ซ.3
#   Input Text   4Address2   Prompran3 Building อาคารพร้อมพันธ์3
#   Input Text   4City   Ladprow ลาดพร้าว
#   Input Text   4Province   Bangkok กรุงเทพ
#   Input Text   4Postcode   10540
   #Click Button   4Confirm