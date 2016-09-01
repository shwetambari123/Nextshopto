

Feature: End to End for menu

Scenario Outline: menu selection for clothes
Given user is on home page
When user register
Then he should see message "Your registration completed"
And he select product from menu with "<keyword1>"
Then he should able to see equal <count> result
When user select the second result it should  be added
Examples:
|keyword1 |  count|
| Clothing |  4    |
|Shoes     | 3      |