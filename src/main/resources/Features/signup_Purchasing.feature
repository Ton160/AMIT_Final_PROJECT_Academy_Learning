Feature: User Signup

  @Regression
  Scenario: Verify that User Can Sign Up Successfully
    Given User is on the home page Actually
    When User clicks on the Sign up button Correctly
    And User enters a new username and password
    And User clicks on the Sign up button Correct
    Then User should see a success message "Sign up successful."

  @Regression
  Scenario: Verify that two products are purchased successfully
    Given I am logged in
    When I add Product 1 to the cart
    And I add Product 2 to the cart
    Then I should see both products in the cart
    And the total amount should be calculated correctly
    When I proceed to checkout
    Then I should see a success message

  @Regression
  Scenario: Signup with an existing username should show an error message
    Given User is on the home page
    When User clicks on the Sign up button
    And User enters an existing username and a password
    And User clicks on the Sign Up button
    Then User should see an error message "This user already exist."



  @Regression
  Scenario: User fails to log in with incorrect credentials
    Given I am on the login page
    When I click on the login button
    And I enter username "SseSse"
    And I enter password "515151"
    And I click on the login submit button
    Then I should see an error alert with message "User does not exist."


  @Regression
  Scenario: User adds the same product twice and verifies the quantity update
    Given I am logged in Successfully
    When I add Product 1 to the cart Successfully
    And I add Product 1 to the cart again
    Then I should see the product quantity updated in the cart
    And the total amount should be calculated correctly and Successfully

  @Regression
  Scenario: Attempt to purchase with an expired credit card
    Given I am logged in and have products in the cart
    When I attempt to purchase using an expired credit card
    Then I should see an error message about the expired credit card