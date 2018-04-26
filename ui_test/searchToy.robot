*** Setting ***
Library    Selenium2Library

*** Variables ***
${BROWSER}    chrome
${GOOGLE URL}    http://www.google.com

*** Test Cases ***
Search All
    เข้าไป google

*** Keywords ***
เข้าไป google
    Open Browser    ${GOOGLE URL}    ${BROWSER}
ค้นหาคำว่าสวัสดี
    Input Text     lst-ib    สวัสดี
    Click Button btnG
รอจนกว่าจะเจอคำว่าสวัสดีตอนเช้า
    Wait Until Page Contains     สวัสดีตอนเช้า