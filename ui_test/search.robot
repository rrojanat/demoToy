*** Setting ***
Library   SeleniumLibrary

*** Test Cases ***
Seach All
  Open search page
  Search
  Should display result 131 products

*** Keywords ***
  Open search page
    Open Browser   http://128.199.66.198:8080/index.html  browser=googlechrome

  Search
    Click Element xpath=//nav/a[2]

  Should display result 131 products
    Capture Page Screenshot
    Wait until element contains xpath=//nax Shopping Cart
