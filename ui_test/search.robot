*** Setting ***
Library   SeleniumLibrary
Library   SeleniumLibrary

*** Test Cases ***
Navigate
  Open index page
  Click Search Toy at Menu
  Search by Gender
  #Click Hyper Link Toy
  Open Toy Detail
  Open Shipping Address

*** Keywords ***
#Open index page
#  Open Browser   http://128.199.66.198:8080/index.html  browser=gc
#
#Click Search Toy at Menu
#  Click Link   Search Toy
#
#Search by Gender
#  Wait Until Page Contains Element   resultTable
#  Wait Until Element Is Enabled   genderCombo
#  Select From List By Label   genderCombo   Neutral
#  Click Button   searchButton
#
#Should display result 131 products
#    Capture Page Screenshot
#    Wait until element contains xpath=//nax Shopping Cart

Open Toy Detail
   Open Browser   http://128.199.66.198:8080/toyName.html  browser=gc
   Select From List By Label   2toyQty   1
   Click Button   AddToCart

Open Shipping Address
   Open Browser   http://128.199.66.198:8080/ShippingAddress.html  browser=gc
   Input Text   4FullName   Sadathit Issamo สดาทิตย์ อิสโม
   Input Text   4Address1   111/222 Ladprow Soi.3 ลาดพร้าว ซ.3
   Input Text   4Address2   Prompran3 Building อาคารพร้อมพันธ์3
   #Click Button   4Confirm