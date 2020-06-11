Feature: Updating Full name
As an Admin
I want update a full name of a user when requested
So that the company can comply with GDPR

   Scenario Outline: 
   Given I go to "<website>" website
   And the username is present "<username>"
   When I click on username "<username>"
   And I click on configure link
   And change the current fullname to new a new fullname "<newfullname>"
   And I click save button
   And I click on People link
   Then the new fullname "<newfullname>" should be visible for the username "<username>"
   
Examples:
    | website   | username | newfullname |
    | http://localhost:8080 | matthunt | Ethan Hunt |
    | http://localhost:8080 | chrisp | ChrisPy |
    | http://localhost:8080 | chez | moouuuw |

