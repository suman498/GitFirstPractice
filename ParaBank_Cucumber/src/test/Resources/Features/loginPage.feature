Feature: Login Functionality for ParaBank Website
  As a customer of ParaBank i should be
  able to login to the customer portal of ParaBank

  Background:
    Given I am at the login Page of ParaBank


    Scenario: Login with valid credentials
      Given I have entered valid username and password
      When I click on Login button
      Then i will be logged in successfully
