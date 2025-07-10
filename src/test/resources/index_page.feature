Feature: Home Page UI Test

  Scenario: Validate the title of the home page
    Given I launch the e-commerce site
    When I navigate to "index.html"
    Then The page title should be "Orbit"

  Scenario: Validate homepage UI elements
    Given I launch the e-commerce site
    Then The "Grab now!" button should be visible
    And The first product title should be "Men Stylish Shirt"
    And There should be at least 8 products listed

  Scenario: Validate Featured Products section
    Given I launch the e-commerce site
    Then The section with title "Featured Products" should be visible

  Scenario: Validate price display for the first product
    Given I launch the e-commerce site
    Then The first product should display the price "₹349"

  Scenario: Validate footer content
    Given I launch the e-commerce site
    Then The footer should contain "©2024,Orbit Pvt Limited"


