Feature: End to End

@login
Scenario Outline:  Search multiple
Given user is on home page
When user register
Then he should see message "Your registration completed"
And he search product with keyword"<keyword>"
Then he should see equal <count> result
When user select the first result it should be added

Examples:
|keyword |  count|
| windows|  1    |
|laptop  |   2   |

@smoke

  Scenario Outline: menu selection
    Given user is on home page
    When user register
    Then he should see message "Your registration completed"
    And he select product with "<keyword>" from menu
    Then he should see equal <count> result
    When user select the second result it sjould be added
 Examples:
 |keyword     |  count  |
 | Notebooks  |  6      |
 |Desktops    |  3      |
